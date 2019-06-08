package cn.itcast.service.impl;

import cn.itcast.service.FavoriteServiceDao;
import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.sql.Date;
import java.util.List;

/**
 * @author Liu Shugong
 * @description FavoriteServiceDaoImpl
 * @date 2019/5/16
 */
public class FavoriteServiceDaoImpl implements FavoriteServiceDao {
    private FavoriteDao favoriteDaoImpl = new FavoriteDaoImpl();

    @Override
    public Favorite isFavorite(String rid, Integer uid) {

        Favorite favorite = favoriteDaoImpl.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite;

    }

    @Override
    public Integer findCountByRid(String rid) {
        Integer count = favoriteDaoImpl.findCountByRid(Integer.parseInt(rid));
        return count;
    }

    @Override
    public void add(String rid, Integer uid) {
        favoriteDaoImpl.add(Integer.parseInt(rid), uid);
    }

    @Override
    public PageBean<Route> myFavorite(Integer currentPage, Integer pageSize,Integer uid) {
        PageBean<Route> pb = new PageBean<>();
        List<Route> routeList = favoriteDaoImpl.myFavorite(currentPage, pageSize,uid);
        Integer totalCount = favoriteDaoImpl.myFavoriteFindAll(uid);
        Integer totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        pb.setRouteList(routeList);
        return pb;
    }
}
