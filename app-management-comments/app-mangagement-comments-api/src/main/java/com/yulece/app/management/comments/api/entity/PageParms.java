package com.yulece.app.management.comments.api.entity;

public class PageParms {
    private int pageNo = 1;// 页码，默认是第一页
    private int pageSize = 10;// 每页显示的记录数，默认是15

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
}
