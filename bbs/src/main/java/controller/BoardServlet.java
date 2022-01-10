package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.Board;
import model.dao.BoardDAO;
import model.dao.MembersDAO;
import model.vo.BoardVO;
import model.vo.MembersVO;
import model.vo.PageVO;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			keyword = keyword.trim();
		}
		String search_tagOpt = request.getParameter("search_tag");
		String detail_btn = request.getParameter("detail_btn");
		String scurrentPage = request.getParameter("page");
		HttpSession session = request.getSession();
		int currentPage = 1;
		
		
		BoardDAO dao = new BoardDAO();
		PageVO voPage = new PageVO();
		int pageDevide = voPage.getPageDivide();
		
		if( scurrentPage != null) {
			currentPage = Integer.parseInt(scurrentPage);	// 현재페이지 int로 변경 
		}
		else {
			currentPage = 1;	// 현재페이지 int로 변경
			
			voPage.setCount(dao.pageCnt(pageDevide, search_tagOpt, keyword));
			voPage.setWhereParam(search_tagOpt);
			voPage.setKeyword(keyword);
			session.setAttribute("page", voPage);
		}
		
		voPage = (PageVO)session.getAttribute("page");
		int start = (currentPage-1)*pageDevide;   // 1, 11, 21, 31 ... 
		int end = pageDevide; //currentPage*voPage.getPageDivide();  // 10, 20, 30, 40 ...
		
		if(detail_btn != null && detail_btn.equals("search")) { // 검색버튼 누른 경우
			
			if(keyword != null && search_tagOpt.equals("title")) { // title로 검색한 경우 
				ArrayList<BoardVO> list = dao.searchTitle(keyword);
				
				if(list!=null &&list.size() == 0) { // 없는 타이틀 검색한 경우의 처리 
//					request.setAttribute("msg", keyword+"(이)가 포함된 글이 없습니다.");
					
				}else {
//					request.setAttribute("list", list);
				}
			}else if(keyword!= null && search_tagOpt.equals("writer")) { // writer로 검색한 경우 
				ArrayList<BoardVO> list = dao.searchWriter(keyword);
				
				if(list != null && list.size() == 0) { // 없는 작성자로 검색한 경우의 처리 
//					request.setAttribute("msg", keyword +"(이)가 포함된 글이 없습니다.");
				}else {
//					request.setAttribute("list", list);
				}
			
			} else if(keyword != null && search_tagOpt.equals("content")) {
				ArrayList<BoardVO> list = dao.searchContent(keyword);
				
				if(list != null && list.size() == 0) { // 없는 작성자로 검색한 경우의 처리 
//					request.setAttribute("msg", keyword +"(이)가 포함된 글이 없습니다.");
				}else {
//					request.setAttribute("list", list);
					
				}	
			}
			// 그냥 검색 눌러도 동작안함
			else { // 아무 것도 안 누르고 검색한 경우 그냥 main에 남아있기 

//				request.setAttribute("list", dao.listAll());
			}
		// 검색 버튼 안 누른 경우, 그냥 Main 접속 시 
		}else {
			
//			System.out.println("currentPage : " + currentPage;
//			request.setAttribute("list", dao.listAll());
//			if(keyword != null)
//				System.out.println("keyword(검색 안누른 경우) : " + keyword);
		}
		
		request.setAttribute("list", dao.pagenation(voPage.getWhereParam(), voPage.getKeyword(), start, end));
		
		request.getRequestDispatcher("/jsp/BoardMain.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Board dao = new BoardDAO();
		String path = null;
		String isBoard = request.getParameter("isBoard");
				
		if(isBoard != null) {
			
			String num = request.getParameter("num");
			String b_writer = request.getParameter("b_writer");
			String b_title = request.getParameter("b_title");
			String b_content = request.getParameter("b_content");
			String writeDate = request.getParameter("writeDate");
			
			BoardVO vo = new BoardVO();
			
			if(num != null) {
				vo.setNum(Integer.parseInt(num));
			}
			vo.setWriter(b_writer);
			vo.setTitle(b_title);
			if(writeDate != null) {
				vo.setWritedate(writeDate);
			}
			vo.setContent(b_content);
			boolean result;
			
			if(isBoard.equals("update")) {
				if(num != null) {
					vo.setNum(Integer.parseInt(num));
					 result = dao.update(vo);
					if(result) {
						request.setAttribute("msg", b_writer+"님의 글이 성공적으로 수정되었습니다.");
					}else {
						request.setAttribute("msg", b_writer +"님의 글이 수정되지 않았습니다.");
					}
				}
			}
			else if (isBoard.equals("write")) {
				
				// WriteBoard (writer, title, content)
				
				BoardVO boardVO = new BoardVO();
				boardVO.setTitle(b_title);
				boardVO.setWriter(b_writer);
				boardVO.setContent(b_content);
				
				result = dao.insert(boardVO);
				
				if(result) {
					request.setAttribute("msg", b_writer+"님의 글이 성공적으로 작성되었습니다.");
				}else {
					request.setAttribute("msg", b_writer +"님의 글이 작성되지 않았습니다.");
				}
				
			}
			
			path = "/jsp/BoardMain.jsp";
			
		}
		else {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String mem_btn = request.getParameter("mem_btn");
			String mem_fbtn = request.getParameter("mem_fbtn");
			
			MembersDAO daoMem = new MembersDAO();
			HttpSession session = request.getSession();
			String msg = null;
			
			if(mem_fbtn != null) {
				
				if(mem_fbtn.equals("mem_info")){
					path = "/jsp/MemberInfo.jsp";
//					request.getRequestDispatcher("/jsp/MemberInfo.jsp").forward(request, response);
				}
				else if (mem_fbtn.equals("list")) {
					path = "/jsp/MembersList.jsp";
					request.setAttribute("mlist", daoMem.listAll());
//					request.getRequestDispatcher("/jsp/MembersList.jsp").forward(request, response);
				}
			} 
			else {
				if(mem_btn.equals("login")) {
					int result = daoMem.login(id,password);
					if(result == 1) {        
						System.out.println("서블릿 로그인");
						
						session.setAttribute("user", daoMem.readMember(id));
					}
					else if(result == 0){
						msg = "비밀번호가 잘못됐습니다.";
					}
					else if(result == -1){
						msg = "존재하지 않는 ID입니다.";
					}
					else if(result == -2){
						msg = "DB오류입니다.";
					}
				}
				else if (mem_btn.equals("sign-up")){
					
					String name = request.getParameter("name");
					MembersVO voMem = new MembersVO();
					
					voMem.setId(id);
					voMem.setPassword(password);
					voMem.setName(name);
					
					int result = -1;
					try {
						result = daoMem.signUp(voMem);
//						System.out.println(result);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
					if(result == -1) {
						msg = "이미 존재하는 아이디입니다.";
					}
					else if(result == 1) {
						msg = "회원가입이 완료됐습니다.";
					}
				}
				else if (mem_btn.equals("logout")) {
					
					session.removeAttribute("user");
					msg = "로그아웃이 완료됐습니다.";
				}
				else if(mem_btn.equals("mem_update")) {
					
					String name = request.getParameter("name");
					daoMem.updateMemberInfo(id, password, name);
					MembersVO voMem = (MembersVO) session.getAttribute("user");
					voMem.setPassword(password);
					voMem.setName(name);
//					session.removeAttribute("user");
					session.setAttribute("user", voMem);
					msg = "개인정보수정이 성공적으로 수행됐습니다.";
					
				}
				else if(mem_btn.equals("sign-out")) {
					
					daoMem.signOut(id);
					msg = "지금까지 게시판을 이용해주셔서 감사합니다.";
					session.removeAttribute("user");
				}
				
				request.setAttribute("msg", msg);
				
				path = "/jsp/BoardMain.jsp";
			}
			
		}
		PageVO voPage = new PageVO();
		request.setAttribute("list", dao.pagenation("null", "", 0, voPage.getPageDivide()));
		request.getRequestDispatcher(path).forward(request, response);
	}
}
