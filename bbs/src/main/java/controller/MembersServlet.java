package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MembersDAO;
import model.vo.MembersVO;

@WebServlet("/memcheck")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String mem_btn = request.getParameter("mem_btn");
		String mem_fbtn = request.getParameter("mem_fbtn");
		String memPath = null;
		
		System.out.println("mem_btn : " + mem_btn);
		
		MembersDAO daoMem = new MembersDAO();
		HttpSession session = request.getSession();
		String msg = null;
		
		if(mem_fbtn != null) {
			
			if(mem_fbtn.equals("mem_info")){
				memPath = "/jsp/MemberInfo.jsp";
//				request.getRequestDispatcher("/jsp/MemberInfo.jsp").forward(request, response);
			}
			else if (mem_fbtn.equals("list")) {
				memPath = "/jsp/MembersList.jsp";
				request.setAttribute("mlist", daoMem.listAll());
//				request.getRequestDispatcher("/jsp/MembersList.jsp").forward(request, response);
			}
			
			request.getRequestDispatcher(memPath).forward(request, response);
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
//					System.out.println(result);
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
//				session.removeAttribute("user");
				session.setAttribute("user", voMem);
				
			}
			else if(mem_btn.equals("sign-out")) {
				
				daoMem.signOut(id);
				
				session.removeAttribute("user");
			}
			
			
			System.out.println(msg);
			response.sendRedirect("/bbs/board");
//			request.getRequestDispatcher("/board").forward(request, response); nullpointError뜸
		}
	}
}



