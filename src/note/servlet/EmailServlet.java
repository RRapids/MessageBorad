package note.servlet;

import note.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EmailServlet", urlPatterns = {"/email"})
public class EmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String active = request.getParameter("active");
        out.println("<html>");
        out.println("<body><center><h3>");
        try {//检查用户是否存在
            if (DAOFactory.getPersonDAOInstance().checkUserById(id)) {
                if (DAOFactory.getPersonDAOInstance().checkPersonById(id).getActive().equals(0)) {
                    DAOFactory.getPersonDAOInstance().update(id);
                    out.println("账号激活成功！！<a href ='login.html'>返回登录</a>");
                }else {
                    out.println("账号已激活！连接失败<a href ='login.html'>返回登录</a>");
                }
            }
            else {
                out.println("账号激活失败！<a href='register.jsp'>请重新注册</a>");
            }
            out.println("</h3></center></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
