package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.BoardVO;

public class BoardDAO implements Board{
	
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
		try(PreparedStatement pstmt = conn.prepareStatement("insert into board (writer, title, writedate) values(?, ?, now())");) {
			System.out.println(vo.getWritedate());
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
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
				ResultSet rs = stmt.executeQuery("select num, writer, title, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분'), cnt from board where writer like '%" +keyword +"%'");) {
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
	
}
