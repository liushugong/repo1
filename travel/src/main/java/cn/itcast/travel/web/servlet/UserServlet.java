package cn.itcast.travel.web.servlet;

import cn.itcast.service.UserServiceDao;
import cn.itcast.service.impl.UserServiceDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserServiceDao userServiceDaoImpl = new UserServiceDaoImpl();

    /**注册功能
     * @Description:
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/13
     * @Time:
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String check = request.getParameter("check");
        String checkCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        if (check != null && !check.equalsIgnoreCase(checkCode)) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            String json = mapper.writeValueAsString(resultInfo);
            response.getWriter().print(json);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        boolean b = userServiceDaoImpl.registUser(user);

        if (b) {
            resultInfo.setFlag(true);
            String content = "<a href='http://192.168.43.121/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("该用户名太受欢迎，请换一个");
        }
        String json = mapper.writeValueAsString(resultInfo);
        response.getWriter().print(json);
    }

    /**
     * @Description:注销功能
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/13
     * @Time:
     */
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * @Description:登录功能
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/13
     * @Time:
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        String check = request.getParameter("check");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (check != null && !check.equalsIgnoreCase(checkcode_server)) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            String json = mapper.writeValueAsString(resultInfo);
            response.getWriter().print(json);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userServiceDaoImpl.login(username, password);

        if (user == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
            String json = mapper.writeValueAsString(resultInfo);
            response.getWriter().print(json);
        } else if (user != null && user.getStatus().equalsIgnoreCase("N")) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("该用户未激活，请先登录注册邮箱激活");
            String json = mapper.writeValueAsString(resultInfo);
            response.getWriter().print(json);
        } else if (user != null && user.getStatus().equalsIgnoreCase("Y")) {
            resultInfo.setFlag(true);

            request.getSession().setAttribute("user", user);
            String json = mapper.writeValueAsString(resultInfo);
            response.getWriter().print(json);
        }
    }

    /**
     * @Description:查找单个用户
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/13
     * @Time:
     */
    protected void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        User user = (User) request.getSession().getAttribute("user");


        if (user == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("未登录，请先登录");
        } else {
            resultInfo.setFlag(true);
            resultInfo.setData(user);
        }
        String json = mapper.writeValueAsString(resultInfo);
        response.getWriter().print(json);
    }

    /**
     * @Description:激活功能
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/13
     * @Time:
     */
    protected void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        if (code != null) {
            boolean b = userServiceDaoImpl.active(code);
            String msg = null;
            if (b) {
                msg = "激活成功请<a href='login.html'>登陆<a>";
            } else {
                msg = "激活失败，请联系管理员";
            }
            response.getWriter().print(msg);
        }
    }


}
