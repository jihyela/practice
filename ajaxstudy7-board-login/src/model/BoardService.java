package model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 모델 계층의 비즈니스 로직을 담당하는 객체 <br>
 * 여러 데이터 액세스 로직을 조합해 비즈니스를 정의한다 
 * @author KOSTA-00-KANGSA
 *
 */
public class BoardService {
	private static BoardService service=new BoardService();
	private BoardDAO dao;
	private BoardService(){
		dao=BoardDAO.getInstance();
	}
	public static BoardService getInstance(){
		return service;
	}

	public ArrayList<BoardVO> getPostingList(String pageNo) throws SQLException {
		int pn=1;
		if(pageNo!=null){
			pn=Integer.parseInt(pageNo);
		}
		return dao.getPostingList(pn);
	}//getPostingList
	
	/**
	 * 글번호를 이용해 상세 게시글 조회 ( 조회수 업데이트 되지 않는다 ) 
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	public BoardVO getPostingByNoNotHit(int no) throws SQLException {		
		return dao.getPostingByNo(no);
	}
	/**
	 * 글번호를 이용해 상세 게시글 조회 ( 조회수 업데이트 된다 ) 
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	public BoardVO getPostingByNo(int no) throws SQLException {	
		dao.updateHit(no);
		return dao.getPostingByNo(no);
	}
	
	public void deletePosting(int no) throws SQLException {
		dao.deletePosting(no);
	}
}
