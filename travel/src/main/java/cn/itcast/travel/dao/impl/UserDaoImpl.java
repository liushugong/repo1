package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Liu Shugong
 * @description UserDaoImpl
 * @date 2019/5/12
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @Description:根据用户名查询用户信息
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/14
     * @Time:
     */
    @Override
    public User findByUsername(String username) {
        User user = null;

        try {
            String sql = "select * from tab_user where username=?";
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {

        }
        return user;
    }
    /**
    *@Description:存储用户信息
    *@Param:
    *@return:
    *@Author:liu shu gong
    *@Date:2019/5/14
    *@Time:
    */

    @Override
    public void save(User user) {
        String sql = "insert into tab_user (uid,username,password,name,birthday,sex,telephone,email,status,code) values (?,?,?,?,?,?,?,?,?,?)";
        int i = jt.update(sql, null, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
    }
/**
*@Description:更新用户激活状态
*@Param:
*@return:
*@Author:liu shu gong
*@Date:2019/5/14
*@Time:
*/
    @Override
    public int updateStatus(String code, String status) {
        String sql = "update tab_user set status=? where code=?";
        int update = jt.update(sql, status, code);
        return update;

    }
/**
*@Description:根据用户名和密码查询用户信息
*@Param:
*@return:
*@Author:liu shu gong
*@Date:2019/5/14
*@Time:
*/
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username=? and password=?";
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {

        }
        return user;
    }


}
