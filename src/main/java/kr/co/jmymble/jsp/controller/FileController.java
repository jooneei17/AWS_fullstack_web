package kr.co.jmymble.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//cos 활용하여 파일 업로드
@WebServlet("/file")
public class FileController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("file.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		MultipartRequest multipartRequest = new MultipartRequest(req, "C:\\upload", 2 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());

		String id = multipartRequest.getParameter("id");
//		String file = multipartRequest.getParameter("file");
		System.out.println(id);
		String origin = multipartRequest.getOriginalFileName("file");
		String realName = multipartRequest.getFilesystemName("file");
//		multipartRequest.getFile("file").
		
		System.out.println(origin);
		System.out.println(realName);
//		System.out.println(file);
		
		
	}
	
}
