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

import kr.co.jmymble.jsp.domain.Reply;
import kr.co.jmymble.jsp.domain.Board;
import kr.co.jmymble.jsp.domain.Criteria;
import kr.co.jmymble.jsp.domain.Member;
import kr.co.jmymble.jsp.domain.Reply;
import kr.co.jmymble.jsp.util.DBConn;

public class ReplyDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//회원가입 insert
	public int insert(Reply reply) { //나오는 값이 처리 행 개수이기 때문에 리턴 값이 int

		conn = DBConn.getConnection();
		int result = 0;
		//처리할 sql 구문
		String sql = "insert into tbl_reply (content, writer, bno) values (?, ?, ?)";
				
		try {
			//문장 생성
			pstmt = conn.prepareStatement(sql);
			//set을 이용해서 각 형식에 맞는 타입 지정
			pstmt.setString(1, reply.getContent());
			pstmt.setString(2, reply.getWriter());
			pstmt.setLong(3, reply.getBno());
			
			
			//문장 처리
			result = pstmt.executeUpdate();
			
			close(); //(데이터베이스를 처리하는 과정에서 발생하는 비정상적인 종료에 대한) 자원 반납처리
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	public Reply selectOne(Long rno) {
		conn = DBConn.getConnection();
		//반환 예정 객체
		Reply reply = null;
		
		//처리할 sql 구문
		String sql = "";
		sql += "select * from tbl_reply where rno = ?";
		
		try {
			// 문장 생성
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, rno); //category
			
			//결과집합 반환
			rs = pstmt.executeQuery();
			
			//결과 집합 >> 자바 객체
			while(rs.next()) { //if일 경우 한 번만 반복하여 나오기 때문에 while로 바꾸기
				idx = 1;
				//객체 생성 후 값 바인딩
				reply = new Reply(
						rs.getLong(idx++),
						rs.getString(idx++),
						rs.getDate(idx++),
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
		return reply;
	}
	

	public List<Reply> selectList(Long bno) {
		conn = DBConn.getConnection();
		//반환 예정 객체
		List<Reply> replies = new ArrayList<Reply>();
		
		//처리할 sql 구문
		String sql = "";
		sql += "select * from tbl_reply where bno = ?";
		
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
				Reply reply = new Reply(
						rs.getLong(idx++),
						rs.getString(idx++),
						rs.getDate(idx++),
						rs.getString(idx++),
						rs.getLong(idx++)
				);
				replies.add(reply);
				
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//결과 반환
		return replies;
		
	}
	
	public int delete(Long rno) { 

		conn = DBConn.getConnection();
		//처리할 sql 구문
		int ret = 0;
		String sql = "delete from tbl_reply where rno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, rno);

			//문장 처리
			ret = pstmt.executeUpdate(); //처리 행 개수. 실패0 성공1
			
			close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
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

//		ReplyDao dao = new ReplyDao();
//		dao.insert(new Reply("java dao에서 테스트", "내용", "id1"));
//		//출력할 때, 1L을 사용하는 이유는 long도 아니고 Long 타입이기 때문에
//		//자동으로 변환을 해주지 않음. 따라서 L로 명시적으로 적어주어야 함
//		System.out.println(dao.selectOne(1L)); 
		
		//추가 확인
//		dao.insert(new MemberVo("id1", "1234", "홍길동", null));
		
//		System.out.println(dao.selectOne("id1"));
		
//		Member m = new Member("javaman', '', ''); UPDATE tbl_member SET PW = '12345' WHERE ID = 'id1'; --", "", "", null);
		
//		new ReplyDao().selectList(1).forEach(System.out::println); //category가 1번인 게시판
		
		//수정 확인
//		ReplyDao dao = new ReplyDao();
//		Reply Reply = dao.selectOne(4L);
//		System.out.println(Reply); //(가장 처음 실행했을 경우) 기존에 DBeaver에 있는 데이터 출력
//		Reply.setTitle("java에서 수정한 제목");
//		Reply.setContent("java에서 수정한 내용");
//		
//		dao.update(Reply);
//		
//		//java에서 수정된 제목, 내용으로 변경되어 출력
//		System.out.println(dao.selectOne(4L));
		
		
		//삭제 확인
//		ReplyDao dao = new ReplyDao();
//		Reply Reply = dao.selectOne(4L);
//		System.out.println(Reply); //(가장 처음 실행했을 경우) 기존에 DBeaver에 있는 데이터 출력
//		dao.delete(4L);
//		
//		//삭제돼 null이 출력 
//		System.out.println(dao.selectOne(4L));
		
		
		//DB에 있는 게시글의 개수 테스트
//		ReplyDao dao = new ReplyDao();
//		System.out.println(dao.selectListCount(1)); //카테고리 1의 게시글 개수 확인
		
		
		//String join 연습
//		String str = "12345";
//		String[] result = str.split("");
//		System.out.println(result.length);
//		
//		String result2 = String.join(" or ", result);
//		System.out.println(result2);
		
		
//		ReplyDao reply = new ReplyDao();
//		reply.insert(new Reply("내용", "댓글댓글", "id1"));
	}
}
