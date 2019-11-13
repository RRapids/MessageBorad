package note.filter;

import java.io.* ;

import javax.servlet.* ;

public class CharFilter implements Filter
{
	public void init(FilterConfig filterConfig)
			throws ServletException
	{
		// System.out.println("** 过滤器初始化...") ;
	}
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
			throws IOException,ServletException
	{
		request.setCharacterEncoding("UTF-8");//不加这条代码会导致汉字存入数据库是乱码
		String content=request.getParameter("content");//System.out.println(content);
		// 如果indexOf返回-1则表示没有查到所要的内容
		if(content!=null)
		{
			if(content.indexOf("晕")==-1)
			{
				chain.doFilter(request,response) ;
			}
			else
			{
				System.out.println("有非法文字") ;
				response.setCharacterEncoding("UTF-8");
				PrintWriter out=response.getWriter();
				out.print("<script language=javascript>alert('有非法文字！');window.location.href='insert_error.jsp';</script>");

				// 如果需要的话，此处可以使用RequestDispatcher进行跳转
				//request.getRequestDispatcher("insert_error.jsp").forward(request,response) ;
			}
		}
		else
		{
			chain.doFilter(request,response) ;
		}

	}
	public void destroy()
	{
		// System.out.println("** 过滤器销毁...") ;
	}
};
