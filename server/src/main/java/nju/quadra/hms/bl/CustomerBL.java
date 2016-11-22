package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.data.mysql.UserDataServiceImpl;
import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.HotelPO;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

public class CustomerBL implements CustomerBLService{
    UserBLService userBL;
    
    public CustomerBL() {
       userBL = new UserBL();;
	}
    
	@Override
	public UserVO getInfo(String username) {
		try {
			UserVO vo = userBL.get(username);
			return vo;
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
		return new UserVO(po.getUsername(), po.getPassword(), po.getName(), 
				po.getContact(), po.getType(), po.getMemberType(), po.getBirthday(), po.getCompanyName());
	}
	
	public static UserPO toPO(MemberVO vo) {
		UserBL userBL =new UserBL();
		UserVO user = userBL.get(vo.username);
		user.birthday = vo.birthday;
		user.companyName = vo.companyName;
		user.companyName = vo.companyName;
		return new UserPO(user.username, user.password, user.name, user.contact,
				user.type, user.memberType, user.birthday, user.companyName);
	}
	
}
