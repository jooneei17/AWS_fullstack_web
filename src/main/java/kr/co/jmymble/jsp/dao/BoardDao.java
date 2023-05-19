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

import kr.co.jmymble.jsp.domain.Board;
import kr.co.jmymble.jsp.domain.Criteria;
import kr.co.jmymble.jsp.domain.Member;
import kr.co.jmymble.jsp.util.DBConn;

public class BoardDao {

	private Connection conn;
//	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//회원가입 insert
	public Long insert(Board board) { //나오는 값이 처리 행 개수이기 때문에 리턴 값이 int

		conn = DBConn.getConnection();
		long result = 0;
		//처리할 sql 구문
		String sql = "insert into tbl_board (title, content, writer) values (?, ?, ?)";
				
		try {
			//문장 생성
			pstmt = conn.prepareStatement(sql);
			//set을 이용해서 각 형식에 맞는 타입 지정
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			
			//문장 처리
			pstmt.executeUpdate();
			close(); //(데이터베이스를 처리하는 과정에서 발생하는 비정상적인 종료에 대한) 자원 반납처리
		
			//쿼리 재실행
			//작성한 게시글의 글번호 조회
			sql = "select max(bno) from tbl_board"; //글 번호 가져오기
			conn = DBConn.getConnection(); //커넥션 다시 따오기
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); //insert 했으므로 rs(결과)가 필요함. 
			
			//커서 하나 옮기기(행 이동) 더 이동할 행이 있는지 없는지. 하지만 지금은 1행 1열이 나올거라는 걸 알고 있음. 따라서 반복문 안 돌림
			rs.next(); 
			
			result = rs.getLong(1); //1번 칼럼 long 반환
			close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	public void update(Board board) {
		conn = DBConn.getConnection();
		//처리할 sql 구문
		//dbeaver에서 수정한 뒤 내용 그대로 복사 붙여넣기!
		String sql = "update tbl_board set\r\n"
				+ "	title = ?,\r\n"
				+ "	content = ?,\r\n"
				+ "	updatedate = now()\r\n"
				+ "where bno = ?";
		System.out.println("board.getBno() = " + board.getBno());
		try {
			//문장 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setLong(3, board.getBno());
			
			//문장 처리
			pstmt.executeUpdate();
			
			close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void increaseHitCount(Long bno) {
		conn = DBConn.getConnection();
		String sql = "update tbl_board set\r\n"
				+ " hitcount = hitcount+1\r\n"
				+ "where bno = ?";
		try {
			//문장 생성
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
	public Board selectOne(Long bno) {

		conn = DBConn.getConnection();
		//반환 예정 객체
		Board board = null;
		
		//처리할 sql 구문
		String sql = "select tb.*, (select count(*) from tbl_reply tr where tr.bno = tb.bno) replyCnt \r\n"
				+ "from tbl_board tb  where bno = ?";
		try {
			// 문장 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			//결과집합 반환
			rs = pstmt.executeQuery();
			System.out.println("BoardDao.selectOne rs = " + rs);
			//결과 집합 >> 자바 객체
			if(rs.next()) { //반환이 true인 조건문에는 while 반복문이 잘 어울림. true일때 반복, false일 경우 
				int idx = 1;
				//객체 생성 후 값 바인딩
				board = new Board(
						rs.getLong(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++),
						rs.getString(idx++),
						rs.getInt(idx++),
						rs.getInt(idx++),
						null, //첨부 파일 초기값은 null로 설정
						rs.getInt(idx++)
				);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//결과 반환
		return board;
		
	}
	
	public List<Board> selectList(Criteria cri) {
		conn = DBConn.getConnection();
		//반환 예정 객체
		List<Board> boards = new ArrayList<Board>();
	
		 
		
		//처리할 sql 구문
		String sql = "";
		sql += "select tb.*, (select count(*) from tbl_reply tr where tr.bno = tb.bno) replyCnt \r\n"
				+ "from tbl_board tb where category = ?";
		//검색 하겠다. 검색 타입이 null이 아닐 때 수행
		
		sql += getSearchSqlBy(cri);
		sql += " order by bno desc limit ? offset ?;" ;
		
		try {
			// 문장 생성
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setInt(idx++, cri.getCategory()); //category
			pstmt.setInt(idx++, cri.getAmount()); //한 페이지에 보여줄 게시글 양
			pstmt.setInt(idx++, (cri.getPageNum() - 1) * cri.getAmount()); //
			
			//결과집합 반환
			rs = pstmt.executeQuery();
			
			//결과 집합 >> 자바 객체
			while(rs.next()) { //if일 경우 한 번만 반복하여 나오기 때문에 while로 바꾸기
				idx = 1;
				//객체 생성 후 값 바인딩
				Board board = new Board(
						rs.getLong(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++),
						rs.getString(idx++),
						rs.getInt(idx++),
						rs.getInt(idx++),
						null, //첨부 파일의 초기 값은 null로 설정
						rs.getInt(idx++)
				);
				boards.add(board);
				
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//결과 반환
		return boards;
		
	}
	
	public int selectListCount(Criteria cri) {
		conn = DBConn.getConnection();
		//반환 예정 변수
		int count = 0;
		
		//처리할 sql 구문
		String sql = "select count(*) from tbl_board where category = ?";
		sql += getSearchSqlBy(cri);
		try {
			// 문장 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cri.getCategory());
			
			//결과집합 반환
			rs = pstmt.executeQuery();
			
			//결과 집합 >> 자바 객체
			while(rs.next()) { 
				int idx = 1;
				//객체 생성 후 값 바인딩
				count = rs.getInt(1);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//결과 반환
		return count;
		
	}

	
	public void delete(Long bno) { 

		conn = DBConn.getConnection();
		//처리할 sql 구문
		
		String sql = "delete from tbl_board where bno = ?";
		
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

	private String getSearchSqlBy(Criteria cri) {
		String sql = "";
		
		if(cri.getType() != null && cri.getKeyword() != null && String.join("", cri.getType()).length() > 0) {
			sql += " and (";
			
			List<String> list = Arrays.asList(cri.getType()); //배열을 리스트로 바꾸기
			//title content writer
			List<String> typeList = list.stream().map(s ->s + " like '%" + cri.getKeyword() + "%' ").collect(Collectors.toList()); //
			sql += String.join(" or ", typeList);
			
//			sql += " title like '%" + cri.getKeyword() + "%' ";
//			sql += " or";
//			sql += " content like '%" + cri.getKeyword() + "%' ";
//			sql += " or";
//			sql += " writer like '%" + cri.getKeyword() + "%' ";
			
			sql += ")";
		}
		
		return sql;
	}
	
	public static void main(String[] args) {

//		BoardDao dao = new BoardDao();
//		dao.insert(new Board("java dao에서 테스트", "내용", "id1"));
//		//출력할 때, 1L을 사용하는 이유는 long도 아니고 Long 타입이기 때문에
//		//자동으로 변환을 해주지 않음. 따라서 L로 명시적으로 적어주어야 함
//		System.out.println(dao.selectOne(1L)); 
		
		//추가 확인
//		dao.insert(new MemberVo("id1", "1234", "홍길동", null));
		
//		System.out.println(dao.selectOne("id1"));
		
//		Member m = new Member("javaman', '', ''); UPDATE tbl_member SET PW = '12345' WHERE ID = 'id1'; --", "", "", null);
		
//		new BoardDao().selectList(1).forEach(System.out::println); //category가 1번인 게시판
		
		//수정 확인
//		BoardDao dao = new BoardDao();
//		Board board = dao.selectOne(4L);
//		System.out.println(board); //(가장 처음 실행했을 경우) 기존에 DBeaver에 있는 데이터 출력
//		board.setTitle("java에서 수정한 제목");
//		board.setContent("java에서 수정한 내용");
//		
//		dao.update(board);
//		
//		//java에서 수정된 제목, 내용으로 변경되어 출력
//		System.out.println(dao.selectOne(4L));
		
		
		//삭제 확인
//		BoardDao dao = new BoardDao();
//		Board board = dao.selectOne(4L);
//		System.out.println(board); //(가장 처음 실행했을 경우) 기존에 DBeaver에 있는 데이터 출력
//		dao.delete(4L);
//		
//		//삭제돼 null이 출력 
//		System.out.println(dao.selectOne(4L));
		
		
		//DB에 있는 게시글의 개수 테스트
//		BoardDao dao = new BoardDao();
//		System.out.println(dao.selectListCount(1)); //카테고리 1의 게시글 개수 확인
		
		
		//String join 연습
		String str = "12345";
		String[] result = str.split("");
		System.out.println(result.length);
		
		String result2 = String.join(" or ", result);
		System.out.println(result2);
	}
}
