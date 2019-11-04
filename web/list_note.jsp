<%@ page import="note.daoImpl.NoteDAOImpl" %>
<%@ page import="note.dao.NoteDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="note.vo.Note" %>
<%@ page import="java.awt.*" %>
<%@ page import="note.util.SplitPage" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: 79876
  Date: 2019/10/23
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="images/else/bg2.jpg" align="top" height="100" width="1920" alt="欢迎进入留言板">
<center><br>
    <a href="uersinfo.jsp"><img src="<%=session.getAttribute("image")%>"></a>
    &nbsp;&nbsp;&nbsp;JSP学习论坛留言板<a href="exit.jsp">退出登录</a>
    <hr>
    <a href="list_note.jsp?flag=first">显示所有留言</a>
    <hr>

    <form action="#">
        <table>
            <tr>
                <td>在</td>
                <td>
                    <select name="item">
                        <%--选项--%>
                        <option value="title">标题</option>
                        <option value="actor">作者</option>
                        <option value="content">内容</option>
                    </select>中查询
                    <input type="text" name="content">
                    <input type="submit" value="查询">
                </td>
            </tr>
        </table>
    </form>

    <h3>
        <a href="insert.jsp">添加新留言</a>
    </h3>
    <%
        //如果用户登录
        if (session.getAttribute("name") != null) {
    %>
    <jsp:useBean id="spage" class="note.util.SplitPage" scope="session">

    </jsp:useBean>
    <%
        NoteDAO note = new NoteDAOImpl();
        String flag = request.getParameter("flag");
        int toatalRows = 0;

    %>

    <table width="80%" border="1" cellpadding="0" align="center">
        <tr bgcolor="#6495ed">
            <td>留言ID</td>
            <td>标题</td>
            <td>作者</td>
            <td>内容</td>
            <td>删除</td>
        </tr>
        <%
            request.setCharacterEncoding("UTF-8");
            String strItem = request.getParameter("item");
            String strContent = request.getParameter("content");
            List<Note> list = null;
            HashMap<String, String> tm = new HashMap<>();
            //判断是否使用了搜索
            if (strContent == null || strContent == "" || strContent.equals("null")) {
                toatalRows = note.getRows(tm);//总的记录数
                spage.setTotalRows(toatalRows);
                //重新计算确定当前要显示的页面值，实现翻页
                spage.confirmPage(flag);

            }

            list = note.findAll(spage);
            for (Note n : list) {%>
        <tr>
            <td><%=n.getId()%>
            </td>
            <td><%=n.getTitle()%>
            </td>
            <td><%=n.getAuthor()%>
            </td>
            <td><%=n.getContent()%>
            </td>
            <td>
                <%
                    if (session.getAttribute("name").equals(n.getAuthor())) {

                    }
                %>
                <a href="delete?id=<%=n.getId()%>">删除</a>
            </td>
        </tr>

        <%}%>

        <tr>
            <td colspan="5" align="right">
                <a href="list_note.jsp?flag=<%=SplitPage.FirstPAGE%>&item=<%=strItem%>&content=<%=strContent%>">首页 </a>
                <a href="list_note.jsp?flag=<%=SplitPage.PreviousPAGE%>&item=<%=strItem%>&content=<%=strContent%>">上一页 </a>
                <a href="list_note.jsp?flag=<%=SplitPage.NextPAGE%>&item=<%=strItem%>&content=<%=strContent%>">下一页 </a>
                <a href="list_note.jsp?flag=<%=SplitPage.LastPAGE%>&item=<%=strItem%>&content=<%=strContent%>">尾页</a>
            </td>
        </tr>

    </table>

    <%
        } else {
            out.println("不是合法用户！2s后转到登录页面");
            //用户未登录
            response.setHeader("refresh", "2;URL=login.html");
        }
    %>

</center>
</body>
</html>
