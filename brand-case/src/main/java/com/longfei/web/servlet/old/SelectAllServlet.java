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
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service 查询
        List<Brand> brands = brandService.selectAll();

        //转为json
        String jsonString = JSON.toJSONString(brands);

        // 响应成功标识
        response.setContentType("text/json;charset=utf-8");

        response.getWriter().write(jsonString);


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
