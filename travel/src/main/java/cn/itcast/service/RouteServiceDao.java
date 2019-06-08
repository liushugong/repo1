package cn.itcast.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteServiceDao {

    /**
     *@Description:查询线路返回PageBean
     *@Param:
     *@return:
     *@Author:liu shu gong
     *@Date:2019/5/14
     *@Time:
     */
    public abstract PageBean<Route> getPageBean(Integer cid, Integer currentPage, Integer pageSize,String rname);
    
    
    /**
    *@Description:
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/16
    *@Time:
    */
    public abstract Route findById(String rid);
    

}
