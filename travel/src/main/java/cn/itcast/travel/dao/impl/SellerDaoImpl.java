package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Liu Shugong
 * @description SellerDaoImpl
 * @date 2019/5/15
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findById(Integer sid) {
        Seller seller=null;
        try {
            String sql="select * from tab_seller where sid=?";
            seller = jt.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
        } catch (DataAccessException e) {

        }
        return seller;
    }
}
