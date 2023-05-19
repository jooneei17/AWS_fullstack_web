package kr.co.jmymble.jsp.service;

import kr.co.jmymble.jsp.dao.MemberDao;
import kr.co.jmymble.jsp.domain.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao = new MemberDao();  
	
	@Override
	public void register(Member member) {
		// TODO Auto-generated method stub
		memberDao.insert(member);
	}

	@Override
	public int login(String id, String pw) {
		Member member = memberDao.selectOne(id);
		
		if(member == null) {
			//id 없음. 로그인 실패
			return 2;
		} else if(!member.getPw().equals(pw)) {
			//비밀번호 틀림. 로그인 실패
			return 3;
			
		} else {
			//로그인 성공
			return 1;
			
		}
	}

	@Override
	public Member get(String id) {
		return memberDao.selectOne(id);
	}
	
	public void 탈퇴(String id) {
		// 작성한 게시글 아이디 변경
		// 작성한 댓글 아이디 변경
		// 회원 테이블 내에서 삭제
		
		
	}
	
}
