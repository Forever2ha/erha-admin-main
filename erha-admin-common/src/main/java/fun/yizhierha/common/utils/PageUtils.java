package fun.yizhierha.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@ApiModel("分页信息封装的数据")
public class PageUtils<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("总记录数")
	private int totalCount;

	@ApiModelProperty("每页记录数")
	private int pageSize;

	@ApiModelProperty("总页数")
	private int totalPage;

	@ApiModelProperty("当前页数")
	private int currentPage;

	@ApiModelProperty("列表数据")
	private List<T> list;
	
	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currentPage    当前页数
	 */
	public PageUtils(List<T> list, int totalCount, int pageSize, int currentPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}

	/**
	 * 分页
	 */
	public PageUtils(IPage<T> page) {
		this.list = page.getRecords();
		this.totalCount = (int)page.getTotal();
		this.pageSize = (int)page.getSize();
		this.currentPage = (int)page.getCurrent();
		this.totalPage = (int)page.getPages();
	}

}
