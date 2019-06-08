package cn.itcast.service.impl;

import cn.itcast.service.UserServiceDao;
import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;

/**
 * @author Liu Shugong
 * @description UserServiceDaoImpl
 * @date 2019/5/12
 */
public class UserServiceDaoImpl implements UserServiceDao {
    private UserDao userDaoImpl = new UserDaoImpl();

    @Override
    public boolean registUser(User user) {
        String username = user.getUsername();
        User byUsername = userDaoImpl.findByUsername(username);
        if (byUsername != null) {
            return false;
        } else {
            userDaoImpl.save(user);
            return true;
        }
    }

    @Override
    public boolean active(String code) {
        int row = userDaoImpl.updateStatus(code, "Y");
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(String username, String password) {
        User user = userDaoImpl.findByUsernameAndPassword(username, password);
        return user;
    }
}
