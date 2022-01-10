package model.vo;

public class PageVO {

	private int pageDivide = 8;
	private int count;
	private String whereParam;
	private String keyword;

	public int getPageDivide() {
		return pageDivide;
	}
	
	public void setPageDivide(int pageDivide) {
		this.pageDivide = pageDivide;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getWhereParam() {
		return whereParam;
	}
	
	public void setWhereParam(String whereParam) {
		this.whereParam = whereParam;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	@Override
	public String toString() {
		return "PageVO [pageDivide=" + pageDivide + ", count=" + count + ", whereParam=" + whereParam + ", keyword="
				+ keyword + "]";
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
