package kr.co.jmymble.jsp.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.jmymble.jsp.dao.AttachDao;
import kr.co.jmymble.jsp.dao.BoardDao;
import kr.co.jmymble.jsp.domain.Attach;
import kr.co.jmymble.jsp.domain.Board;
import kr.co.jmymble.jsp.domain.Criteria;

public class BoardServiceImpl implements BoardService{
	//Service는 사용자 측에서 해야할 기능 (transition의 기준)

	private BoardDao dao = new BoardDao();
	private AttachDao attachDao = new AttachDao();
	//댓글 선언
	
	@Override
	public Long register(Board board) {
		//글 작성 후 글번호 지정
		
		Long bno = (long)dao.insert(board); //첨부파일 insert하기 위해서는 bno를 알아야 함.
		System.out.println("boardService.register() :: " + bno);
		//
		for(Attach attach : board.getAttachs()) { //업로드해서 가져온 값, bno는 null인 상태
			attach.setBno(bno); //bno 값 넣어주기
			attachDao .insert(attach);
			
		}
		
		return bno;
	}

	@Override
	public Board get(Long bno) {
		dao.increaseHitCount(bno); //조회수 1 증가
		Board board = dao.selectOne(bno);
		board.setAttachs(attachDao.selectList(bno));
		return board;
	}

	@Override
	public List<Board> list(Criteria cri) {
		System.out.println("BoardServiceImpl.list cri = " + cri);
		return dao.selectList(cri);
//		return dao.selectList(cri).stream().map(board -> { //게시글 개수만큼 반복작업
//				board.setAttachs(attachDao.selectList(board.getBno()));
//				return board;
//		}).collect(Collectors.toList());
	}

	@Override
	public void modify(Board board) {
		// TODO Auto-generated method stub
		dao.update(board);
	}

	@Override
	public void remove(Long bno) {
		//운영체제가 가지고 있는 파일 시스템
		//파일 시스템에서 삭제
		//지워야될 파일의 위치는 db가 알고 있음
		attachDao.selectList(bno).forEach(attach -> {
			attach.getFile().delete();
			if(attach.isImage()) { //이미지 시 썸네일까지 삭제
				//getFile(false) 일반 파일, getFile(true) 이미지 파일
				attach.getFile(true).delete(); 
			}
		});
		
		//첨부 목록 삭제 -> db삭제
		attachDao.delete(bno);
		
		//댓글 삭제
		
		
		//db에서 tbl_board 삭제
		dao.delete(bno);
	}

	@Override
	public int listCount(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.selectListCount(cri);
	}

}
