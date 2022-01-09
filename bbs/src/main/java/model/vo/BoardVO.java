package model.vo;

public class BoardVO {
	
	private int num;		// 게시판 글 번호
	private String writer;  // 작성자 
	private String title; 	// 타이틀
	private String content; // 컨텐트 
	private String writedate; // 작성날짜 및 시간
	private int cnt;		 // 조회수 
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "BoardVO [num="+ num + ", writer=" + writer + ", title=" + title + ", content+="+
				content + ", writedate=" + writedate + ", cnt+=" + cnt + "]";
	}
	
}