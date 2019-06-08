package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {
    /**
    *@Description:根据路线查询商家信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/15
    *@Time:
    */
    public abstract Seller findById(Integer sid);
}
