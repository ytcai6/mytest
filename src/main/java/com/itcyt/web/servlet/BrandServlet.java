package com.itcyt.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itcyt.pojo.Brand;
import com.itcyt.pojo.PageBean;
import com.itcyt.service.BrandService;
import com.itcyt.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * 测试功能
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService=new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();
        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }

    public void editById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService.updateById(brand);
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
        int[] ids = JSON.parseObject(params, int[].class);

        brandService.deleteByIds(ids);

        response.getWriter().write("success");
    }


    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收当前页码与每页展示条数参数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收当前页码与每页展示条数参数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        System.out.println("rows=>" + pageBean.getRows());
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
        int id = Integer.parseInt(_id);
        brandService.deleteById(id);
        response.getWriter().write("success");
    }

}