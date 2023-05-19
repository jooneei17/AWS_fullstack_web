package kr.co.jmymble.jsp.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.co.jmymble.jsp.domain.Member;
import kr.co.jmymble.jsp.util.DBConn;

public class MemberDao {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	//회원가입 insert
	public int insert(Member vo) { //나오는 값이 처리 행 개수이기 때문에 리턴 값이 int
		conn = DBConn.getConnection();
		int result = 0;
		//처리할 sql 구문
		String sql = "insert into tbl_member(id, pw, name) values ('"
				+ vo.getId() + "', '" + vo.getPw() + "', '" + vo.getName() + "')";
		try {
			//문장 생성
			stmt = conn.createStatement();
			
			//문장 처리
			result = stmt.executeUpdate(sql);
			close(); //(데이터베이스를 처리하는 과정에서 발생하는 비정상적인 종료에 대한) 자원 반납처리
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	
	public Member selectOne(String id) {
		conn = DBConn.getConnection();
		//반환 예정 객체
		Member vo = null;
		
		//처리할 sql 구문
		String sql = "select * from tbl_member where id = '" + id + "'";
		try {
			// 문장 생성
			stmt = conn.createStatement();
			
			//결과집합 반환
			rs = stmt.executeQuery(sql);
			
			//결과 집합 >> 자바 객체
			if(rs.next()) { //반환이 true인 조건문에는 while 반복문이 잘 어울림. true일때 반복, false일 경우 
				int idx = 1;
				//객체 생성 후 값 바인딩
				vo = new Member(
						rs.getString(idx++), 
						rs.getString(idx++),
						rs.getString(idx++), 
						rs.getDate(idx++)
				);
				
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//결과 반환
		return vo;
		
	}
	
	public void close() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}	
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}	
		}
		
	}
	
	
	public static void main(String[] args) {
//		MemberDao dao = new MemberDao();
//		dao.insert(new MemberVo("id1", "1234", "홍길동", null));
	}
	
}
