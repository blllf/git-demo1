package com.longfei.web.servlet;

import com.longfei.pojo.People;
import com.longfei.service.UserService;
import com.longfei.service.impl.UserServiceImpl;
import com.longfei.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/selectUserServlet")
public class SelectUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        People people = new People();
        people.setUsername(username);


        System.out.println("people" + username);

        boolean flag = userService.verify(people);


        if (flag) {
            response.getWriter().write("" + flag);
        } else {
            response.getWriter().write("" + flag);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}