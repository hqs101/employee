package com.hnluchuan.staff.dto.api.info;

public class BaseInfoAPIDTO {
    private String totalPages = "";
    private String page = "";
    private String pageSize = "";
    private String totalRecords = "";
    public String getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public String getPageSize() {
        return pageSize;
    }
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
    public String getTotalRecords() {
        return totalRecords;
    }
    public void setTotalRecords(String totalRecords) {
        this.totalRecords = totalRecords;
    }
    
    
}
