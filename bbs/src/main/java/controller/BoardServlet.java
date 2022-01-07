package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.Board;
import model.dao.BoardDAO;
import model.vo.BoardVO;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String num = request.getParameter("num");
		String action = request.getParameter("action");
		
		Board dao = new BoardDAO();
		if(keyword == null) {
			if(action != null) {
				boolean result = dao.delete(Integer.parseInt("num"));
				if(result) {
					request.setAttribute("msg", "글이 성공적으로 삭제되었습니다.");
				}else {
					request.setAttribute("msg", "글이 삭제되지 않았습니다.");
				}
			}
			request.setAttribute("list", dao.listAll());
		}
		else {
			ArrayList<BoardVO> list = dao.search(keyword);
			if(list != null &&list.size() == 0) {
				request.setAttribute("msg", keyword +"(이)가 포함된 글이 없습니다.");
			}else {
				request.setAttribute("list", dao.search(keyword));
			}
		}
		request.getRequestDispatcher("/jsp/BoardMain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String b_writer = request.getParameter("b_writer");
		String b_title = request.getParameter("b_title");
		String writeDate = request.getParameter("writeDate");
		Board dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setWriter(b_writer);
		vo.setTitle(b_title);
		vo.setWritedate(writeDate);
		if(action.equals("insert")) {
			boolean result = dao.insert(vo);
			if(result) {
				request.setAttribute("msg", b_writer+"님의 글이 성공적으로 입력되었습니다.");
			}else {
				request.setAttribute("msg", b_writer +"님의 글의 입력이 실패했습니다. ");
			}
		}else {
			vo.setNum(Integer.parseInt(action));
			boolean result = dao.update(vo);
			if(result) {
				request.setAttribute("msg", b_writer +"님의 글이 성공적으로 수정되었습니다. ");
			}else {
				request.setAttribute("msg", b_writer +"님의 글이 수정되지 않았습니다.");
			}
		}
		request.setAttribute("list", dao.listAll());
		request.getRequestDispatcher("/jsp/BoardMain.jsp").forward(request, response);

	}

}
