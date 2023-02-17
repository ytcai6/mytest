package com.itcyt.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itcyt.pojo.Admin;
import com.itcyt.service.AdminService;
import com.itcyt.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 后台管理员Servlet
 */
@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet {
    private AdminService adminService=new AdminServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("testing login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminService.login(username, password);
        if (admin != null) {
            String jsonString = JSON.toJSONString(admin);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
//            response.getWriter().write("success");
        } else {
//            response.sendRedirect("/brand_case_war/login.html");
            response.getWriter().write("fail");
        }

//        BufferedReader reader = request.getReader();
//        String params = reader.readLine();
//        System.out.println("params=>"+params);
//        String password = JSON.parseObject(params, String.class);
//        System.out.println("password=>"+password);
//        brandService.add(brand);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Admin admin = JSON.parseObject(params, Admin.class);
        adminService.register(admin);
        response.getWriter().write("success");
    }

}