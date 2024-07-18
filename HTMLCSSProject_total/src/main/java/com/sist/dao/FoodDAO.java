package com.sist.dao;
/*
 * 	   클래스의 종류 (역할) => 클래스는 한개의 기능을 수행
 * 					    =================== 컴포넌트
 * 
 *     VO => 데이터를 모아서 한번에 브라우저 / 모바일로 전송
 *     DAO => 데이터베이스 연동
 *     Manager => 관리 => 데이터 수집 / Open API
 *     Service => 사용자 요청에 대한 처리
 *     Model(Controller) => 사용자 요청을 받아서 => 결과를 전송
 *     =================== MVC (Spring MVC)
 */
import java.util.*;

import com.sist.database.DataBaseConnection;

import java.sql.*;
/*				   JSP 프로젝트  Spring 프로젝트   Spring-Boot => 개인 프로젝트
 * 	 JDBC =======> DBCP ====> ORM ======> DataSet
 *     |			|		   |		     | 자동 SQL 문장을 만들어 준다 (JPA)	 
 *    기본		  연결 속도		  라이브러리 (데이터베이스만 연결)
 *    			 Connection 관리 MyBatis
 *    
 *    자격요건 : Spring 가능자 , JQuery | Vue | React , MySQL , Spring-Boot , AWS
 *    		  =========== 
 *    		  | Java+JSP+Oracle ==> Full Stack
 *    		  우대 : 리눅스 , 정보처리 , DevOps
 *    							  ======= 개발+운영 => Docker / 쿠바네티스 / 젠킨스
 */
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static FoodDAO dao;
	private DataBaseConnection dbconn=new DataBaseConnection();
	
	public static FoodDAO newInstance() {
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
	public List<FoodVO> foodListData(){
		List<FoodVO> list=new ArrayList<FoodVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT fno,name,poster,rownum "
					+ "FROM food_house "
					+ "WHERE rownum<=20";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
}
