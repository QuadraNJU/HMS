package nju.quadra.hms.bl;

import java.util.ArrayList;

import nju.quadra.hms.blservice.HotelRoomBLService;
import nju.quadra.hms.data.mysql.HotelRoomDataServiceImpl;
import nju.quadra.hms.dataservice.HotelRoomDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.HotelRoomPO;
import nju.quadra.hms.vo.HotelRoomVO;

public class HotelRoomBL implements HotelRoomBLService{
    private final HotelRoomDataService hotelRoomDataService;
    
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
		return voarr;
	}

	@Override
	public HotelRoomVO getById(int roomId) {
		try {
			HotelRoomPO po = hotelRoomDataService.getById(roomId);
			return HotelRoomBL.toVO(po);
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
	public ResultMessage modify(HotelRoomVO vo) {
		HotelRoomPO po = HotelRoomBL.toPO(vo);
		try {
			hotelRoomDataService.update(po);
			return new ResultMessage(ResultMessage.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}

	private static HotelRoomVO toVO(HotelRoomPO po) {
		return new HotelRoomVO(po.getId(), po.getHotelId(), po.getName(), po.getTotal(), po.getPrice());
	}
	
	private static HotelRoomPO toPO(HotelRoomVO vo) {
		return new HotelRoomPO(vo.id, vo.hotelId, vo.name, vo.total, vo.price);
	}
}