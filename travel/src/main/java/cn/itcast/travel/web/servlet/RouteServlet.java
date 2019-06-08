package cn.itcast.travel.web.servlet;

import cn.itcast.service.FavoriteServiceDao;
import cn.itcast.service.RouteServiceDao;
import cn.itcast.service.impl.FavoriteServiceDaoImpl;
import cn.itcast.service.impl.RouteServiceDaoImpl;
import cn.itcast.travel.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    /**
     * @Description:分页展示线路信息
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/14
     * @Time:
     */
    private RouteServiceDao routeServiceDaoImpl = new RouteServiceDaoImpl();
    private FavoriteServiceDao favoriteServiceDaoImpl = new FavoriteServiceDaoImpl();

    protected void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String rname = request.getParameter("rname");

        //rname=new String(rname.getBytes("iso-8859-1"),"utf-8");


        String cidStr = request.getParameter("cid");
        Integer cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equalsIgnoreCase(cidStr)) {

            cid = Integer.parseInt(cidStr);
        }
        Integer currentPage;
        if (currentPageStr == null || currentPageStr.length() == 0 || "null".equalsIgnoreCase(currentPageStr)) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPageStr);
        }
        Integer pageSize = 10;
        if (pageSizeStr == null || pageSizeStr.length() == 0) {
            pageSize = 5;
        }
        PageBean<Route> pb = routeServiceDaoImpl.getPageBean(cid, currentPage, pageSize, rname);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pb);
        response.getWriter().print(json);
    }

    /**
     * @Description:查询线路详情
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/16
     * @Time:
     */
    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收id
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String rid = request.getParameter("rid");
        //2.调用service查询route对象
        Route route = routeServiceDaoImpl.findById(rid);
        //3.转为json写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(route);
        response.getWriter().print(json);
    }

    /**
     * @Description:判断线路是否被收藏
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/16
     * @Time:
     */
    protected void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();
        Integer uid = 0;
        if (user != null) {
            uid = user.getUid();
        }

        Favorite favorite = favoriteServiceDaoImpl.isFavorite(rid, uid);

        if (favorite == null) {
            resultInfo.setFlag(true);
            resultInfo.setData(favorite);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("该线路信息已被收藏");
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        response.getWriter().print(json);
    }

    /**
     * @Description:收藏线路
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/16
     * @Time:
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return;
        }

        favoriteServiceDaoImpl.add(rid, user.getUid());
    }

    /**
     * @Description:"我的收藏中的线路信息”
     * @Param:
     * @return:
     * @Author:liu shu gong
     * @Date:2019/5/17
     * @Time:
     */
    protected void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        if ("null"==currentPageStr||"".equals(currentPageStr) || currentPageStr == null) {
            currentPageStr = "1";
        }
        int currentPage = Integer.parseInt(currentPageStr);
        if ("null"==pageSizeStr||"".equals(pageSizeStr) || pageSizeStr == null) {
            pageSizeStr = "12";
        }
        int pageSize = Integer.parseInt(pageSizeStr);
        User user =(User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        PageBean<Route> pb = favoriteServiceDaoImpl.myFavorite(currentPage, pageSize,uid);
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(pb);
        response.getWriter().print(json);
    }
}
