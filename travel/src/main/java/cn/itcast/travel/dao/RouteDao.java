package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
    *@Description:查询线路总记录数
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract Integer findTotalCount(Integer cid,String rname);
    /**
    *@Description:每页的线路信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract List<Route>findByPage(Integer cid,Integer start,Integer pageSize,String rname);
    /**
    *@Description:根据线路id查询线路详情
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/15
    *@Time:
    */
    public abstract Route findById(Integer rid);

}
