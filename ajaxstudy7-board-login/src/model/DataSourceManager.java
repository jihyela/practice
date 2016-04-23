package model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * 
 *  컨넥션 풀을 생성하여 공유하는 객체
 *  시스템 상에서 단 하나만 존재하면 되고
 *  공유해서 사용할 수 있으면 된다. 
 *  -> Singleton Pattern 
 *
 */
public class DataSourceManager {
	private static DataSourceManager instance=new DataSourceManager();
	private DataSource ds;
	private DataSourceManager(){
		try {
			InitialContext ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mydbcp");
			System.out.println("톰켓 데이터베이스 컨넥션 풀 생성 "+ds);
		} catch (NamingException e) {			
			e.printStackTrace();
		}				
	}
	public static DataSourceManager getInstance(){
		return instance;
	}
	public DataSource getDataSource(){
		return ds;
	}
}
























