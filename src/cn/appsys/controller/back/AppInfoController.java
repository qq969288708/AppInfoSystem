package cn.appsys.controller.back;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.Page;
import cn.appsys.service.back.AppInfoService;
import cn.appsys.service.back.Backend_UserService;

@Controller
@RequestMapping("/sys/app")
public class AppInfoController {

	@Resource
	private AppInfoService appInfoService;
	
	//审核状态是否通过
	@RequestMapping(value="/shenHeZhuangTai",method = RequestMethod.POST)
	public String shenHeZhuangTai(@RequestParam("status") Integer status,
			@RequestParam("id") Integer ids) {
		//调用方法执行
		appInfoService.upAppInfoStatus(status, ids);
		
		return "redirect:/sys/app/goAppList";
	}
	
	
	//将信息审核处理页面
	@RequestMapping("/shenHeApp")
	public String shenHeApp(@RequestParam("aid") Integer aid,
			@RequestParam("vid")Integer vid, Model model) {
        //调用service层方法获取AppInfoID
		 AppInfo appInfo = appInfoService.queryInfoListAll(aid);
		//调用service层方法获取版本信息ID
	   List<AppVersion>	appVersion = appInfoService.queryAppVersionInfo(vid);
	   model.addAttribute("appInfo", appInfo);
	   model.addAttribute("appVersion", appVersion.size() > 0 ? appVersion.get(0) : null);
		return "backend/appcheck";
	}
	
	

	@RequestMapping("/queryCategoryByParentId")
	@ResponseBody // 将当前方法返回的值输出到响应流中,输出到页面
	public Object queryCategoryByParentId(@RequestParam("pid") Integer pid) {
		// 根据当前类别ID查询当前类别下的子信息
		List<AppCategory> categoryList = appInfoService.queryCategoryList(pid);
		return categoryList;
	}

	// 负责分页查询按条件APP带审核状态列表信息的方法
	@RequestMapping("/goAppList")
	public String goAppList(@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryFlatformId", required = false) Integer queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
			@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo, Model model) {
		// 1.调用Service层方法获取总记录数
		Integer totalCount = appInfoService.queryTotaCount(querySoftwareName, queryFlatformId, queryCategoryLevel1,
				queryCategoryLevel2, queryCategoryLevel3);
		// 2.封装Page实体类
		Page page = new Page();
		page.setCurrPageNo(currPageNo);
		page.setPageSize(5);
		page.setTotalCount(totalCount);
		// 3.调用Service层查询带审查状态的分页列表信息 回显
		List<AppInfo> appInfoList = appInfoService.queryAppInfoPage(querySoftwareName, queryFlatformId,
				queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, currPageNo, page.getPageSize());
		// 4.调用Service层查询平台列表信息
		List<DataDictionary> flatFormList = appInfoService.queryAllFlatFrom();
		// 5.调用Service层查询列表分类的一级查询
		List<AppCategory> categoryLevel1List = appInfoService.queryCategoryList(null);
		// 如果1级分类的级别不为空，通过1级分类ID将当前1级分类下的2级分类查出来
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;
		if (null != queryCategoryLevel2) {
			categoryLevel2List = queryCategoryLevel1 != null
					? appInfoService.queryCategoryList(queryCategoryLevel1)
					: null;
			if (null != queryCategoryLevel3) {
				categoryLevel3List = queryCategoryLevel2 != null
						? appInfoService.queryCategoryList(queryCategoryLevel2)
						: null;
			}
		}
		// 6.将查询出来的数据保存到MODEL对象
		model.addAttribute("pages", page);
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("categoryLevel2List", categoryLevel2List);
		model.addAttribute("categoryLevel3List", categoryLevel3List);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		// 7.跳转到BackGoList页面
		return "backend/applist";
	}

}
