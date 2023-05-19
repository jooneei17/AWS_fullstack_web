package kr.co.jmymble.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.co.jmymble.jsp.domain.Reply;
import kr.co.jmymble.jsp.service.ReplyService;
import kr.co.jmymble.jsp.service.ReplyServiceImpl;
import kr.co.jmymble.jsp.util.ParamSolver;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {

	private ReplyService service = new ReplyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//댓글 목록을 가져와야 함.
		//필요한 입력 값 = bno
		
		String bnoTest = req.getParameter("bno");
		System.out.println("reply doget");
		System.out.println(bnoTest);
		
		Long bno = Long.valueOf(req.getParameter("bno")); //bno 값 가져오기
		List<Reply> replies = service.list(bno);
		Gson gson = new Gson();
//		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(replies);
		resp.setContentType("application/json; charset=utf8");
		resp.getWriter().print(json);
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 여부
		
		
		// 댓글 쓴 사람 === 로그인한 사람인지?
		Long rno = Long.valueOf(req.getParameter("rno"));
		PrintWriter out = resp.getWriter();
		Reply reply = service.get(rno);
		
		System.out.println(service.get(rno));
		System.out.println(reply != null);
		System.out.println(ParamSolver.isMine(req, reply.getWriter()));
		
		if(reply != null && ParamSolver.isMine(req, reply.getWriter())) {
			out.print(service.remove(rno)); //그냥 rno하는 거 아님. req.getParameter("rno")임;;;
			//삭제 되었으면 목록의 element가 빠져야 한다. (안해주면 db에는 안 빠져있다)
		} else {
			out.print(0);
		}
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reply reply = ParamSolver.getParams(req, Reply.class);
		service.register(reply);
	}
	//조회하기
	//Http Method
	// GET(조회), POST(작성), PUT(PATCH), DELETE(삭제)
	
}
