package kr.co.jmymble.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.jmymble.jsp.domain.Attach;
import kr.co.jmymble.jsp.domain.Criteria;
import kr.co.jmymble.jsp.domain.Member;
import kr.co.jmymble.jsp.util.DBConn;

public class AttachDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//회원가입 insert
	public void insert(Attach attach) { 
		conn = DBConn.getConnection();
		String sql = "insert into tbl_attach values (?, ?, ?, ?, ?)";
		try {
			//문장 생성
			pstmt = conn.prepareStatement(sql);
			//set을 이용해서 각 형식에 맞는 타입 지정
			int idx = 1;
			pstmt.setString(idx++, attach.getUuid());
			pstmt.setString(idx++, attach.getOrigin());
			pstmt.setBoolean(idx++, attach.isImage());
			pstmt.setString(idx++, attach.getPath());
			pstmt.setLong(idx++, attach.getBno());
			
			//문장 처리
			pstmt.executeUpdate();
			close(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Attach selectOne(String uuid) {

		conn = DBConn.getConnection();
		//반환 예정 객체
		Attach attach = null;
		
		//처리할 sql 구문
		String sql = "select * from tbl_attach where uuid = ?";
		try {
			// 문장 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uuid);
			
			//결과집합 반환
			rs = pstmt.executeQuery();
			
			//결과 집합 >> 자바 객체
			if(rs.next()) { //반환이 true인 조건문에는 while 반복문이 잘 어울림. true일때 반복, false일 경우 
				int idx = 1;
				//객체 생성 후 값 바인딩
				attach = new Attach(
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getBoolean(idx++),
						rs.getString(idx++),
						rs.getLong(idx++)
				);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//결과 반환
		return attach;
		
	}
	
	public List<Attach> selectList(Long bno) {
		conn = DBConn.getConnection();
		//반환 예정 객체
		List<Attach> attachs = new ArrayList<Attach>();
		
		//처리할 sql 구문
		String sql = "select * from tbl_attach where bno = ?";
		
		try {
			// 문장 생성
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, bno); //category
			
			//결과집합 반환
			rs = pstmt.executeQuery();
			
			//결과 집합 >> 자바 객체
			while(rs.next()) { //if일 경우 한 번만 반복하여 나오기 때문에 while로 바꾸기
				idx = 1;
				//객체 생성 후 값 바인딩
				Attach attach = new Attach(
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getBoolean(idx++),
						rs.getString(idx++),
						rs.getLong(idx++)
				);
				attachs.add(attach);
				
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//결과 반환
		return attachs;
	}
	

	//글 번호를 통한 일괄 삭제
	public void delete(Long bno) { 

		conn = DBConn.getConnection();
		//처리할 sql 구문
		String sql = "delete from tbl_attach where bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);

			//문장 처리
			pstmt.executeUpdate();
			close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {

		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}	
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}	
		}
		
	}

	public static void main(String[] args) {


		//String join 연습
		String str = "12345";
		String[] result = str.split("");
		System.out.println(result.length);
		
		String result2 = String.join(" or ", result);
		System.out.println(result2);
	}
}
