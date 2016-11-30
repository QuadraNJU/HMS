package nju.quadra.hms.bl;

import java.util.ArrayList;

import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.data.mysql.HotelDataServiceImpl;
import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.AreaPO;
import nju.quadra.hms.po.HotelPO;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;

public class HotelBL implements HotelBLService{
    HotelDataService hotelDataService;
    
    public HotelBL() {
		hotelDataService = new HotelDataServiceImpl();
	}
    
	@Override
	public ArrayList<HotelVO> search(int areaId) {
		ArrayList<HotelVO> voarr = new ArrayList<>();
		try {
			ArrayList<HotelPO> poarr = hotelDataService.getByArea(areaId);
			for(HotelPO po: poarr) voarr.add(HotelBL.toVO(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return voarr;
	}

	@Override
	public ArrayList<HotelVO> getAll() {
		ArrayList<HotelVO> voarr = new ArrayList<>();
		try {
			ArrayList<HotelPO> poarr = hotelDataService.getAll();
			for(HotelPO po: poarr) voarr.add(HotelBL.toVO(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return voarr;
	}

	@Override
	public HotelVO getByStaff(String staff) {
		HotelPO po = null;
		try {
			po = hotelDataService.getByStaff(staff);
			return HotelBL.toVO(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

	@Override
	public HotelVO getDetail(int id) {
		HotelPO po = null;
		try {
			po = hotelDataService.getById(id);
			return HotelBL.toVO(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<AreaVO> getAllArea() {
		ArrayList<AreaVO> voarr = new ArrayList<>();
		try {
			ArrayList<AreaPO> poarr = hotelDataService.getAllArea();
			for (AreaPO po: poarr)
			    voarr.add(new AreaVO(po.getId(), po.getCityName(), po.getAreaName()));
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}

	@Override
	public ResultMessage modify(HotelVO vo) {
		HotelPO po = HotelBL.toPO(vo);
		try {
			hotelDataService.update(po);
			return new ResultMessage(ResultMessage.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}

	@Override
	public ResultMessage changeStaff(int id, String username) {
		HotelPO po = null;
		try {
		    po = hotelDataService.getById(id); 
			po.setStaff(username);
			hotelDataService.update(po);
			return new ResultMessage(ResultMessage.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}
	
	public static HotelVO toVO(HotelPO po) {
		return new HotelVO(po.getId(), po.getName(), po.getAreaId(),
				po.getAddress(), po.getDescription(), po.getFacilities(), po.getStar(), po.getStaff());
	}
	
	public static HotelPO toPO(HotelVO vo) {
		return new HotelPO(vo.id, vo.name, vo.areaId, vo.address, vo.description, vo.facilities, vo.star, vo.staff);
	}
	
}