package model;
/**
 * BoardDao 에서 사용할 SQL을 정의 
 * @author kosta-00-kangsa-001
 *
 */
public interface Query {	
	/**
	 * 게시글 글쓰기
	 */
	String INSERT_POSTING=
			"insert into board_inst(no,title,writer,password,content,time_posted) values(board_inst_seq.nextval,?,?,?,?,sysdate)";
	/**
	 * 현재 시퀀스(글번호) 조회
	 */
	String CURRENT_NO="select board_inst_seq.currval from dual";
	/**
	 * 글번호에 해당하는 게시물 조회
	 */
	String SELECT_POSTING="select no,title,writer,content,to_char(time_posted,'YYYY.MM.DD HH24:MI:SS') as time_posted,hits from board_inst where no=?";	
	/**
	 * 게시물 목록 : 페이지 번호에 해당하는 게시물들만 조회하도록 처리 
	 */
	String PAGE_LIST="select no,writer,title,time_posted,hits from("+
	"select no,writer,title,time_posted,hits,ceil(rownum/5) as page from("+
		"select no,writer,title,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits from board_inst order by no desc"+
		")"+
	") where page=?";
	/*String PAGE_LIST
			="SELECT no,title,writer,hits,to_char(time_posted,'YYYY.MM.DD') as time_posted from board_inst order by no desc";*/
	/**
	 * 조회수 업데이트
	 */
	String UPDATE_HITS="update board_inst set hits=hits+1 where no=?";	
	/**
	 * 게시물의 비밀번호 확인
	 */
	String PASS_CHECK="select count(*) from board_inst where no=? and password=?";
	/**
	 * 글번호에 해당하는 게시물 삭제
	 */
	String DELETE_POSTING="delete from board_inst where no=?";
	/**
	 * 게시물 수정 
	 */
	String UPDATE_POSTING="update board_inst set title=?,writer=?,content=? where no=?";
}

