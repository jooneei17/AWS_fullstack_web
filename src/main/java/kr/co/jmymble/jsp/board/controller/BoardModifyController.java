package kr.co.jmymble.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jmymble.jsp.domain.Board;
import kr.co.jmymble.jsp.domain.Criteria;
import kr.co.jmymble.jsp.service.BoardService;
import kr.co.jmymble.jsp.service.BoardServiceImpl;
import kr.co.jmymble.jsp.util.ParamSolver;

@WebServlet("/board/modify")
public class BoardModifyController extends HttpServlet{
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect(req.getContextPath() + "/member/login");
			return;
		}
		req.setAttribute("cri", ParamSolver.getParams(req, Criteria.class));
		
		req.setAttribute("board", boardService.get(Long.valueOf(req.getParameter("bno"))));
		req.getRequestDispatcher("/WEB-INF/jsp/board/modify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = ParamSolver.getParams(req, Criteria.class);
		Board board = ParamSolver.getParams(req, Board.class);
		
		String bnoString = req.getParameter("bno");
		System.out.println("modify dopost " + bnoString);
		
		System.out.println(cri);
		System.out.println(board);
		
		boardService.modify(board);
		//수정한 뒤, 다시 view로 간다
		resp.sendRedirect("view?bno=" + board.getBno() +"&" + cri.getFullQueryString());
	}
}
