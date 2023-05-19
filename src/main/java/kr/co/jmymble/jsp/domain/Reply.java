package kr.co.jmymble.jsp.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Reply {
	private Long rno;
	
	@NonNull
	private String content;
	
	@NonNull
	private Date regDate;
	
	@NonNull
	private String writer;
	
	private Long bno;
	
}
