package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BoardDAO;
import model.vo.BoardVO;
//import model.vo.MembersVO;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String detail_btn = request.getParameter("detail_btn");
		BoardDAO dao = new BoardDAO();
		
		String boardPath = null;
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null) {
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
				
				BoardVO vo = (BoardVO)session.getAttribute("detail");
				
				if (detail_btn.equals("update")) {
					request.setAttribute("updatePage", vo);
					
					boardPath = "/jsp/UpdateBoard.jsp";
				} else if (detail_btn.equals("delete")) {
					dao.delete(vo.getNum());
					
					boardPath = "/board";
				}
				else if(detail_btn.equals("write")) {
					
					boardPath = "/jsp/WriteBoard.jsp";
				}
				
				session.removeAttribute("detail");
			}
		}
		else {
			System.out.println("로그인을 해주세요");
			boardPath = "/board";
		}
		
		request.getRequestDispatcher(boardPath).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String boardPath = "/bbs/board";
		BoardDAO dao = new BoardDAO();
		
		// WriteBoard (writer, title, content)
		PrintWriter out = response.getWriter();
		String b_title = request.getParameter("b_title");
		String b_writer = request.getParameter("b_writer");
		String b_content = request.getParameter("b_content");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(b_title);
		boardVO.setWriter(b_writer);
		boardVO.setContent(b_content);
		
		dao.insert(boardVO);

		response.sendRedirect(boardPath);
		
	}
}
