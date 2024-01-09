package com.longfei.web.servlet;


import com.alibaba.fastjson.JSON;
import com.longfei.pojo.Brand;
import com.longfei.pojo.PageBean;
import com.longfei.service.BrandService;
import com.longfei.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service 查询
        List<Brand> brands = brandService.selectAll();

        //转为json
        String jsonString = JSON.toJSONString(brands);

        // 响应成功标识
        response.setContentType("text/json;charset=utf-8");

        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


    // delete by id
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收id
        String id = request.getParameter("id");
        //调用方法删除
        brandService.deleteById(Integer.parseInt(id));
        //响应成功的标识
        response.getWriter().write("success");
    }

    /*
    * 批量删除
    * */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接收数据[1,2,3]
        // 获取请求体方法
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //调用service 添加
        brandService.deleteByIds(ids);

        // 响应成功标识
        response.getWriter().write("success");
    }

    /*
    * 分页查询
    * */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收参数  当前页码 和 每页展示数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //调用service查询

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //转为json
        String jsonString = JSON.toJSONString(pageBean);

        // 响应成功标识
        response.setContentType("text/json;charset=utf-8");

        response.getWriter().write(jsonString);
    }

    /*
     * 分页条件查询
     * */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收参数  当前页码 和 每页展示数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象

        // 获取请求体方法
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //将json数据转化为Brand(java)对象
        Brand brand = JSON.parseObject(params, Brand.class);




        //调用service查询

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //转为json
        String jsonString = JSON.toJSONString(pageBean);

        // 响应成功标识
        response.setContentType("text/json;charset=utf-8");

        response.getWriter().write(jsonString);
    }


    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用方法修改
        brandService.update(brand);
        //响应成功的标识
        response.getWriter().write("success");
    }




}
