package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

import java.util.Date;
import java.util.List;

public interface FavoriteDao {
    /**
    *@Description:根据rid 和 uid 查询收藏信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/16
    *@Time:
    */
    public abstract Favorite findByRidAndUid(Integer rid,Integer uid);

    /**
    *@Description:根据rid 查询收藏次数
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/16
    *@Time:
    */
    public abstract Integer findCountByRid(Integer rid);
    
    /**
    *@Description:添加到收藏记录
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/16
    *@Time:
    */
    public abstract void add(Integer rid,Integer uid);
    /**
     *@Description:我的收藏中的线路信息(具有分页效果)
     *@Param:
     *@return:
     *@Author:liu shu gong
     *@Date:2019/5/17
     *@Time:
     */
    public abstract List<Route> myFavorite(Integer currentPage,Integer pageSize,Integer uid);
    /**
    *@Description:查询我的收藏中总计的线路信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/18
    *@Time:
    */
    public abstract Integer myFavoriteFindAll(Integer uid);
}
