package cn.itcast.travel.web.servlet;

import cn.itcast.service.CategoryServiceDao;
import cn.itcast.service.impl.CategoryServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryServiceDao categoryServiceDaoImpl=new CategoryServiceDaoImpl();

    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String json = categoryServiceDaoImpl.findAll();
        response.getWriter().print(json);

    }


}
