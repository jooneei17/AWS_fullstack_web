package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/java")
//@WebServlet(value={"/java", "/servlet"}, initParams = @WebInitParam(name = "version", value = "17.0")) //두 개의 요청정보를 이용해서 현재 페이지로 이동
@WebServlet(urlPatterns = "/java")
public class HelloJava extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello java");
	}
	
}
