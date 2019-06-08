package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
    *@Description:根据线路id查询线路图片
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/15
    *@Time:
    */
    public abstract List<RouteImg>findById(Integer rid);
}
