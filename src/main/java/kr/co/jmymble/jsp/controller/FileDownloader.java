package kr.co.jmymble.jsp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jmymble.jsp.domain.Attach;
import kr.co.jmymble.jsp.util.ParamSolver;

@WebServlet("/download")
public class FileDownloader extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Attach attach = ParamSolver.getParams(req, Attach.class);
		System.out.println("FileDownloader.doGet :: " + attach);
		
//		//File객체 생성 후 스트링 뽑아내기
//		File file = new File(ParamSolver.UPLOAD_PATH, attach.getPath());
//		String origin = attach.getOrigin();
//		//파일명 중 마지막 .의 위치
//		int dotIdx = origin.lastIndexOf(".");
//		
//		//확장자를 담을 변수
//		String ext = "";
//		
//		//확장자 (문자열 결합을 통한 조합) 구하기
//		if(dotIdx > -1) {
//			ext = origin.substring(dotIdx); //.txt
//			
//		}
//		file = new File(file, attach.getUuid() + ext);
		
		File file = attach.getFile();
		
		System.out.println("fileDownloader.doGet :: " + file);
		System.out.println(file.exists());
		
		//응답 제작
		//attachment -> 다운로드 하겠다
		//filename -> 다운로드 받았을 때 파일 명(origin)
		//한글 제목 처리 new String(origin.getBytes("utf-8"), "ios-8859-1") -> 기존의 utf-8 인코딩을 ios-8859-1 로 바꾼다.
		resp.addHeader("Content-Disposition", "attachment; filename=" + new String(attach.getOrigin().getBytes("utf-8"), "iso-8859-1"));
		
		//input
		//output
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[(int)file.length()]; //InputStream에 있는 파일의 내용을 가져와서 bytye배열에 저장
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		bos.write(bytes);
		bis.close();
		bos.close();
	}
	
}
