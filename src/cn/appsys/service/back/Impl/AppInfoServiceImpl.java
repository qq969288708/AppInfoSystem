package cn.appsys.service.back.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.back.AppInfoMapper;
import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.service.back.AppInfoService;
@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
	
	@Resource(name="appInfoMapper")
	private AppInfoMapper appInfoMapper;

	public int queryTotaCount(String querySoftwareName, Integer queryFlatformId, Integer queryCategoryLevel1,
			Integer queryCategoryLevel2, Integer queryCategoryLevel3) {

		return appInfoMapper.queryTotaCount(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
	}

	public List<AppInfo> queryAppInfoPage(String querySoftwareName, Integer queryFlatformId,
			Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer currPage,
			Integer pageSize) {
		
		//计算偏移量位置
		Integer from = (currPage-1)*pageSize;
		return appInfoMapper.queryAppInfoPage(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, from, pageSize);
	}

	public List<DataDictionary> queryAllFlatFrom() {
		// TODO Auto-generated method stub
		return appInfoMapper.queryAllFlatFrom();
	}

	public List<AppCategory> queryCategoryList(Integer categoryId) {
		// TODO Auto-generated method stub
		return appInfoMapper.queryCategoryList(categoryId);
	}

	//根据信息ID查询，返回对象
	public AppInfo queryInfoListAll(Integer aid) {
		// TODO Auto-generated method stub
		return appInfoMapper.queryInfoListAll(aid);
	}
    
	//根据版本ID查询最新版本信息
	public List<AppVersion> queryAppVersionInfo(Integer vid) {
		// TODO Auto-generated method stub
		return appInfoMapper.queryAppVersionInfo(vid);
	}
     
	//修改状态是否通过
	public int upAppInfoStatus(Integer idam, Integer id) {
		// TODO Auto-generated method stub
		return appInfoMapper.upAppInfoStatus(idam, id);
	}
    
	
	
	
	
	
	
	


}
