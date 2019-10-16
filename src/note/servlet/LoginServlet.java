package note.servlet;

import note.daoImpl.PersonDAOImpl;
import note.factory.DAOFactory;
import note.vo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet.do"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //接收用户名和密码
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String flag = request.getParameter("flag");
        Person person = new Person();
        person.setUserName(username);
        person.setUserPassword(password);
        person.setFlag(flag);
        //取出已存入session中的验证码
        HttpSession session = request.getSession();
        String rand = (String) session.getAttribute("rand");
        String input = request.getParameter("verify");

        try {
            if (DAOFactory.getPersonDAOInstance().login(person) && rand.equals(input)) {
                System.out.println("judge login 123");
                session.setAttribute("name",person.getUserName());
                session.setAttribute("image",person.getImage());
                session.setAttribute("id",person.getUserId());
                if (person.getActive().equals("1")){
                    request.getRequestDispatcher("main.jsp");
                }else {
                    request.getRequestDispatcher("err.jsp");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
