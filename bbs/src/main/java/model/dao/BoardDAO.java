package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.BoardVO;

public class BoardDAO implements Board{
	
	public int totalBoardCount() {
		int total = -1;
		Connection conn = MySQL.connect();
		
		try(PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM board");) {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
//			System.out.println("total : " + total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return total;
	}
	
	public ArrayList<BoardVO> page() {
		
		ArrayList<BoardVO> blist = null;
		Connection conn = MySQL.connect();
		
		
		return blist;
	}
	
	@Override
	/*
	 *  MainPage에서 전체 리스트 보여주는 method
	 */
	public ArrayList<BoardVO> listAll(){
		ArrayList<BoardVO> blist = null;
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select num, writer, title, date_format(writedate,'%Y년 %m월 %d일 %H시 %i분'), cnt from board "
					+ "order by writedate desc");){
			blist = new ArrayList<>();
			BoardVO vo = null;
			while(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setWritedate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				blist.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return blist;
	}
	
	/*
	 * 상세 페이지를 보여주는 메소드
	 */
	public BoardVO detailView(int num) {
		Connection conn = MySQL.connect();
		BoardVO vo = null;		// vo 변수 초기화
		try(Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select num, writer, title, content, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분') writedate, cnt from board where num = " + num );
			vo = new BoardVO();
			if(rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWritedate(rs.getString("writedate"));
				vo.setCnt(rs.getInt("cnt"));
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
		MySQL.close(conn);
		return vo;
	}
	
	@Override
	/*
	 *  게시글 작성
	 */
	public boolean insert(BoardVO vo) {
		boolean result = true;
		Connection conn = MySQL.connect();

		try(PreparedStatement pstmt = conn.prepareStatement("insert into board (writer, title, content, writedate) values (?, ?, ?, now())");) {
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("등록 오류");
			result = false;
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		MySQL.close(conn);
		return result;
	}

	@Override
	/*
	 * 게시판 글 타이틀로 검색  
	 */
	public ArrayList<BoardVO> searchTitle(String keyword) {
		
		ArrayList<BoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select num, writer, title, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분'), cnt from board where title like '%" +keyword +"%'");) {
			BoardVO vo;
			while(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setWritedate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		return list;
	}
	
	/*
	 *  게시판 글 작성자로 검색 
	 */
	public ArrayList<BoardVO> searchWriter(String keyword) {
		
		ArrayList<BoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select num, writer, title, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분'), cnt from board where writer like '%" +keyword +"'");) {
			BoardVO vo;
			while(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setWritedate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return list;
	}

	/*
	 *  게시판 글 내용으로 검색 
	 */
	public ArrayList<BoardVO> searchContent(String keyword) {
		
		ArrayList<BoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select num, writer, title, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분'), cnt from board where content like '%" +keyword +"%'");) {
			BoardVO vo;
			while(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setWritedate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		return list;
	}
	
	@Override
	/*
	 * 게시글 삭제 
	 */
	public boolean delete(int num) {
		boolean result = true;
		Connection conn = MySQL.connect();
		try(PreparedStatement pstmt = conn.prepareStatement("delete from board where num = ?");) {
			pstmt.setInt(1, num);
			int deleteNum = pstmt.executeUpdate();
			if(deleteNum != 1)
				result = false;
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		MySQL.close(conn);
		return result;
	}

	@Override
	/*
	 *  게시글 업데이트
	 */
	public boolean update(BoardVO vo) {
		boolean result = true;
		Connection conn = MySQL.connect();
		try(PreparedStatement pstmt = conn.prepareStatement("update board set "+
						"title = ?, " +
						"writedate = now(), "+
						"content = ? " +
						"where num = ?");) {
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		return result;
	}
	
	/*
	 * 조회수 증가 
	 */
	public void cntIncrease(BoardVO vo) {
		Connection conn = MySQL.connect();
		try(PreparedStatement pstmt = conn.prepareStatement("update board set "+
						"cnt = cnt + 1 " +
						"where num = ?");) {
			pstmt.setInt(1, vo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		return;
	}
	
	/*
	 * Pagination 구현 - 데이터 개수 count , 1. 나눌 페이지 갯수 2. table 3. where 절 인자값 
	 * 					(1) 모든 글 카운트 null (2) 컨텐츠 , (3) 제목 , (4) 작성자
	 * 					select count(*) from ?; select count(*) from ? where writer like '%%';
	 * 
	 *  		if("null".equals(whereParam)
	 */
	
	public int pageCnt(int pageDevide, String whereParam, String keyword) {
		int total = 0;
		int pageCount = -1;
		//int pageDevide = 10;
		Connection conn = MySQL.connect();
		
		String SQL = null;
		
		// 전체 출력
		if(whereParam == null || "null".equals(whereParam)) { 
			SQL = "SELECT COUNT(*) FROM board";
		}
		// writer, title, content가 입력되는 경우에 따라 처리
		else {
			SQL = "SELECT COUNT(IFNULL("+ whereParam +", 'null')) FROM board WHERE "+ whereParam +" like '%" + keyword + "%'";
		}

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);) {
			if(rs.next()) {
				total = rs.getInt(1); 
			}
			pageCount = total/pageDevide;
			if(total%pageDevide >= 1) {
				pageCount += 1;
				System.out.println("pageCnt:" +pageCount);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		System.out.println(pageCount);
		return pageCount;
	}
	
	public int pageCntAll() {
		int total = 0;
		int pageCount = -1;
		int pageDevide = 10;
		Connection conn = MySQL.connect();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM board");) {
			if(rs.next()) {
				total = rs.getInt(1); 
			}
			pageCount = total/pageDevide;
			if(total%pageDevide >= 1) {
				pageCount += 1;
				System.out.println("pageCnt:" +pageCount);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		System.out.println(pageCount);
		return pageCount;
	}
	
	public int pageCntTitle(String keyword) {
		int total = 0;
		int pageCount = -1;
		int pageDevide = 10;
		Connection conn = MySQL.connect();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(IFNULL(title, 'null')) FROM board WHERE title like '%" + keyword + "%'");) {
			if(rs.next()) {
				total = rs.getInt(1); 
			}
			pageCount = total/pageDevide;
			if(total%pageDevide >= 1) {
				pageCount += 1;
				System.out.println("pageCnt:" +pageCount);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		System.out.println(pageCount);
		return pageCount;
	}
	
	public int pageCntWriter(String keyword) {
		int total = 0;
		int pageCount = -1;
		int pageDevide = 10;
		Connection conn = MySQL.connect();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(IFNULL(writer, 'null')) FROM board WHERE title like '%" + keyword + "%'");) {
			if(rs.next()) {
				total = rs.getInt(1); 
			}
			pageCount = total/pageDevide;
			if(total%pageDevide >= 1) {
				pageCount += 1;
				System.out.println("pageCnt:" +pageCount);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		System.out.println(pageCount);
		return pageCount;
	}
	
	public int pageCntContent(String keyword) {
		int total = 0;
		int pageCount = -1;
		int pageDevide = 10;
		Connection conn = MySQL.connect();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(IFNULL(content, 'null')) FROM board WHERE title like '%" + keyword + "%'");) {
			if(rs.next()) {
				total = rs.getInt(1); 
			}
			pageCount = total/pageDevide;
			if(total%pageDevide >= 1) {
				pageCount += 1;
				System.out.println("pageCnt:" +pageCount);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		MySQL.close(conn);
		System.out.println(pageCount);
		return pageCount;
	}
	
	/*
	 *  MySQL Limit을 통해 시작,끝 페이지의 데이터들만 저장하여 반환 
	 */
	public ArrayList<BoardVO> pagenation(String whereParam, String keyword, int start, int end){
		ArrayList<BoardVO> blist = null;
		Connection conn = MySQL.connect();
		
		String SQL = null;
		
		// 전체 출력
		if(whereParam == null || "null".equals(whereParam)) { 
			SQL = "SELECT num, writer, title, date_format(writedate,'%Y년 %m월 %d일 %H시 %i분'), cnt FROM board ORDER BY writedate DESC LIMIT "+start+", "+ end;
		}
		// writer, title, content가 입력되는 경우에 따라 처리
		else {
			SQL = "SELECT num, writer, title, date_format(writedate,'%Y년 %m월 %d일 %H시 %i분'), cnt FROM board WHERE " + whereParam + " LIKE '%" + keyword + "%' ORDER BY writedate DESC LIMIT "+start+", "+ end;
		}
		
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);)
		{
			blist = new ArrayList<>();
			BoardVO vo = null;
			while(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setWritedate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				blist.add(vo);
				System.out.println(vo.toString());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return blist;
	}
	
}
