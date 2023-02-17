package com.itcyt.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itcyt.pojo.Brand;
import com.itcyt.pojo.PageBean;
import com.itcyt.service.OrderService;
import com.itcyt.service.UserService;
import com.itcyt.service.impl.OrderServiceImpl;
import com.itcyt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 订单Servlet
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = orderService.selectAll();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(result);
    }
//
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        orderService.add(params);
        response.getWriter().write("success");
    }
//
    public void editById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        orderService.editById(params);
        response.getWriter().write("success");
    }
//
////    /**
////     * 批量删除
////     * @param request
////     * @param response
////     * @throws ServletException
////     * @throws IOException
////     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

//        转为int[]
        String[] ids = JSON.parseObject(params, String[].class);
        orderService.deleteByIds(ids);
        response.getWriter().write("success");
    }
//
////    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//////        接收当前页码与每页展示条数参数  url?currentPage=1&pageSize=5
////        String _currentPage = request.getParameter("currentPage");
////        String _pageSize = request.getParameter("pageSize");
////        int currentPage = Integer.parseInt(_currentPage);
////        int pageSize = Integer.parseInt(_pageSize);
////        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
////
////        String jsonString = JSON.toJSONString(pageBean);
////        response.setContentType("text/json;charset=utf-8");
////        response.getWriter().write(jsonString);
////    }
////
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收当前页码与每页展示条数参数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        PageBean<Brand> pageBean = orderService.selectByPageAndCondition(currentPage, pageSize,params);

//        System.out.println("rows=>" + pageBean.getRows());
//        System.out.println("total=>" + pageBean.getTotalCount());

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
//
    /**
     * 删除单条数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id = request.getParameter("id");
        orderService.deleteById(_id);
        response.getWriter().write("success");
    }

}