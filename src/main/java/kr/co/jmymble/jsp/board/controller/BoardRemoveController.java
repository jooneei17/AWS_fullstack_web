package kr.co.jmymble.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jmymble.jsp.domain.Criteria;
import kr.co.jmymble.jsp.service.BoardService;
import kr.co.jmymble.jsp.service.BoardServiceImpl;
import kr.co.jmymble.jsp.util.ParamSolver;

@WebServlet("/board/remove")
public class BoardRemoveController extends HttpServlet{
	private BoardService boardService = new BoardServiceImpl();
	
	//view
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = ParamSolver.getParams(req, Criteria.class); //크리테리아 불러오기
		boardService.remove(Long.valueOf(req.getParameter("bno")));
		//특정 bno에 해당하는 게시글을 삭제한 뒤 돌아가는 위치는 전체 쿼리스트링(bno 값 없는)을 가져와서 붙인다.
		
		//삭제 뒤 기존에 갖고 있는 내 페이지 정보(5개 보기, 2페이지, 검색 키워드 등등)가 사라지지 않은 상태로
		//그대로 가져오게 된다.
		
		//삭제
		resp.sendRedirect("list" + "?" + cri.getFullQueryString());
	}
}
