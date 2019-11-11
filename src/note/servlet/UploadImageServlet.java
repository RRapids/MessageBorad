package note.servlet;

import com.jspsmart.upload.SmartUpload;
import note.factory.DAOFactory;
import note.vo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UploadImageServlet", urlPatterns = "/uploadImage.do")
public class UploadImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        JspFactory fac = JspFactory.getDefaultFactory();
        PageContext pageContext = fac.getPageContext(this, req, resp, null, false, JspWriter.NO_BUFFER, true);
        com.jspsmart.upload.File file = null;
        int id = (int) session.getAttribute("id");
        out.println("<html>");
        out.println("<body><center><h3>");
        //System.out.println("<%=id%>"+id);
        //新建上传对象
        SmartUpload su = new SmartUpload();
        // 上传初始化
        su.initialize(pageContext);
        // 设定上传限制
        // 限制每个上传文件的最大长度。
        su.setMaxFileSize(20000);
        // 限制总上传数据的长度。
        // su.setTotalMaxFileSize(20000);
        //通过扩展名限制设定允许上传的文件,这里仅允许doc,txt文件。
        su.setAllowedFilesList("gif,bmp,jpg");
        // 通过扩展名限制设定禁止上传的文件,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。
        // su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
        // 实现文件上传
        try {
            su.upload();

            // 将上传文件全部保存到指定目录,必须保证upload目录在应用程序根文件夹中存在
            su.save("/images/face", su.SAVE_VIRTUAL);
            //提示成功上传文件数量
            //out.println(count+"个文件上传成功！<br>");
            // 逐一提取上传文件信息，同时可保存文件。
            for (int i = 0; i < su.getFiles().getCount(); i++) {
                file = su.getFiles().getFile(i);
                // 若文件不存在则继续
                if (file.isMissing()) continue;
                // 显示当前文件信息
                //out.println("<br>文件长度" + file.getSize());
                //out.print ln("<br>文件名：" + file.getFileName()+"　长度："+file.getSize());
            }
            Person person = new Person();
            person.setUserId(id);
            System.out.println("file name" + file.getFileName());
            person.setImage("images/face/" + file.getFileName());
            DAOFactory.getPersonDAOInstance().update(person);
            session.setAttribute("image", person.getImage());
            out.print("<script language=javascript>alert('头像上传成功！！！');" +
                    "window.location.href='list_note.jsp';</script>");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.print("<script language=javascript>alert('头像上传失败！！！请检查文件格式和大小！');" +
                    "window.location.href='uersinfo.jsp';</script>");
        }
        out.println("</h3></center></body></html>");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
