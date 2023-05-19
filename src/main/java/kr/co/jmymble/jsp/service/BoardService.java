package kr.co.jmymble.jsp.service;

import java.util.List;

import kr.co.jmymble.jsp.domain.Board;
import kr.co.jmymble.jsp.domain.Criteria;

public interface BoardService {
	//CRUD
	
	//Create
	Long register(Board board);
	
	//단일 조회
	Board get(Long bno);
	
	//목록 조회
	List<Board> list(Criteria criteria); //카테고리 별로 목록이 다르게 나온다. 
	
	//게시글 개수 
	int listCount(Criteria cri);
	
	//수정
	void modify(Board board);
	
	//삭제
	void remove(Long bno);
	
}
