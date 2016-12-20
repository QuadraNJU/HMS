package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

public interface CustomerBLService {
    ResultMessage register(UserVO vo);

    MemberVO getMemberInfo(String username);

    ResultMessage modifyMemberInfo(MemberVO vo);

    ResultMessage enroll(MemberVO vo);

    ArrayList<String> getAllCompany();
}
