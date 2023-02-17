package com.itcyt.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.itcyt.pojo.Brand;
import com.itcyt.service.BrandService;
import com.itcyt.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService=new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        Brand brand = JSON.parseObject(params, Brand.class);

        brandService.add(brand);

        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}