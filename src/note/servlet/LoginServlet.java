package note.servlet;

import note.daoImpl.PersonDAOImpl;
import note.vo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login.do"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession hs = request.getSession(true);
        //接收用户名和密码
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        Person person = new Person();
        person.setUserName(username);
        person.setUserPassword(password);
        PersonDAOImpl persondao = new PersonDAOImpl();
        out.println("账户名和密码是："+username+password);
        try {
            if (persondao.login(person)) {
                request.getRequestDispatcher("main.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
