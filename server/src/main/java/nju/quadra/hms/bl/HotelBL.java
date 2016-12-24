package nju.quadra.hms.bl;

import nju.quadra.hms.util.Logger;
import java.util.ArrayList;

import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.blservice.HotelRoomBLService;
import nju.quadra.hms.blservice.OrderBLService;
import nju.quadra.hms.data.mysql.HotelDataServiceImpl;
import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.model.LoginSession;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.AreaPO;
import nju.quadra.hms.po.HotelPO;
import nju.quadra.hms.vo.*;

public class HotelBL implements HotelBLService {
    private final LoginSession session;
    private final HotelDataService hotelDataService = new HotelDataServiceImpl();

    public HotelBL() {
        session = null;
    }

    public HotelBL(LoginSession session) {
        this.session = session;
    }

    @Override
    public ArrayList<HotelSearchVO> search(int areaId, String username) {
        // 安全性: 仅限客户调用，且只能获取自己的订单信息
        if (session != null) {
            if (session.userType.equals(UserType.CUSTOMER)) {
                username = session.username;
            } else {
                return new ArrayList<>();
            }
        }

        ArrayList<HotelSearchVO> result = new ArrayList<>();
        ArrayList<HotelVO> hotels = new HotelBL().getByArea(areaId);
        OrderBLService orderBL = new OrderBL();
        HotelRoomBLService hotelRoomBL = new HotelRoomBL();

        for (HotelVO hotelVO : hotels) {
            ArrayList<HotelRoomVO> rooms = hotelRoomBL.getAll(hotelVO.id);
            ArrayList<OrderVO> orders = new ArrayList<>();
            ArrayList<OrderRankVO> ranks = new ArrayList<>();

            ArrayList<OrderDetailVO> hotelOrders = orderBL.getByHotel(hotelVO.id);
            for (OrderVO orderVO : hotelOrders) {
                if (orderVO.state.equals(OrderState.RANKED)) {
                    ranks.add(new OrderRankVO(orderVO.id, orderVO.endDate, orderVO.rank, orderVO.comment));
                }
                if (orderVO.username.equals(username)) {
                    orders.add(orderVO);
                }
            }

            result.add(new HotelSearchVO(hotelVO, rooms, orders, ranks));
        }
        return result;
    }

    @Override
    public ArrayList<HotelVO> getByArea(int areaId) {
        // 安全性: 仅限网站管理人员调用
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ArrayList<>();
        }

        ArrayList<HotelVO> voarr = new ArrayList<>();
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getByArea(areaId);
            for (HotelPO po : poarr) voarr.add(HotelBL.toVO(po));
        } catch (Exception e) {
            Logger.log(e);
        }
        return voarr;
    }

    @Override
    public HotelVO getByStaff(String staff) {
        // 安全性: 酒店工作人员只能获得自己的酒店信息，网站管理人员可以获得所有酒店信息
        if (session != null) {
            if (session.userType.equals(UserType.HOTEL_STAFF)) {
                staff = session.username;
            } else if (!session.userType.equals(UserType.WEBSITE_MASTER)) {
                return null;
            }
        }

        if (session != null) {
            if (session.userType.equals(UserType.HOTEL_STAFF)) {
                staff = session.username;
            } else {
                return null;
            }
        }

        try {
            HotelPO po = hotelDataService.getByStaff(staff);
            return HotelBL.toVO(po);
        } catch (Exception e) {
            Logger.log(e);
        }
        return null;
    }

    @Override
    public HotelVO getDetail(int id) {
        // 安全性: 仅限系统内部调用
        if (session != null) {
            return null;
        }

        try {
            HotelPO po = hotelDataService.getById(id);
            return HotelBL.toVO(po);
        } catch (Exception e) {
            Logger.log(e);
        }
        return null;
    }

    @Override
    public ArrayList<AreaVO> getAllArea() {
        // 该方法无安全性需求
        ArrayList<AreaVO> voarr = new ArrayList<>();
        try {
            ArrayList<AreaPO> poarr = hotelDataService.getAllArea();
            for (AreaPO po : poarr)
                voarr.add(new AreaVO(po.getId(), po.getCityName(), po.getAreaName()));
        } catch (Exception e) {
            Logger.log(e);
        }
        return voarr;
    }

    @Override
    public ResultMessage add(HotelVO vo) {
        ResultMessage checkResult = checkVO(vo);
        if (checkResult.result != ResultMessage.RESULT_SUCCESS) {
            return checkResult;
        }

        // 安全性: 仅限网站管理人员调用
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        if (getByArea(vo.areaId).stream().filter(hotel -> hotel.name.equals(vo.name)).count() > 0) {
            return new ResultMessage("该商圈内存在相同名称的酒店");
        }

        HotelPO po = HotelBL.toPO(vo);
        try {
            hotelDataService.insert(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage delete(int id) {
        // 安全性: 仅限网站管理人员调用
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        try {
            HotelPO po = hotelDataService.getById(id);
            hotelDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(HotelVO vo) {
        ResultMessage checkResult = checkVO(vo);
        if (checkResult.result != ResultMessage.RESULT_SUCCESS) {
            return checkResult;
        }

        // 安全性: 酒店工作人员只允许修改自己的酒店信息，网站管理人员不限
        if (session != null) {
            if (session.userType.equals(UserType.HOTEL_STAFF)) {
                HotelVO hotel = new HotelBL().getByStaff(session.username);
                if (hotel == null || hotel.id != vo.id) {
                    return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
                }
                vo.staff = session.username;
            } else if (!session.userType.equals(UserType.WEBSITE_MASTER)) {
                return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
            }
        }

        HotelPO po = HotelBL.toPO(vo);
        try {
            hotelDataService.update(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    private ResultMessage checkVO(HotelVO vo) {
        if (vo.areaId <= 0 || vo.name.trim().isEmpty() || vo.address.trim().isEmpty() || vo.description.trim().isEmpty() || vo.facilities.trim().isEmpty() || vo.star.trim().isEmpty()) {
            return new ResultMessage("酒店基本信息不完整，请重新输入");
        }
        if (vo.staff != null && !vo.staff.isEmpty()) {
            UserVO user = new UserBL().get(vo.staff);
            if (user == null) {
                return new ResultMessage("该用户名不存在，请重新输入");
            }
            if (!user.type.equals(UserType.HOTEL_STAFF)) {
                return new ResultMessage("该用户名不是酒店工作人员，请重新输入");
            }
            HotelVO hotel2 = new HotelBL().getByStaff(vo.staff);
            if (hotel2 != null && hotel2.id != vo.id) {
                return new ResultMessage("该用户名已经是其它酒店的工作人员，请重新输入");
            }
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    private static HotelVO toVO(HotelPO po) {
        return new HotelVO(po.getId(), po.getName(), po.getAreaId(),
                po.getAddress(), po.getDescription(), po.getFacilities(), po.getStar(), po.getStaff());
    }

    private static HotelPO toPO(HotelVO vo) {
        return new HotelPO(vo.id, vo.name, vo.areaId, vo.address, vo.description, vo.facilities, vo.star, vo.staff);
    }

}