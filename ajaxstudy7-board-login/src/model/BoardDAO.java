package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class BoardDAO {
	private static BoardDAO dao = new BoardDAO();
	private DataSource dataSource;
	private BoardDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static BoardDAO getInstance(){
		return dao;
	}
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); 
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		closeAll(pstmt,con);
	}
	
	public int totalCount() throws SQLException {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select count(*) from board2";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
 		} finally {
			closeAll(pstmt, con);
		}
		return count;
	}//totalCount
	
	public ArrayList<BoardVO> getPostingList(int pageNo) throws SQLException {
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("select A.no, A.title, B.name, A.hits, A.time_posted, A.id ");
			sql.append("from (select no,title,to_char(time_posted,'YYYY.MM.DD') ");
			sql.append("as time_posted,hits,ceil(rownum/5) as page, id from board2) A, member2 B ");
			sql.append("where B.id = A.id and A.page=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pageNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new BoardVO(rs.getInt(1), rs.getString(2), null, rs.getInt(4), rs.getString(5), 
						new MemberVO(rs.getString(6),rs.getString(3))));
			}
		}finally {
			closeAll(rs, pstmt, con); 
		}
		return list;
	}//getPostingList
	
	public MemberVO login(String id, String password) throws SQLException {
		MemberVO mvo = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = getConnection();
			String sql = "select name from member2 where id=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)!=0) {
					mvo = new MemberVO(id, rs.getString(1), password);
				}
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return mvo;
	}//login
	
	public BoardVO getPostingByNo(int no) throws SQLException{
		BoardVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select b.no, b.title, m.name, b.content, b.hits,");
			sql.append(" to_char(b.time_posted,'YYYY.MM.DD HH24:MM:SS')as time_posted, b.id");
			sql.append(" from board2 b, member2 m where b.id = m.id and b.no=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vo=new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(5),
						rs.getString(6), new MemberVO(rs.getString(7), rs.getString(3), null));
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return vo;
	}//getPostingByNo
	
	public void updateHit(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			String sql="update board2 set hits=hits+1 where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}//updateHit
	
	public void deletePosting(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="delete from board2 where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);		
			int result=pstmt.executeUpdate();
			System.out.println("dao delete:"+result);
		}finally{
			closeAll(pstmt,con);
		}
	}//deletePosting
	
	public void write(BoardVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			String sql="select borad2_seq.nextval from dual";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vo.setNo(rs.getInt(1));
			}
			pstmt.close();
			sql="insert into board2(no, title, content, time_posted, id) values(?, ?, ?, sysdate,(select id from member2 where id=?))";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getId());		
			pstmt.executeUpdate();							
		}finally{
			closeAll(rs,pstmt,con);
		}
	}//write
	
	public void updatePosting(BoardVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			String sql="update board2 set title=?,content=? where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());		
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}//updatePosting
	
}
