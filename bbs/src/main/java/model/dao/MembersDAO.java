package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.BoardVO;
import model.vo.MembersVO;

public class MembersDAO {
	
	public ArrayList<MembersVO> listAll(){
		ArrayList<MembersVO> mlist = null;
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, password FROM members");){
			mlist = new ArrayList<>();
			MembersVO vo = null;
			while(rs.next()) {
				vo = new MembersVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				mlist.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return mlist;
	}
	
	public MembersVO readMember(String id) {
		String SQL = "SELECT password, name, grade FROM MEMBERS WHERE id = ?";
		Connection conn = MySQL.connect();
		MembersVO vo = new MembersVO();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setId(id);
				vo.setPassword(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setGrade(rs.getInt(3));
			}
		}
		catch(SQLException se) {
			System.out.println(se.getMessage());
		}
		
		MySQL.close(conn);
		return vo;
	}
	
	public int login(String id, String password) {
		String SQL = "SELECT password FROM MEMBERS WHERE id = ?";
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(password)) {		
//					System.out.println("로그인 성공");
					MySQL.close(conn);
					return 1; // 로그인 성공
				}
				else {
//					System.out.println("비밀번호 불일치");
					MySQL.close(conn);
					return 0; // 비밀번호 불일치
				}
			}
			MySQL.close(conn);
			return -1; // 아이디가 없음
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}			
		MySQL.close(conn);
//		System.out.println("데이터베이스 오류");
		return -2; // DB 오류
	}
	
	public int signUp(MembersVO vo) throws SQLException {
		String SQL = "INSERT INTO members (id, password, name) values (?, ?, ?)";
		Connection conn = MySQL.connect();
		int result = -1;
		try(PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
//			System.out.println("회원가입 성공");
			result = pstmt.executeUpdate(); // 1, 회원가입 성공
			MySQL.close(conn);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return -1; // 회원가입 실패
	}
	
	public boolean signOut(String id) {
		String SQL = "DELETE FROM members WHERE id = ?";
		Connection conn = MySQL.connect();
		boolean result = true;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, id);
			int signOutNum = pstmt.executeUpdate();
			if(signOutNum != 1)
				result = false;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}			
		MySQL.close(conn);		
		return result;
	}
	
	public boolean updateMemberInfo(String id, String password, String name) {
//		update members set password='1234', name='호랑나비' where id = 'loud'; 
		
		String SQL = "UPDATE members SET password = ?, name = ? where id = ?";
		Connection conn = MySQL.connect();
		boolean result = true;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SQL)){
			pstmt.setString(1, password);
			pstmt.setString(2, name);
			pstmt.setString(3, id);
			int updateNum = pstmt.executeUpdate();
			
//			System.out.println("업데이트 수행");
			
			if(updateNum != 1) {
				result = false;
			}
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return result;
	}
}
