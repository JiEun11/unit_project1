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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String search_tagOpt = request.getParameter("search_tag");
		String num = request.getParameter("num");
		String detail_btn = request.getParameter("detail_btn");
		String scurrentPage = request.getParameter("page");
		int currentPage = 1;
//		String action = request.getParameter("action");
		
		Board dao = new BoardDAO();
		// 검색버튼 누른 경우 
		if(detail_btn != null && detail_btn.equals("search")) {
			// title로 검색한 경우 
			if(keyword != null && search_tagOpt.equals("title")) {
				ArrayList<BoardVO> list = dao.searchTitle(keyword);
				
				// 없는 타이틀 검색한 경우의 처리 
				if(list!=null &&list.size() == 0) {
					request.setAttribute("msg", keyword+"(이)가 포함된 글이 없습니다.");
				}else {
					request.setAttribute("list", list);
				}
				
			// writer로 검색한 경우 
			}else if(keyword!= null && search_tagOpt.equals("writer")) {
				ArrayList<BoardVO> list = dao.searchWriter(keyword);
				
				// 없는 작성자로 검색한 경우의 처리 
				if(list != null && list.size() == 0) {
					request.setAttribute("msg", keyword +"(이)가 포함된 글이 없습니다.");
				}else {
					request.setAttribute("list", list);
				}
				
			// content로 검색한 경우 
			}else if(keyword!=null && search_tagOpt.equals("content")) {
				ArrayList<BoardVO> list = dao.searchContent(keyword);
				
				// 없는 게시글로 검색한 경우의 처리 
				if(list != null && list.size() == 0) {
					request.setAttribute("msg", keyword +"(이)가 포함된 글이 없습니다.");
				}else {
					request.setAttribute("list", list);
				}
				
			// 아무 것도 안 누르고 검색한 경우 그냥 main에 남아있기 
			}else {
				request.setAttribute("list", dao.listAll());
			}
 
		}	
		// button 값 없는 Main 접속 시 
		else {
			// 1, 2, 3, ,, page 눌렀을 때
			if( scurrentPage != null) {
				currentPage = Integer.parseInt(scurrentPage);	// 현재페이지 int로 변경 
				//int total = ((BoardDAO) dao).pageCnt("board");  // board테이블의 데이터 개수
				int start = (currentPage-1)*10;   // 1, 11, 21, 31 ... 
				int end = currentPage*10;  // 10, 20, 30, 40 ...
				System.out.printf("start: %d, end: %d %n", start, end);
				request.setAttribute("list", dao.pagenation("board",start, end));
			}
			// 검색 버튼 안 누른 경우, 그냥 Main 접속 시 
			request.setAttribute("list", dao.listAll());
		}
		
		request.getRequestDispatcher("/jsp/BoardMain.jsp").forward(request, response);
		
//		if(keyword == null) {
//			if(action != null && action.equals("delete")) {
//				boolean result = dao.delete(Integer.parseInt(num));
//				if(result) {
//					request.setAttribute("msg", "글이 성공적으로 삭제되었습니다.");
//				}else {
//					request.setAttribute("msg", "글이 삭제되지 않았습니다.");
//				}
//			}
//			request.setAttribute("list", dao.listAll());
//		}
//		else {
//			ArrayList<BoardVO> list = dao.search(keyword);
//			if(list != null &&list.size() == 0) {
//				request.setAttribute("msg", keyword +"(이)가 포함된 글이 없습니다.");
//			}else {
//				request.setAttribute("list", dao.search(keyword));
//			}
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String num = request.getParameter("num");
//		String action = request.getParameter("action");
		String b_writer = request.getParameter("b_writer");
		String b_title = request.getParameter("b_title");
		String b_content = request.getParameter("b_content");
		String writeDate = request.getParameter("writeDate");
		
		Board dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		vo.setNum(Integer.parseInt(num));
		vo.setWriter(b_writer);
		vo.setTitle(b_title);
		vo.setWritedate(writeDate);
		vo.setContent(b_content);
		
		/*
		 *  상세 페이지에서 수정 버튼 클릭 시 동작 
		 */
		if(num != null) {
			vo.setNum(Integer.parseInt(num));
			boolean result = dao.update(vo);
			if(result) {
				request.setAttribute("msg", b_writer+"님의 글이 성공적으로 수정되었습니다.");
			}else {
				request.setAttribute("msg", b_writer +"님의 글이 수정되지 않았습니다.");
			}
			
		}
		
//		if(action.equals("insert")) {
//			boolean result = dao.insert(vo);
//			if(result) {
//				request.setAttribute("msg", b_writer+"님의 글이 성공적으로 입력되었습니다.");
//			}else {
//				request.setAttribute("msg", b_writer +"님의 글의 입력이 실패했습니다. ");
//			}
//		}else if(action.equals("update")){
//			vo.setNum(Integer.parseInt(num));
//			boolean result = dao.update(vo);
//			if(result) {
//				request.setAttribute("msg", b_writer +"님의 글이 성공적으로 수정되었습니다. ");
//			}else {
//				request.setAttribute("msg", b_writer +"님의 글이 수정되지 않았습니다.");
//			}
//		}
		request.setAttribute("list", dao.listAll());
		request.getRequestDispatcher("/jsp/BoardMain.jsp").forward(request, response);

	}

}
