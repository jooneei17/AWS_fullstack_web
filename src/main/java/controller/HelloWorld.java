package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet{
	private static int count;
	
	@Override
	public void init() throws ServletException {
		count++;
		System.out.println(count);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		count++;
		System.out.println(count);
	}

	@Override
	public void destroy() {
		count++;
		System.out.println(count);
	}

}
