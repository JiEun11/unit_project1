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
import model.vo.PageVO;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String detail_btn = request.getParameter("detail_btn");
		BoardDAO dao = new BoardDAO();
		String boardPath = null;
		String msg = null;
		
		PageVO voPage = new PageVO();
		
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
			msg = "로그인을 해주세요";
			boardPath = "/jsp/BoardMain.jsp";
			request.setAttribute("msg", msg);
			
//			voPage.setCount(dao.pageCnt(voPage.getPageDivide(), "null", ""));
//			voPage.setWhereParam("null");
//			voPage.setKeyword("");
//			session.setAttribute("page", voPage);
			request.setAttribute("list", dao.pagenation("null", "", 0, voPage.getPageDivide()));
		}
		
		request.getRequestDispatcher(boardPath).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
}
