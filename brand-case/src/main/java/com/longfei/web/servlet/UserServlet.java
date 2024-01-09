package com.longfei.web.servlet;


import com.longfei.pojo.People;
import com.longfei.service.BrandService;
import com.longfei.service.UserService;
import com.longfei.service.impl.BrandServiceImpl;
import com.longfei.service.impl.UserServiceImpl;
import com.longfei.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();


    public void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        People people = userService.selectByName(username, password);

        if(people != null){

            if ("1".equals(remember)){

                Cookie c_username = new Cookie("username" , username);
                Cookie c_password = new Cookie("password" , password);

                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);

                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            HttpSession session = request.getSession();
            session.setAttribute("people", people);



            response.sendRedirect("/brand-case/brand.html");

            /*String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/selectAllServlet");*/
        }else {
            request.setAttribute("login_msg" , "用户名或密码不正确");
            /*response.sendRedirect("http://localhost:8080/brand-case/login.jsp");*/
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }

    }



    public void selectUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        People people = new People();
        people.setUsername(username);


        System.out.println("people"+username);

        boolean flag = userService.verify(people);


        if (flag){
            response.getWriter().write(""+flag);
        }else {
            response.getWriter().write(""+flag);
        }
    }

    public void registerServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        People people = new People();
        people.setUsername(username);
        people.setPassword(password);
        // 获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");

        //获取程序生成的验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        if (!checkCodeGen.equalsIgnoreCase(checkCode)){

            request.setAttribute("captcha_msg" , "验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);

            // 不允许注册
            return;
        }

        //调用service注册
        boolean flag = userService.register(people);
        if (flag){
            request.setAttribute("register_msg" , "注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            request.setAttribute("register_msg","用户名已存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }


    public void checkCodeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //生成验证码
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);

        //存入session中
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen",checkCode);
    }


    /*public void loginFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user add....");
    }*/
}
