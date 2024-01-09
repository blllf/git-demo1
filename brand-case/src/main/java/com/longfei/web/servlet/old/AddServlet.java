package com.longfei.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.longfei.pojo.Brand;
import com.longfei.service.BrandService;
import com.longfei.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.getParameter  接收不了json数据
        //  request.getParameter()

        // 获取请求体方法
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //将json数据转化为java对象
        Brand brand = JSON.parseObject(params, Brand.class);
        /*System.out.println(brand);*/

        //调用service 添加
        brandService.add(brand);

        // 响应成功标识
        response.getWriter().write("success");



    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
