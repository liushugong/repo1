package cn.itcast.service.impl;

import cn.itcast.service.RouteServiceDao;
import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;

/**
 * @author Liu Shugong
 * @description RouteServiceDaoImpl
 * @date 2019/5/14
 */
public class RouteServiceDaoImpl implements RouteServiceDao {

    private RouteDao routeDaoImpl = new RouteDaoImpl();
    private RouteImgDao routeImgDaoImpl = new RouteImgDaoImpl();
    private SellerDao sellerDaoImpl = new SellerDaoImpl();
    private FavoriteDao favoriteDaoImpl = new FavoriteDaoImpl();
    /**
     * @Description:查询线路并返回PageBean对象
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/14
     * @Time:
     */
    @Override
    public PageBean<Route> getPageBean(Integer cid, Integer currentPage, Integer pageSize, String rname) {
        PageBean<Route> pb = new PageBean<>();
        Integer totalCount = routeDaoImpl.findTotalCount(cid, rname);
        Integer start = (currentPage - 1) * pageSize;
        List<Route> routeList = routeDaoImpl.findByPage(cid, start, pageSize, rname);
        Integer totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        pb.setRouteList(routeList);
        return pb;
    }

    @Override
    public Route findById(String rid) {
        //1.根据id去route表中查询route对象

            Route route = routeDaoImpl.findById(Integer.parseInt(rid));

        //2.根据route的id 查询图片集合信息
        List<RouteImg> routeImgList = routeImgDaoImpl.findById(Integer.parseInt(rid));
        //2.2将集合设置到route对象
        route.setRouteImgList(routeImgList);
        //3.根据route的sid（商家id）查询商家对象
        Seller seller = sellerDaoImpl.findById(route.getSid());
        route.setSeller(seller);

        Integer count = favoriteDaoImpl.findCountByRid(Integer.parseInt(rid));
        route.setCount(count);
        return route;
    }
}
