package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelBLService {
    ArrayList<HotelVO> search(int areaId);

    ArrayList<HotelVO> getAll();

    HotelVO getByStaff(String staff);

    HotelVO getDetail(int id);

    ResultMessage add(HotelVO vo);

    ResultMessage delete(int id);

    ResultMessage modify(int id, HotelVO vo);

    ResultMessage changeStaff(int id, String username);
}
