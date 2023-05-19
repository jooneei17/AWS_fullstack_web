package kr.co.jmymble.jsp.service;

import kr.co.jmymble.jsp.domain.Member;

public interface MemberService {
	//회원 가입
	void register(Member member);
	
	//로그인
	int login(String id, String pw);
	
	//회원 단일 조회
	Member get(String id);
	
	//회원 목록 조회 -> 딱히 필요 없음
	
	
	//회원 정보 수정
	
	//탈퇴
	
}
