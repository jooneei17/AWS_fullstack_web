package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@WebServlet("/joinProcess")
public class Join extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//parameter 처리 메서드
		//getParameter(문자열:name) => name에 해당하는 value 반환(String)
		//getParameterValues(문자열:name) => name에 해당하는 values 반환(String[])
		
		//getParameterNames() => 모든 파라미터들의 이름을 Enumeration 타입으로 반환
		//getParameterMaps() 
		
//		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String[] hobby = req.getParameterValues(gender);
		
		@Data
		@AllArgsConstructor
		class MyVo {
			String id; 
			String pw; 
			String name;
			String gender;
			String[] hobby;
			
		}
		MyVo vo = new MyVo(id, pw, name, gender, hobby);
		System.out.println(vo);
		System.out.println("=======================");
		
		Enumeration<String> paramNames = req.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String key = paramNames.nextElement();
			if(key.equals("hobby")) 
				System.out.println(key + ":" + Arrays.toString(req.getParameterValues(key)));
			else 
				System.out.println(key + ":" + req.getParameter(key));
		}
		System.out.println("=======================");
		
		
		Map<String, String[]> map = req.getParameterMap();
		for(String key : map.keySet()) {
				System.out.println(key + ":" + Arrays.toString(map.get(key)));
		}
	}
	
}
