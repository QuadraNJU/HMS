package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.customerBL.CreditRecordBLService;
import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.data.mysql.UserDataServiceImpl;
import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

public class CustomerBL implements CustomerBLService {

    private UserBLService userBL;
    private CreditRecordBLService creditRecordBL;
	private UserDataService userDataService;
    
    public CustomerBL() {
        userBL = new UserBL();;
        creditRecordBL = new CreditRecordBL();
		userDataService = new UserDataServiceImpl();
	}

	@Override
	public ResultMessage register(UserVO vo) {
		vo.type = UserType.CUSTOMER;
		return new UserBL().add(vo);
	}

	public double getCurrentCredit(String username) {
    	try {
    		ArrayList<CreditRecordVO> record = creditRecordBL.get(username);
    		if (record != null && record.size() > 0) {
    			return record.get(0).creditResult;
			}
		} catch (Exception e) {
    		e.printStackTrace();
		}
		return CreditRecordBL.ORIGINAL_CREDIT;
	}

	@Override
	public MemberVO getMemberInfo(String username) {
		try {
			UserPO po = userDataService.get(username);
			if (po != null) {
				return new MemberVO(po.getUsername(), po.getMemberType(), po.getBirthday(), po.getCompanyName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage enroll(MemberVO vo) {
		try {
			UserPO po = CustomerBL.toPO(vo);
			UserVO uservo = CustomerBL.toVO(po);
			userBL.modify(uservo);
			return new ResultMessage(ResultMessage.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
		}
	}
    	
	public static UserVO toVO(UserPO po) {
		return new UserVO(po.getUsername(), po.getPassword(), po.getName(), po.getContact(), po.getType());
	}
	
	public static UserPO toPO(MemberVO vo) {
		UserVO user = new UserBL().get(vo.username);
		return new UserPO(user.username, user.password, user.name, user.contact,
				user.type, vo.memberType, vo.birthday, vo.companyName);
	}
	
}
