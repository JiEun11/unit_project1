package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BoardDAO;
import model.vo.BoardVO;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String detail_btn = request.getParameter("detail_btn");
		BoardDAO dao = new BoardDAO();

		if (detail_btn == null) {

			String num = request.getParameter("num");
			BoardVO vo = dao.detailView(Integer.parseInt(num));

			request.setAttribute("detailPage", vo);

			request.getRequestDispatcher("/jsp/BoardDetail.jsp").forward(request, response);
			
		} else if (detail_btn.equals("list")) {
			request.getRequestDispatcher("/board").forward(request, response);
			
		} else if(detail_btn.equals("create")) {
			
			
		} else {
			//BoardVO vo = (BoardVO)request.getAttribute("detail");
			HttpSession session = request.getSession();
			BoardVO vo = (BoardVO)session.getAttribute("detail");
			
			
			if (detail_btn.equals("update")) {
				request.setAttribute("updatePage", vo);
				session.removeAttribute("detail");
				request.getRequestDispatcher("/jsp/updateBoard.jsp").forward(request, response);
			} else if (detail_btn.equals("delete")) {
				dao.delete(vo.getNum());
				session.removeAttribute("detail");
				request.getRequestDispatcher("/board").forward(request, response);
				System.out.println("delete");
			}
			
		}

	}

}
