package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.database.*;
public class FoodDAO {
	private PreparedStatement ps;
	private Connection conn;
	private static FoodDAO dao;
	private String[] mode= {"","한식","중식","양식","일식"};
	private DataBaseConnection dbconn=new DataBaseConnection();
	
	public static FoodDAO newInstance() {
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
	public List<FoodVO> foodFindData(String addr,int page){
		List<FoodVO> list=new ArrayList<FoodVO>();
		try {
			conn=dbconn.getConnection();
			String sql= "SELECT fno,name,poster,num "
					+ "FROM (SELECT fno,name,poster,rownum as num "
					+ "FROM (SELECT fno,name,poster "
					+ "FROM food_house WHERE address LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowsize=12;
			int start=(rowsize*page)-(rowsize-1);
			int end=(rowsize*page);
			ps.setString(1, addr);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2).substring(0, rs.getString(2).indexOf("[")));
				vo.setPoster(rs.getString(3).replace("https", "http"));
				
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
	public int foodFindTotalPage(String addr) {
		int total=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+ "FROM food_house "
					+ "WHERE address LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, addr);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return total;
	}
	public List<FoodVO> foodListData(int type,int page){
		List<FoodVO> list=new ArrayList<FoodVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT fno,name,poster,num "
					+ "FROM (SELECT fno,name,poster,rownum as num "
					+ "FROM (SELECT fno,name,poster "
					+ "FROM food_house WHERE type LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
			/*
			 * String sql="SELECT fno,name,poster,num "
					+ "FROM (SELECT fno,name,poster,rownum as num "
					+ "FROM (SELECT fno,name,poster "
					+ "FROM food_house WHERE REGEXP_LIKE(type,?))) " >> |로 여러개 들어가짐
					+ "WHERE num BETWEEN ? AND ?";
			 */
			ps=conn.prepareStatement(sql);
			int rowsize=12;
			int start=(rowsize*page)-(rowsize-1);
			int end=(rowsize*page);
			ps.setString(1, mode[type]);
			ps.setInt(2, start);
			ps.setInt(3, end);
			// 전체 / 베스트 / 특가 / 신상품 => 각각 테이블이 만들어져있다
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2).substring(0, rs.getString(2).indexOf("[")));
				vo.setPoster(rs.getString(3).replace("https", "http"));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	public int foodListTotalPage(int type) {
		int total=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+ "FROM food_house "
					+ "WHERE type LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, mode[type]);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return total;
	}
}	
