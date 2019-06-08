package cn.itcast.travel.domain;

import java.util.List;

/**
 * @author Liu Shugong
 * @description PageBean
 * @date 2019/5/14
 */
public class PageBean<T> {
    private Integer totalCount;
    private Integer totalPage;
    private Integer currentPage;
    private Integer pageSize;
    private List<T> routeList;

    public PageBean() {
    }

    public PageBean(Integer totalCount, Integer totalPage, Integer currentPage, Integer pageSize, List<T> routeList) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.routeList = routeList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<T> routeList) {
        this.routeList = routeList;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", routeList=" + routeList +
                '}';
    }
}
