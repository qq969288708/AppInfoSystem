package cn.appsys.pojo;

/**
 * 分页实体类
 * @author Administrator
 *
 */
public class Page {
	private Integer currPageNo;//当前页码
	private Integer pageSize;//每一页显示的数据行数
	private Integer totalCount; //总记录数
	private Integer totalPageCount;//总页数
	public Integer getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Integer totalCount) {		
		if(totalCount>0) {
            this.totalCount = totalCount;
			this.totalPageCount = this.totalCount%this.pageSize == 0 ?
					this.totalCount / this.pageSize
					:this.totalCount / pageSize+1;			
		}
	}
	
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
    
	

}
