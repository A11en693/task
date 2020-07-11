package com.yan.task.request;

public class PageRequest {
    private int page = 1;
    private int pageSize = 10;
    private int start;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start = this.page>1?(page-1)*this.pageSize:0;
    }


}
