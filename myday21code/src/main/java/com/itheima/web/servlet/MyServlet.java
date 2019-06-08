package com.itheima.web.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/MyServlet")
public class MyServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
       /* response.sendRedirect(request.getContextPath()+"/index.jsp");*/
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
