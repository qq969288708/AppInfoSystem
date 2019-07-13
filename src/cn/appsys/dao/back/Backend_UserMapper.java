package cn.appsys.dao.back;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.BackendUser;

/**
 * ��̨��¼��ӿ�
 * @author Administrator
 *
 */
public interface Backend_UserMapper {
	 
	//查询账号密码
	 BackendUser queryBackendUserLogin(@Param("userCode")String userCode,
			 @Param("userPassword")String userPassword);
}
