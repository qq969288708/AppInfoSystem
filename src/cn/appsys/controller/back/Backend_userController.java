package cn.appsys.controller.back;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appsys.pojo.BackendUser;
import cn.appsys.service.back.Backend_UserService;

@Controller
@RequestMapping("/user")
public class Backend_userController {
	
	@Resource
	private Backend_UserService backend_UserService;
	
	//跳转到登录界面
	@RequestMapping("/gologin")
	public String gobackendlogin() {
				
		return "backendlogin";
	}
	

	//登录验证界面
	@RequestMapping(value="/doLogin",method = RequestMethod.POST)
	public String doLogin(@RequestParam("userCode")String userCode,
			@RequestParam("userPassword")String userPassword, 
			HttpSession session,Model model) {
		//调用查询登录方法
		BackendUser user = backend_UserService.queryBackendUserLogin(userCode, userPassword);
		
		if(user==null) {
			model.addAttribute("error","用户名或密码不正确");
			return "backendlogin";			
		}else {
			session.setAttribute("userSession", user);
			return "backend/main";
		}		
	}
	
	//注销退出页面
	@RequestMapping("/zhuXiao")
	public String zhuXiao(HttpServletRequest request) {
		request.removeAttribute("userSession");
		return"backendlogin";
	}

	
	
	
}
