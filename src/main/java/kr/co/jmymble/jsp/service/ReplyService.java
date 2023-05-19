package kr.co.jmymble.jsp.service;

import java.util.List;

import kr.co.jmymble.jsp.domain.Board;
import kr.co.jmymble.jsp.domain.Criteria;
import kr.co.jmymble.jsp.domain.Reply;

public interface ReplyService {
	//CRUD
	
	//Create
	Long register(Reply reply);
	
	//단일 조회
	Reply get(Long rno);

	//목록 조회
	List<Reply> list(Long bno); //카테고리 별로 목록이 다르게 나온다. 
	
	//삭제
	int remove(Long rno);
	
	
}
