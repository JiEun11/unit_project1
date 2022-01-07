package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.vo.BoardVO;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String num = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.detailView(Integer.parseInt(num));
		
		request.setAttribute("detailPage", vo);
		
		request.getRequestDispatcher("/jsp/BoardDetail.jsp").forward(request, response);
		out.close();
	}
	
}
