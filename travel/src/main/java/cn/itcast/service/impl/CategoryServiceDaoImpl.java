package cn.itcast.service.impl;

import cn.itcast.service.CategoryServiceDao;
import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Liu Shugong
 * @description CategoryServiceDaoImpl
 * @date 2019/5/13
 */
public class CategoryServiceDaoImpl implements CategoryServiceDao {
    private CategoryDao categoryDaoImpl = new CategoryDaoImpl();
/**
*@Description:查询所有分类信息
*@Param:
*@return:
*@Author:liu shu gong
*@Date:2019/5/14
*@Time:
*/
    @Override
    public String findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categorySort = jedis.zrangeWithScores("categorySort", 0, -1);

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        List<Category> categoryList = null;
        if (categorySort == null || categorySort.size() == 0) {
            categoryList = categoryDaoImpl.findAll();

            for (Category category : categoryList) {
                jedis.zadd("categorySort", category.getCid(), category.getCname());
            }
            try {
                json = mapper.writeValueAsString(categoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } else {
            categoryList = new ArrayList<Category>();
            for (Tuple tuple : categorySort) {
                Category category = new Category();
                category.setCid((int) tuple.getScore());
                category.setCname(tuple.getElement());
                categoryList.add(category);
            }
            try {
                json = mapper.writeValueAsString(categoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return json;
    }
}
