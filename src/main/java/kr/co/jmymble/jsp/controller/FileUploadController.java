package kr.co.jmymble.jsp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.co.jmymble.jsp.domain.Attach;
import kr.co.jmymble.jsp.util.ParamSolver;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;

//tomcat을 활용한 파일 업로드
@MultipartConfig(location = ParamSolver.UPLOAD_PATH, fileSizeThreshold = 1024)
@WebServlet("/fileUpload")
public class FileUploadController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("file.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getContentType());
		System.out.println(req.getParameter("id"));
		
		Collection<Part> parts = req.getParts(); 
		
		List<Attach> attachs = new ArrayList<>();
		for(Part p : parts) { //업로드한 모든 파일 확인 가능
			if(p.getContentType() == null) { //파일이 아니면 업로드 불가
//				String name = p.getName(); //file.jsp의 input의 name속성
//				System.out.println(name + " :: " + req.getParameter(getServletName()));
				continue;
			}
			//파일의 원본 이름
			String origin = p.getSubmittedFileName();
			
			//파일명 중 마지막 .의 위치
			int dotIdx = origin.lastIndexOf(".");
			
			//확장자를 담을 변수
			String ext = "";
			
			//확장자 (문자열 결합을 통한 조합) 구하기
			if(dotIdx > -1) {
				ext = origin.substring(dotIdx); //.txt
				
			}
			
			//UUID 문자열 생성
			String uuid = UUID.randomUUID().toString();
			
			
			// thumbnail
			// c:/upload/2023/03/14/uuid_t.ext //썸네일 작성 규칙
//				p.write("c:\\upload\\" + fileName + ext);
			// 결국 업로드된 파일명은 위 모든 것이 조합된 형태로 만들어진다.
			// 겹칠 일 없음, 원본 파일명도 알고 있음
			
			//경로 문자열 반환
			String path = getTodayStr();
			
			//경로 문자열에 대한 폴더 생성
			File targetPath = new File(ParamSolver.UPLOAD_PATH, path);
			if(!targetPath.exists()) {
				targetPath.mkdirs();
			}
			
			// 원본에 대한 저장
			File fs = new File(targetPath, uuid + ext);
			p.write(fs.getPath());
			
			System.out.println(p.getContentType()); //해당 이미지 파일의 마임타입 확인하기
			//이미지 여부 확인
			// image/x-icon, image/webp -> 이미지 이지만 500에러 발생(fabicon 혹은 .webp 같은거)
//				List<String> exceptImgMine = Arrays.asList("image/x-icon", "image/webp"); //아래에서 try-catch 해주면 이거 안 써도 됨!
//				boolean image = p.getContentType().startsWith("image") && !exceptImgMine.contains(p.getContentType()); //위 에러를 발생시키는 이미지를 이미지로 업로드 하지 않도록
			
			boolean image = p.getContentType().startsWith("image");
			
			//첨부할 파일이 이미지의 경우 썸네일 파일까지 추가한다. 
			if(image) {
				try {
					//썸네일 생성
					File out = new File(targetPath, uuid + "_t" + ext);
					Thumbnailator.createThumbnail(fs, out, 200, 200); //200 200은 가로세로 최대 px
				} catch (UnsupportedFormatException ignore) {}
			}
			attachs.add(new Attach(uuid, origin, image, path));
			// uuid, origin, image(boolean), path
			
			attachs.forEach(System.out::println);
		}
	}
	
	private String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	
	}
	
}
