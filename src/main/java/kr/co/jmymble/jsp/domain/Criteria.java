package kr.co.jmymble.jsp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

	//1. 내가 몇 페이지 인지. 
	//기본 값으로 1 설정
	private int pageNum = 1;
	
	//2. 한 페이지에 몇 개씩 보여줄지
	//기본 값으로 10 설정
	private int amount = 10;
	
	//카테고리. 기본 값은 1
	private int category = 1;
	
	//검색 타입 (title), (content), (writer) 
	private String[] type;
	
	//검색 키워드
	private String keyword = "";
 
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String getQueryString() {
		String str = "";
		str += "amount=" + amount + "&category=" + category;
		str += getTypeStr();
		return str;
	}
	
	public String getFullQueryString() {
		String str = "pageNum=" + pageNum + "&";
		str += getQueryString();
		return str;
	}
	
	public String getTypeStr() {
		String str = "";
//		str += "amount=" + amount + "&category=" + category;
		if(type != null) {
			for(String s : type) {
				str += "&type=" + s;
			}
			str += "&keyword=" + keyword;
		}
		return str;
	}
	
}
