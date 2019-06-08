package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
    *@Description:根据用户名查询用户信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract User findByUsername(String username);
    
    /**
    *@Description:存储用户信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract void save(User user);
    /**
    *@Description:更新激活状态
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract int updateStatus(String code,String status);
    /**
    *@Description:根据用户名和密码查询单个用户信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract User findByUsernameAndPassword(String username,String password);
}
