package model;

public class BoardVO {
	//no, title, content, hits, time_posted, vo
	private int no;
	private String title;
	private String content;
	private int hits;
	private String time_posted;
	private String id;
	private MemberVO memberVO;
	public BoardVO() {
		super();
	}
	public BoardVO(int no, String title, String content, int hits, String time_posted, MemberVO memberVO) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.time_posted = time_posted;
		this.memberVO = memberVO;
	}
	
	public BoardVO(String title, String content, MemberVO memberVO) {
		super();
		this.title = title;
		this.content = content;
		this.memberVO = memberVO;
	}
	
	public BoardVO(String title, String content, String id) {
		super();
		this.title = title;
		this.content = content;
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getTime_posted() {
		return time_posted;
	}
	public void setTime_posted(String time_posted) {
		this.time_posted = time_posted;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content="
				+ content + ", hits=" + hits + ", time_posted=" + time_posted
				+ ", memberVO=" + memberVO + "]";
	}
	
}
