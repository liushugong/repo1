package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Liu Shugong
 * @description CategoryDaoImpl
 * @date 2019/5/13
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        /**
        *@Description:查询所有分类信息
        *@Param:[]
        *@return:java.util.List<cn.itcast.travel.domain.Category>
        *@Author:liu shu gong
        *@Date:2019/5/14
        *@Time:
        */
        String sql = "select * from tab_category order by cid ASC ";
        List<Category> categoryList = jt.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return categoryList;
    }
}
