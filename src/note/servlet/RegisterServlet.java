package note.servlet;

import note.factory.DAOFactory;
import note.util.Mail;
import note.vo.Person;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.do"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body><center><h3>");
        String userimage = request.getParameter("image");
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String useremail = request.getParameter("email");
        String flag = request.getParameter("flag");
        Person person = new Person();
        person.setImage(userimage);
        person.setUserName(username);
        person.setUserPassword(password);
        person.setEmail(useremail);
        person.setFlag(flag);
        person.setActive("0");

        try {
            if (!DAOFactory.getPersonDAOInstance().checkUser(person)) {
                DAOFactory.getPersonDAOInstance().insert(person);
                person.setUserId(DAOFactory.getPersonDAOInstance().QueryId(person));
                new Mail(person);
                int i = useremail.indexOf("@");
                out.println("注册成功!请到邮箱激活");
                out.println("<a href='https://mail." + useremail.substring(i + 1) + "'>请进入注册邮箱激活账号</a>");
            } else {
                out.println("注册失败！<a href='register.jsp'>请重新注册</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</h3></center></body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
