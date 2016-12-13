package nju.quadra.hms.bl;

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
        if (session != null) {
            if (session.userType.equals(UserType.CUSTOMER)) {
                username = session.username;
            } else {
                return new ArrayList<>();
            }
        }

        ArrayList<HotelSearchVO> result = new ArrayList<>();
        ArrayList<HotelVO> hotels = getByArea(areaId);
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
        ArrayList<HotelVO> voarr = new ArrayList<>();
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getByArea(areaId);
            for (HotelPO po : poarr) voarr.add(HotelBL.toVO(po));
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public ArrayList<HotelVO> getAll() {
        ArrayList<HotelVO> voarr = new ArrayList<>();
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getAll();
            for (HotelPO po : poarr) voarr.add(HotelBL.toVO(po));
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public HotelVO getByStaff(String staff) {
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
            // e.printStackTrace();
        }
        return null;
    }

    @Override
    public HotelVO getDetail(int id) {
        try {
            HotelPO po = hotelDataService.getById(id);
            return HotelBL.toVO(po);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<AreaVO> getAllArea() {
        ArrayList<AreaVO> voarr = new ArrayList<>();
        try {
            ArrayList<AreaPO> poarr = hotelDataService.getAllArea();
            for (AreaPO po : poarr)
                voarr.add(new AreaVO(po.getId(), po.getCityName(), po.getAreaName()));
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public ResultMessage add(HotelVO vo) {
        HotelPO po = HotelBL.toPO(vo);
        try {
            hotelDataService.insert(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage delete(int id) {
        try {
            HotelPO po = hotelDataService.getById(id);
            hotelDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(HotelVO vo) {
        if (session != null) {
            if (session.userType.equals(UserType.HOTEL_STAFF)) {
                // 酒店工作人员只允许操作对应的酒店
                HotelVO hotel = getByStaff(session.username);
                if (hotel == null || hotel.id != vo.id) {
                    return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
                }
                vo.staff = session.username;
            } else if (!session.userType.equals(UserType.WEBSITE_MASTER)) {
                return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
            }
        }

        if (vo.name.trim().isEmpty() || vo.address.trim().isEmpty() || vo.description.trim().isEmpty()
                || vo.facilities.trim().isEmpty() || vo.star.trim().isEmpty()) {
            return new ResultMessage(ResultMessage.RESULT_DATA_INVALID);
        }

        HotelPO po = HotelBL.toPO(vo);
        try {
            hotelDataService.update(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    private static HotelVO toVO(HotelPO po) {
        return new HotelVO(po.getId(), po.getName(), po.getAreaId(),
                po.getAddress(), po.getDescription(), po.getFacilities(), po.getStar(), po.getStaff());
    }

    private static HotelPO toPO(HotelVO vo) {
        return new HotelPO(vo.id, vo.name, vo.areaId, vo.address, vo.description, vo.facilities, vo.star, vo.staff);
    }

}