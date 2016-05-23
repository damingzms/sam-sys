package cn.sam.test.helper.controllerAndView;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.RowBounds;

public class Page<T> {
	
	public static final int DEFAULT_CURRENT_PAGE = 1;
	
	public static final int DEFAULT_PAGE_SIZE = 5;

	/**
	 * 记录总的页数
	 */
	private int pageNum;

	/**
	 * 记录当前的页码
	 */
	private int currentPage = DEFAULT_CURRENT_PAGE;

	/**
	 * 总的记录数
	 */
	private int total;
	/**
	 * 每页最多显示记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 记录当前页的内容
	 */
	private List<T> rows;

	/**
	 * 其它属性的值
	 */
	private Properties userdata;

	/**
	 * 设置总页数
	 * 
	 * @param total
	 *            记录数
	 * @param pageSize
	 *            每页最大的记录数
	 */
	public void setPageNum(int total, int pageSize) {
		if (total >= 0 && pageSize > 0) {
			pageNum = (total - 1) / pageSize + 1;
		}
	}
	
	public RowBounds getRowBounds() {
		return new RowBounds((currentPage - 1) * pageSize, pageSize);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Properties getUserdata() {
		return userdata;
	}

	public void setUserdata(Properties userdata) {
		this.userdata = userdata;
	}

}
