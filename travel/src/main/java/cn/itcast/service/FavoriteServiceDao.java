package cn.itcast.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.sql.Date;
import java.util.List;

public interface FavoriteServiceDao {
    /**
    *@Description:判断用户是否收藏
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/16
    *@Time:
    */
    public abstract Favorite isFavorite(String rid,Integer uid);

    /**
    *@Description:根据线路rid查询收藏次数
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/16
    *@Time:
    */
    public abstract Integer findCountByRid(String rid);

    /**
    *@Description:添加收藏记录
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/16
    *@Time:
    */
    public abstract void add(String rid, Integer uid);

    /**
     *@Description:我的收藏中的线路信息
     *@Param:
     *@return:
     *@Author:liu shu gong
     *@Date:2019/5/17
     *@Time:
     */
    public abstract PageBean<Route> myFavorite(Integer currentPage, Integer pageSize,Integer uid);
}
