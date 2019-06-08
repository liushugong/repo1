package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Shugong
 * @description RouteDaoImpl
 * @date 2019/5/14
 */
public class RouteDaoImpl implements RouteDao {
    /**
     * @Description:查询所有线路数目
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/14
     * @Time:
     */
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Integer findTotalCount(Integer cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        ArrayList<Object> paraList = new ArrayList<>();
        if (cid != 0) {
            sb.append(" and cid= ?");
            paraList.add(cid);
        }
        if (rname != null && !"".equals(rname)&&!"null".equals(rname)) {
            sb.append(" and rname like ?");
            paraList.add("%"+rname+"%");
        }
        sql = sb.toString();
        Integer totalCount = jt.queryForObject(sql, Integer.class, paraList.toArray());
        return totalCount;
    }

    /**
     * @Description:分页查询具体线路
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/14
     * @Time:
     */
    @Override
    public List<Route> findByPage(Integer cid, Integer start, Integer pageSize, String rname) {
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        ArrayList<Object> paraList = new ArrayList<>();

        System.out.println(cid);
        if (cid != 0) {
            sb.append(" and cid= ?");
            paraList.add(cid);
        }

        if (rname != null && !"".equals(rname)&&!"null".equals(rname)) {
            sb.append(" and rname like ?");
            paraList.add("%"+rname+"%");
        }
        sb.append(" limit ?,?");
        paraList.add(start);
        paraList.add(pageSize);
        sql = sb.toString();
        List<Route> routeList = jt.query(sql, new BeanPropertyRowMapper<Route>(Route.class),paraList.toArray());
        return routeList;
    }

    /**
    *@Description:根据线路id查询线路详情
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/15
    *@Time:
    */
    @Override
    public Route findById(Integer rid) {
        Route route = null;
        try {
            String sql="select * from tab_route where rid=?";
            route = jt.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        } catch (DataAccessException e) {
        }
        return route;
    }
}
