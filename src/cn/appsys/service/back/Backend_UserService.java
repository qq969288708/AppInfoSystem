package cn.appsys.service.back;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.BackendUser;

public interface Backend_UserService {
	//查询账号密码
	 BackendUser queryBackendUserLogin(String userCode,
			 String userPassword);

}
