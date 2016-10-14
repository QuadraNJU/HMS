package nju.quadra.hms.bl.stub;

import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 16/10/15.
 */
public class CustomerBLService_Stub implements CustomerBLService {
    @Override
    public UserVO getInfo(String username) {
        return new UserVO("quadra", "61d1e734315a0db52c3572d7ab8a8b68ac327bb42699ffd80b4fcec9870e77f3",
                "Quadra", "10086", UserType.WEBSITE_MANAGER, null, null, null);
    }

    @Override
    public ResultMessage enroll(MemberVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
