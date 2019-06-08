package cn.itcast.service;

import cn.itcast.travel.domain.User;

public interface UserServiceDao {
    /**
    *@Description:注册用户功能
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract boolean registUser(User user);
    /**
    *@Description:激活功能
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract boolean active(String code);
    /**
    *@Description:登录功能
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */
    public abstract User login(String username,String password);
}
