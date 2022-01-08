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
		
		String boardPath = null;
		
		if (detail_btn == null) {

			String num = request.getParameter("num");
			BoardVO vo = dao.detailView(Integer.parseInt(num));
			dao.cntIncrease(vo);
			vo.setCnt(vo.getCnt()+1);
			request.setAttribute("detailPage", vo);
			boardPath = "/jsp/BoardDetail.jsp";
			
		} else if (detail_btn.equals("list")) {
			boardPath = "/board";
			
		} else {
			//BoardVO vo = (BoardVO)request.getAttribute("detail");
			
			HttpSession session = request.getSession();
			BoardVO vo = (BoardVO)session.getAttribute("detail");
			
			if (detail_btn.equals("update")) {
				request.setAttribute("updatePage", vo);
				session.removeAttribute("detail");
				boardPath = "/jsp/UpdateBoard.jsp";
				
			} else if (detail_btn.equals("delete")) {
				dao.delete(vo.getNum());
				session.removeAttribute("detail");
				boardPath = "/board";
			}
			else if(detail_btn.equals("write")) {
				
				boardPath = "/jsp/WriteBoard.jsp";
			}
			
		}
		
		request.getRequestDispatcher(boardPath).forward(request, response);
		
	}
}
