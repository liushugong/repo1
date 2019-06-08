package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author Liu Shugong
 * @description FavoriteDaoImpl
 * @date 2019/5/16
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(Integer rid, Integer uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid=? and uid=?";
            favorite = jt.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {

        }
        return favorite;
    }

    /**
     * @Description:根据线路rid查询收藏次数
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/16
     * @Time:
     */
    @Override
    public Integer findCountByRid(Integer rid) {
        String sql = "select count(*) from tab_favorite where rid=?";
        Integer count = jt.queryForObject(sql, Integer.class, rid);
        return count;
    }

    /**
     * @Description:添加收藏记录
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/16
     * @Time:
     */
    @Override
    public void add(Integer rid, Integer uid) {
        String sql = "insert into tab_favorite (rid,date,uid) values(?,?,?)";
        int update = jt.update(sql, rid, new Date(), uid);
    }

    /**
     * @Description:我的收藏中的线路信息
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/17
     * @Time:
     */
    @Override
    public List<Route> myFavorite(Integer currentPage, Integer pageSize ,Integer uid) {
        String sql = "SELECT t1.rid,t1.rname,t1.price,t1.routeIntroduce,t1.rflag,t1.rdate,t1.isThemeTour,t1.count,t1.cid,t1.rimage,t1.sid,t1.sourceId\n" +
                "FROM tab_route t1,tab_favorite t2 WHERE t1.rid=t2.rid AND t2.uid=? limit ? ,?";
        List<Route> routeList = jt.query(sql, new BeanPropertyRowMapper<Route>(Route.class), uid,(currentPage - 1) * pageSize, pageSize);
        return routeList;
    }

    @Override
    public Integer myFavoriteFindAll(Integer uid) {
        String sql = "SELECT count(*) FROM tab_route t1, tab_favorite t2 WHERE t1.rid = t2.rid AND t2.uid=?";
        Integer totalCount = jt.queryForObject(sql, Integer.class,uid);
        return totalCount;
    }
}
