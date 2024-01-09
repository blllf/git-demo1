package com.longfei.web.servlet;

import com.longfei.pojo.People;
import com.longfei.service.UserService;
import com.longfei.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
