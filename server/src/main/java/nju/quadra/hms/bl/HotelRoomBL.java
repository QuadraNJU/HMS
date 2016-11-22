package nju.quadra.hms.bl;

import java.util.ArrayList;

import nju.quadra.hms.blservice.hotelBL.HotelBLService;
import nju.quadra.hms.blservice.hotelRoomBL.HotelRoomBLService;
import nju.quadra.hms.data.mysql.HotelDataServiceImpl;
import nju.quadra.hms.data.mysql.HotelRoomDataServiceImpl;
import nju.quadra.hms.dataservice.HotelRoomDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.HotelPO;
import nju.quadra.hms.po.HotelRoomPO;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;

public class HotelRoomBL implements HotelRoomBLService{
    HotelRoomDataService hotelRoomDataService;
    
    public HotelRoomBL() {
		hotelRoomDataService = new HotelRoomDataServiceImpl();
	}

	@Override
	public ArrayList<HotelRoomVO> getAll(int hotelId) {
		ArrayList<HotelRoomVO> voarr = new ArrayList<>();
		try {
			ArrayList<HotelRoomPO> poarr = hotelRoomDataService.get(hotelId);
			for(HotelRoomPO po : poarr) voarr.add(HotelRoomBL.toVO(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage add(HotelRoomVO vo) {
		HotelRoomPO po = HotelRoomBL.toPO(vo);
		try {
			hotelRoomDataService.insert(po);
			return new ResultMessage(ResultMessage.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}

	@Override
	public ResultMessage delete(int roomId) {
		try {
			HotelRoomPO po = hotelRoomDataService.getById(roomId);
			hotelRoomDataService.delete(po);
			return new ResultMessage(ResultMessage.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}

	@Override
	public ResultMessage modify(int roomId, HotelRoomVO vo) {
		HotelRoomPO po = HotelRoomBL.toPO(vo);
		try {
			hotelRoomDataService.update(po);
			return new ResultMessage(ResultMessage.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}

	public static HotelRoomVO toVO(HotelRoomPO po) {
		return new HotelRoomVO(po.getId(), po.getHotelId(), po.getName(), po.getTotal(), po.getPrice());
	}
	
	public static HotelRoomPO toPO(HotelRoomVO vo) {
		return new HotelRoomPO(vo.id, vo.hotelId, vo.name, vo.total, vo.price);
	}
}