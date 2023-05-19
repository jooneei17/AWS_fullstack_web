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

@WebServlet("/board/view")
public class BoardViewController extends HttpServlet{
	private BoardService boardService = new BoardServiceImpl();
	
	//view
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = ParamSolver.getParams(req, Criteria.class);
		String bnoString = req.getParameter("bno");
		Long bno = Long.valueOf(bnoString);
		System.out.println("bno : " + bno);

		req.setAttribute("cri", cri); //해당 이름으로 호출 가능해짐
		
		
		req.setAttribute("board", boardService.get(bno));
		req.getRequestDispatcher("/WEB-INF/jsp/board/view.jsp").forward(req, resp);
	}
	
}
