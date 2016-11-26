package nju.quadra.hms.blservice.customerBL;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 16/10/15.
 */
public interface CustomerBLService {
    ResultMessage register(UserVO vo);

    MemberVO getMemberInfo(String username);

    ResultMessage enroll(MemberVO vo);
}
