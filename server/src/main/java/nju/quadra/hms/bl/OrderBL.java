package nju.quadra.hms.bl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.orderBL.OrderBLService;
import nju.quadra.hms.data.mysql.CreditDataServiceImpl;
import nju.quadra.hms.data.mysql.OrderDataServiceImpl;
import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.dataservice.OrderDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.CreditRecordPO;
import nju.quadra.hms.po.OrderPO;
import nju.quadra.hms.vo.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by RaUkonn on 2016/11/20.
 */
public class OrderBL implements OrderBLService {
    private OrderDataService orderDataService;


    public OrderBL() {
        orderDataService = new OrderDataServiceImpl();

    }

    /**
     * 注释部分都是正文，但是两个promotion还没写orz
     */
    @Override
    public PriceVO getPrice(OrderVO vo) {
//        WebsitePromotionDataService websitePromotionDataService = new WebsitePromotionDataServiceImpl();
//        HotelPromotionDataService hotelPromotionDataService = new HotelPromotionDataServiceImpl();
        PriceVO result = null;
        try {
//            ArrayList<WebsitePromotionPO> wppos = websitePromotionDataService.getAll();
//            Collections.sort(wppos);
//            WebsitePromotionPO wppo = wppos.get(wppos.size() - 1);
//            WebsitePromotionVO wpvo = WebsitePromotionBL.toVO(wppo);
//
//            ArrayList<HotelPromotionPO> hppos = hotelPromotionDataService.get(vo.hotelId);
//            Collections.sort(hppos);
//            HotelPromotionPO hppo = hppos.get(hppos.size() - 1);
//            HotelPromotionVO hpvo = HotelPromotionBL.toVO(hppo);

            /*TEST*/
            WebsitePromotionVO wpvo = null;
            HotelPromotionVO hpvo = null;
            double originalPrice = vo.price;
//            double finalPrice = originalPrice * wppo.getPromotion() * hppo.getPromotion();
            double finalPrice = originalPrice;
            /*TEST*/
            result =  new PriceVO(originalPrice, finalPrice, hpvo, wpvo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultMessage add(OrderVO vo) {
        try {
            if(!hasValidCredit(vo.username)){
                return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "客户信用值不足，无法预定酒店");
            }
            OrderPO po = OrderBL.toPO(vo);
            orderDataService.insert(po);
        }  catch(SQLIntegrityConstraintViolationException e){
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "订单号已存在，请重新输入");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "服务器访问异常，请重新尝试");
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ArrayList<OrderVO> getByCustomer(String username) {
        ArrayList<OrderVO> voarr = new ArrayList<>();
        try {
            ArrayList<OrderPO> poarr = orderDataService.getByCustomer(username);
            for(OrderPO po: poarr) {
                voarr.add(OrderBL.toVO(po));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public ArrayList<OrderVO> getByHotel(int hotelId) {
        ArrayList<OrderVO> voarr = new ArrayList<>();
        try {
            ArrayList<OrderPO> poarr = orderDataService.getByHotel(hotelId);
            for(OrderPO po: poarr) {
                voarr.add(OrderBL.toVO(po));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public ArrayList<OrderVO> getByState(OrderState state) {
        ArrayList<OrderVO> voarr = new ArrayList<>();
        try {
            ArrayList<OrderPO> poarr = orderDataService.getByState(state);
            for(OrderPO po: poarr) {
                voarr.add(OrderBL.toVO(po));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public ResultMessage undoDelayed(OrderVO vo, boolean returnAllCredit) {
        try {
            CreditDataService creditDataService = new CreditDataServiceImpl();
            OrderPO po = orderDataService.getById(vo.id);
             if(po.getState() != OrderState.DELAYED)
                 //订单状态必须为"异常(逾期)"才可调用此方法
                 return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "该订单无法被撤销（订单状态不为\"异常(逾期)\"），请重新选择");
             po.setState(OrderState.UNDO);
             //todo:这里还要加个记录撤销时间的东西，表打错了orz
             orderDataService.update(po);
             //增添的信用值为订单的原价或者一半
            double currRate = returnAllCredit? CreditRecordBL.UNDO_DELAYED_RATE[1]: CreditRecordBL.UNDO_DELAYED_RATE[0];
             CreditRecordPO creditRecordPO = new CreditRecordPO(0, vo.username, new Date(System.currentTimeMillis()), vo.id, CreditAction.ORDER_UNDO, vo.price * currRate);
             creditDataService.insert(creditRecordPO);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "订单不存在，请确认订单信息");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "服务器访问异常，请重新尝试");
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage undoUnfinished(OrderVO vo) {
        try {
            CreditDataService creditDataService = new CreditDataServiceImpl();
            OrderPO po = orderDataService.getById(vo.id);
            //订单状态必须为"未执行"才可调用此方法
            if(po.getState() != OrderState.UNCOMPLETED)
                return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "该订单无法被撤销（订单状态不为\"未执行\"），请重新选择");
            po.setState(OrderState.UNDO);
            //todo:这里还要加个记录撤销时间的东西，表打错了orz
            orderDataService.update(po);
            //如果撤销的订单距离最晚订单执行时间不足6个小时，撤销的同时扣除用户的信用值，信用值为订单的(总价值*1/2)
            double currRate = 0;
            //由于我们的记录时间是按0:00记录，但是实际上的酒店是按12:00为入住时限的，所以要加一个12h的gap
            if(vo.startDate.getTime() + 12 * 3600 *1000 - System.currentTimeMillis() > CreditRecordBL.LATEST_CHECKIN_TIME_GAP) currRate = CreditRecordBL.UNDO_UNFINISHED_RATE;
            CreditRecordPO creditRecordPO = new CreditRecordPO(0, vo.username, new Date(System.currentTimeMillis()), vo.id, CreditAction.ORDER_UNDO, vo.price * currRate);
            creditDataService.insert(creditRecordPO);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "订单不存在，请确认订单信息");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "服务器访问异常，请重新尝试");
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage finish(OrderVO vo) {
        try {
            CreditDataService creditDataService = new CreditDataServiceImpl();
            OrderPO po = orderDataService.getById(vo.id);
            if(po.getState() == OrderState.FINISHED)
                return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "该订单已经完成，请重新选择");
            po.setState(OrderState.FINISHED);
            po.setEndDate(new Date(System.currentTimeMillis()));
            orderDataService.update(po);
            //信用值为订单原价
            CreditRecordPO creditRecordPO = new CreditRecordPO(0, vo.username, new Date(System.currentTimeMillis()), vo.id, CreditAction.ORDER_UNDO, vo.price * CreditRecordBL.FINISH_RATE);
            creditDataService.insert(creditRecordPO);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "订单不存在，请确认订单信息");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "服务器访问异常，请重新尝试");
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage addRank(OrderRankVO vo) {
        try {
            OrderPO po = orderDataService.getById(vo.orderId);
            po.setRank(vo.rank);
            po.setComment(vo.comment);
            orderDataService.update(po);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "订单不存在，请确认订单信息");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "服务器访问异常，请重新尝试");
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    public static OrderVO toVO(OrderPO po) {
        return new OrderVO(po.getId(), po.getUsername(), po.getHotelId(), po.getStartDate(), po.getEndDate(), po.getRoomId(), po.getRoomCount(), po.getPersonCount(), new Gson().fromJson(po.getPersons(), new TypeToken<ArrayList<String>>(){}.getType()), po.isHasChildren(), po.getPrice(), po.getState(), po.getRank(), po.getComment());
    }

    public static OrderPO toPO(OrderVO vo) {
        return new OrderPO(vo.id, vo.username, vo.hotelId, vo.startDate, vo.endDate, vo.roomId, vo.roomCount, vo.personCount, new Gson().toJson(vo.persons), vo.hasChildren, vo.price, vo.state, vo.rank, vo.comment);
    }

    private boolean hasValidCredit(String username) throws Exception{
        CreditDataService creditDataService = new CreditDataServiceImpl();

        double currCredit = CreditRecordBL.ORIGINAL_CREDIT;
        ArrayList<CreditRecordPO> poarr = creditDataService.get(username);
        for(CreditRecordPO po: poarr) currCredit += po.getDiff();

        return currCredit >= CreditRecordBL.MIN_CREDIT? true: false;
    }
}
