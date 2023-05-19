package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {
	public static void main(String[] args) throws IOException {
//		System.out.println(File.separator); //window는 \ linux 베이스는 /
//		System.out.println(File.pathSeparator); //window는 ; linux 베이스는 :
//		File file = new File("c:" + File.separator);
//		System.out.println(file);
//		System.out.println(file.isDirectory()); //true
//		System.out.println(file.isFile()); //false
		
//		File 권한(RWX) R = read, W = write, X = execute
//		101 5(oct)(읽기 가능, 쓰기 불가능, 실행 가능)
//		111 7
//		100 4
		
//		Hidden
//		File file = new File("abcd");
//		System.out.println(file.exists());
//		
//		file.createNewFile(); //파일 생성하기
//		
//		File file2 = new File("abcde");
//		System.out.println(file2.exists());
//		System.out.println(file2.mkdir());
//		
//		// c:/upload/2023/03/14
//		File file3 = new File("c:/upload", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
//		file3.mkdirs();
//		File file4 = new File(file3, "test.txt");
//		file4.createNewFile();
		
//		File file = new File("c:/users/joonv");
//		File[] files = file.listFiles();
//		for(File f : files) {
////			System.out.println(f);
//			System.out.print(new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss").format(f.lastModified()));
//			if(f.isDirectory()) {
//				System.out.print("           <DIR>            ");
//			}
//			if(f.isFile()) {
//				System.out.print("         " + f.length() + "bytes");
//			}
//			System.out.println("         " + f.getName());
//		}
		
//		String str = "a.b.c.txt";
		String str = "abcde";
		System.out.println(str.substring(str.lastIndexOf(".")));
		System.out.println(new File("c:/upload" , "abcd.txt").getPath());
	}
}
