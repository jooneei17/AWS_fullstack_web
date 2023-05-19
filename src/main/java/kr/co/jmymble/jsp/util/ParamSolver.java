package kr.co.jmymble.jsp.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import kr.co.jmymble.jsp.domain.Attach;
import kr.co.jmymble.jsp.domain.Board;
import kr.co.jmymble.jsp.domain.Criteria;
import kr.co.jmymble.jsp.domain.Member;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;

public class ParamSolver {
	public static final String UPLOAD_PATH = "c:/upload"; //상수 형태로 정의
	public static <T> T getParams(HttpServletRequest req, Class<T> clazz) {
		//인스턴스 생성
		T t = null;
		try {
			t = clazz.getDeclaredConstructor().newInstance();
			
			
			//선언 필드에 대한 타입 및 이름 체크
			//내가 정의한 필드만 보기 위해서 getDeclaredFields 함수를 가져옴. 
			Field[] fields = clazz.getDeclaredFields();
//			System.out.println(f1);
			for(Field f : fields) {
				//f.getType은 반환 타입이 Class 이다. 
//				System.out.println(f.getName());
				String fieldName = f.getName();
				String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method[] methods = clazz.getDeclaredMethods();
				for(Method m : methods) {
					//setter만 호출하기 위해 set으로 시작하는 지에 대한 조건 처리
					if(setterName.equals(m.getName())) {
						if(req.getParameter(fieldName) == null) {
							continue; //아예 안하는 것 아님, 이번꺼만 건너뛰기
						}
//						System.out.println(m.getName()); //setter가 잘 나오는지 확인
					
						if(f.getType() == Integer.class || f.getType() == int.class) {
							m.invoke(t, Integer.parseInt(req.getParameter(fieldName)));
						}
						if(f.getType() == String.class) {
							m.invoke(t, req.getParameter(fieldName));
						}
						if(f.getType() == String[].class) {
							m.invoke(t, (Object)req.getParameterValues(fieldName));
						}
						if(f.getType() == Long.class || f.getType() == long.class) {
							m.invoke(t, Long.valueOf(req.getParameter(fieldName)));
						}

					}
				}
			
			}
			if(req.getContentType() == null || !req.getContentType().startsWith("multipart")) { //multipart 아니면 리턴, 맞으면 아래
				return t;
			}

			Collection<Part> parts = req.getParts(); 
			
			List<Attach> attachs = new ArrayList<>();
			for(Part p : parts) { //업로드한 모든 파일 확인 가능
				if(p.getContentType() == null) { //파일이 아니면 업로드 불가
//					String name = p.getName(); //file.jsp의 input의 name속성
//					System.out.println(name + " :: " + req.getParameter(getServletName()));
					continue;
				}
				
				System.out.println("p의 contentType " + p.getContentType());
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
//					p.write("c:\\upload\\" + fileName + ext);
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
				
//				System.out.println(p.getContentType()); //해당 이미지 파일의 마임타입 확인하기
				//이미지 여부 확인
				// image/x-icon, image/webp -> 이미지 이지만 500에러 발생(fabicon 혹은 .webp 같은거)
//					List<String> exceptImgMine = Arrays.asList("image/x-icon", "image/webp"); //아래에서 try-catch 해주면 이거 안 써도 됨!
//					boolean image = p.getContentType().startsWith("image") && !exceptImgMine.contains(p.getContentType()); //위 에러를 발생시키는 이미지를 이미지로 업로드 하지 않도록
				
				boolean image = p.getContentType().startsWith("image");
				
				//첨부할 파일이 이미지의 경우 썸네일 파일까지 추가한다. 
				if(image) {
					try {
						//썸네일 생성
						File out = new File(targetPath, uuid + "_t" + ext);
						Thumbnailator.createThumbnail(fs, out, 200, 200); //200 200은 가로세로 최대 px
					} catch (UnsupportedFormatException ignore) {image = false;} //이미지가 아닌 것으로 인식시키기
				}
				attachs.add(new Attach(uuid, origin, image, path));
				// uuid, origin, image(boolean), path
				
				attachs.forEach(System.out::println);
			}
			if(clazz == Board.class) {
				((Board)t).setAttachs(attachs);
			}
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return t;
	}
	
	public static boolean isLogin(HttpServletRequest req) {
		return req.getSession().getAttribute("member") != null;
	}
	
	public static boolean isMine(HttpServletRequest req, String writer) {
		return isLogin(req) && ((Member)req.getSession().getAttribute("member")).getId().equals(writer);
	}
	
	private static String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
		//Criteria.class = 클래스 리터럴 보냄. -> 해당 클래스의 설계도 확인 가능해짐!
		getParams(null, Criteria.class);
	}
}
