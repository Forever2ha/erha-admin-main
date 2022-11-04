/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */
package fun.yizhierha.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.constant.EhAdminConstant;
import fun.yizhierha.common.xss.SQLFilter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;


public class Query<T> {
    @Data
    public static class PageVo{
        @ApiModelProperty("每页大小")
        private Integer pageSize;
        @ApiModelProperty("当前页")
        private Integer currentPage;
        @ApiModelProperty("排序键")
        private String sidx;
        @ApiModelProperty("顺序")
        private String order;// asc正序 desc倒序
    }

    public IPage<T> getPage(PageVo pageVo) {
        return this.getPage(pageVo, null, false);
    }

    public IPage<T> getPage(PageVo pageVo, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if(pageVo.getCurrentPage() != null){
            curPage = pageVo.getCurrentPage();
        }
        if(pageVo.getPageSize() != null){
            limit = pageVo.getPageSize();
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);


        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject(pageVo.getSidx());
        String order = (String)pageVo.getOrder();


        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(EhAdminConstant.ASC.equalsIgnoreCase(order)) {
                return  page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if(StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        //默认排序
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
