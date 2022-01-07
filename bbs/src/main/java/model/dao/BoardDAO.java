package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				blist.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return blist;
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
	 * 게시판 글 검색  
	 */
	public ArrayList<BoardVO> search(String keyword) {
		
		ArrayList<BoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select num, writer, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분',"
						+ "title from board where title like '%" +keyword +"%'");) {
			BoardVO vo;
			while(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setWritedate(rs.getString(3));
				vo.setTitle(rs.getString(4));
				list.add(vo);
			}
		} catch (SQLException e) {
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
		try(PreparedStatement pstmt = conn.prepareStatement("update board set"+
						"writer = ?, " +
						"title = ?, " +
						"writedate = now() "+
						"where num = ?");) {
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setInt(3, vo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		MySQL.close(conn);
		return result;
	}
	
}
