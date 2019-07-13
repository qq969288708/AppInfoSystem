package cn.appsys.dao.back;
/**
 * 审核APP信息接口
 * @author Administrator
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import cn.appsys.pojo.DataDictionary;

public interface AppInfoMapper { 
	
	
	
	
	//负责分页查询按条件APP带审核状态列表信息的总记录数	
	int queryTotaCount(
			@Param("querySoftwareName")String querySoftwareName,
			@Param("queryFlatformId") Integer queryFlatformId,
			@Param("queryCategoryLevel1") Integer queryCategoryLevel1,
			@Param("queryCategoryLevel2") Integer queryCategoryLevel2,
			@Param("queryCategoryLevel3") Integer queryCategoryLevel3);
    
	//负责按条件查询审核状态的APP信息列表
	/**
	 * 
	 * @param 软件名称
	 * @param 软件平台所属的ID名称
	 * @param 一级分类
	 * @param 二级分类
	 * @param 三级分类
	 * @param 当前页码
	 * @param 每页显示的数据行数
	 * @return
	 */
	List<AppInfo> queryAppInfoPage(
			@Param("querySoftwareName")String querySoftwareName,
			@Param("queryFlatformId") Integer queryFlatformId,
			@Param("queryCategoryLevel1") Integer queryCategoryLevel1,
			@Param("queryCategoryLevel2") Integer queryCategoryLevel2,
			@Param("queryCategoryLevel3") Integer queryCategoryLevel3,
			@Param("from") Integer from,@Param("pageSize") Integer pageSize);
	/**
	 * 查询所有平台列表信息
	 * @return
	 */
    List<DataDictionary> queryAllFlatFrom();
    
    /**
     * 查询所有一级列表信息
     * @return
     */
    List<AppCategory> queryCategoryList(@Param("categoryId")Integer categoryId);
    
    //根据信息ID查询，返回对象
    AppInfo queryInfoListAll(@Param("aid") Integer aid);
    
    //根据版本ID查询最新版本信息
    List<AppVersion> queryAppVersionInfo(@Param("vid") Integer vid);
    //修改审核状态为不通过
    int upAppInfoStatus(@Param("idam")Integer idam,@Param("id")Integer id);
  
    
}
