package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
    *@Description:查询所有分类信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract List<Category>findAll();
}
