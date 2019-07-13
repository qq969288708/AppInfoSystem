package cn.appsys.service.back.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.back.Backend_UserMapper;
import cn.appsys.pojo.BackendUser;
import cn.appsys.service.back.Backend_UserService;

@Service("backend_UserService")
public class Backend_UserServiceImpl implements Backend_UserService {
    
	@Resource(name="backend_UserMapper")
    private Backend_UserMapper backend_UserMapper;
    
	//查询用户名和密码方法
	public BackendUser queryBackendUserLogin(String userCode, String userPassword) {

		return backend_UserMapper.queryBackendUserLogin(userCode, userPassword);
	}

}
