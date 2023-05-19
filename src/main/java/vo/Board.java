package vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private long bno;
	private String title;
	private String content;
	private Date regdate;
	public String getTest() {
		return "my string";
	}
}
