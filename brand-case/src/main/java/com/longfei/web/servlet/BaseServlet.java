package com.longfei.web.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
* 替换HTTPServlet,根据请求的最后一段路径进行分发
* */
public class BaseServlet extends HttpServlet {


    //根据请求的最后一段路径进行分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.
        String uri = req.getRequestURI(); // brand-case/brand/selectAll
        //2. 获取最后一段路径
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);
       // System.out.println(methodName);

        //2. 执行方法
        //2.1 获取BrandServlet 字节码对象 class


        // 谁调用我（this 所在的方法）， 我（this）代表谁
        //这里this 代表BaseServlet的子类们
        //System.out.println(this);  // BrandServlet ? UserServlet

        Class<? extends BaseServlet> cls = this.getClass();

        // 2.2 获取方法method 对象

        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
           //2.3 执行方法
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }





    }
}
