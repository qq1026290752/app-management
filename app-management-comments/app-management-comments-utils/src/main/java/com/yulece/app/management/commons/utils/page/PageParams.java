package com.yulece.app.management.commons.utils.page;

public class PageParams {
    private int pageNo = 1;// 页码，默认是第一页
    private int pageSize = 10;// 每页显示的记录数，默认是15
    private String sort;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
