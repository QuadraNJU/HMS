package nju.quadra.hms.bl;

import nju.quadra.hms.util.Logger;
import java.util.ArrayList;

import nju.quadra.hms.blservice.HotelRoomBLService;
import nju.quadra.hms.data.mysql.HotelRoomDataServiceImpl;
import nju.quadra.hms.dataservice.HotelRoomDataService;
import nju.quadra.hms.model.LoginSession;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.HotelRoomPO;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;

public class HotelRoomBL implements HotelRoomBLService {
    private final LoginSession session;
    private final HotelVO hotel;
    private final HotelRoomDataService hotelRoomDataService = new HotelRoomDataServiceImpl();

    public HotelRoomBL() {
        session = null;
        hotel = null;
    }

    public HotelRoomBL(LoginSession session) {
        this.session = session;
        this.hotel = new HotelBL().getByStaff(session.username);
    }

    @Override
    public ArrayList<HotelRoomVO> getAll(int hotelId) {
        // 安全性: 仅允许酒店工作人员操作自己酒店的客房信息
        if (session != null) {
            if (session.userType.equals(UserType.HOTEL_STAFF)) {
                hotelId = hotel.id;
            } else {
                return new ArrayList<>();
            }
        }

        ArrayList<HotelRoomVO> voarr = new ArrayList<>();
        try {
            ArrayList<HotelRoomPO> poarr = hotelRoomDataService.get(hotelId);
            for (HotelRoomPO po : poarr) voarr.add(HotelRoomBL.toVO(po));
        } catch (Exception e) {
            Logger.log(e);
        }
        return voarr;
    }

    @Override
    public HotelRoomVO getById(int roomId) {
        try {
            HotelRoomPO po = hotelRoomDataService.getById(roomId);
            // 安全性: 仅允许酒店工作人员操作自己酒店的客房信息
            if (session != null && (!session.userType.equals(UserType.HOTEL_STAFF) || po.getHotelId() != hotel.id)) {
                return null;
            }
            return HotelRoomBL.toVO(po);
        } catch (Exception e) {
            Logger.log(e);
        }
        return null;
    }

    @Override
    public ResultMessage add(HotelRoomVO vo) {
        ResultMessage checkResult = checkVO(vo);
        if (checkResult.result != ResultMessage.RESULT_SUCCESS) {
            return checkResult;
        }

        // 安全性: 仅允许酒店工作人员操作自己酒店的客房信息
        if (session != null) {
            if (session.userType.equals(UserType.HOTEL_STAFF)) {
                vo.hotelId = hotel.id;
            } else {
                return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
            }
        }

        HotelRoomPO po = HotelRoomBL.toPO(vo);
        try {
            hotelRoomDataService.insert(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage delete(int roomId) {
        try {
            HotelRoomPO po = hotelRoomDataService.getById(roomId);
            // 安全性: 仅允许酒店工作人员操作自己酒店的客房信息
            if (session != null && (!session.userType.equals(UserType.HOTEL_STAFF) || po.getHotelId() != hotel.id)) {
                return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
            }
            hotelRoomDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(HotelRoomVO vo) {
        ResultMessage checkResult = checkVO(vo);
        if (checkResult.result != ResultMessage.RESULT_SUCCESS) {
            return checkResult;
        }

        try {
            HotelRoomPO po = hotelRoomDataService.getById(vo.id);
            // 安全性: 仅允许酒店工作人员操作自己酒店的客房信息
            if (session != null && (!session.userType.equals(UserType.HOTEL_STAFF) || po.getHotelId() != hotel.id)) {
                return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
            }
            vo.hotelId = po.getHotelId();
            po = HotelRoomBL.toPO(vo);
            hotelRoomDataService.update(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    private ResultMessage checkVO(HotelRoomVO vo) {
        if (vo.name.trim().isEmpty()) {
            return new ResultMessage("客房类型不能为空，请重新输入");
        }
        if (vo.price < 0 || vo.total < 0) {
            return new ResultMessage("客房价格与数量必须为正数，请重新输入");
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    private static HotelRoomVO toVO(HotelRoomPO po) {
        return new HotelRoomVO(po.getId(), po.getHotelId(), po.getName(), po.getTotal(), po.getPrice());
    }

    private static HotelRoomPO toPO(HotelRoomVO vo) {
        return new HotelRoomPO(vo.id, vo.hotelId, vo.name, vo.total, vo.price);
    }
}