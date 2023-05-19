package kr.co.jmymble.jsp.service;

import java.util.List;

import kr.co.jmymble.jsp.dao.ReplyDao;
import kr.co.jmymble.jsp.domain.Reply;

public class ReplyServiceImpl implements ReplyService{
	private ReplyDao dao = new ReplyDao();
	@Override
	public Long register(Reply reply) {
		// TODO Auto-generated method stub
		return (long)dao.insert(reply);
	}
	@Override
	public Reply get(Long rno) {
		// TODO Auto-generated method stub
		return dao.selectOne(rno);
	}

	@Override
	public List<Reply> list(Long bno) {
		// TODO Auto-generated method stub
		return dao.selectList(bno);
	}

	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		return dao.delete(rno);
	}

	
}
