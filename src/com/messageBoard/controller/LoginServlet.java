package com.messageBoard.controller;

import com.messageBoard.model.UserDAOSQLServletImpl;
import com.messageBoard.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession hs = request.getSession(true);
        //接受用户
//        String userName = request.getParameter("userName");
        String userCount = request.getParameter("userCount");
        String userPassword = request.getParameter("userPassword");
        Users user = new Users();
//        user.setUserName(userName);
        user.setUserCount(userCount);
        user.setUserPassword(userPassword);
        UserDAOSQLServletImpl userDAOSQLServlet = new UserDAOSQLServletImpl();
        if (userDAOSQLServlet.findUser(user)) {
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
