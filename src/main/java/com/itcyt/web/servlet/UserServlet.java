package com.itcyt.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itcyt.pojo.Brand;
import com.itcyt.pojo.PageBean;
import com.itcyt.service.BrandService;
import com.itcyt.service.UserService;
import com.itcyt.service.impl.BrandServiceImpl;
import com.itcyt.service.impl.UserServiceImpl;
import com.wechatConnection.query2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户好友信息管理Servlet
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = userService.selectAll();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(result);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        userService.add(params);
        response.getWriter().write("success");
    }

    public void editById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        userService.editById(params);
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

//        转为int[]
        String[] ids = JSON.parseObject(params, String[].class);
        userService.deleteByIds(ids);
        response.getWriter().write("success");
    }

//    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        接收当前页码与每页展示条数参数  url?currentPage=1&pageSize=5
//        String _currentPage = request.getParameter("currentPage");
//        String _pageSize = request.getParameter("pageSize");
//        int currentPage = Integer.parseInt(_currentPage);
//        int pageSize = Integer.parseInt(_pageSize);
//        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
//
//        String jsonString = JSON.toJSONString(pageBean);
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }
//
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收当前页码与每页展示条数参数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        PageBean<Brand> pageBean = userService.selectByPageAndCondition(currentPage, pageSize,params);

//        System.out.println("rows=>" + pageBean.getRows());
//        System.out.println("total=>" + pageBean.getTotalCount());

        String jsonString = JSON.toJSONString(pageBean);
//        System.out.println("jsonString=> ");
//        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 删除单条数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id = request.getParameter("id");
        userService.deleteById(_id);
        response.getWriter().write("success");
    }

}