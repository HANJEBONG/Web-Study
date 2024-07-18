package com.sist.dao;
import java.util.*;
import java.sql.*;
public class FoodDAO {
	private PreparedStatement ps;
	private Connection conn;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static FoodDAO dao;
	
	public FoodDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void disConnection() {
		try {
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static FoodDAO newInstance() {
		if(dao==null) {
			dao=new FoodDAO();
		}
		return dao;
	}
	public void foodInsert (FoodVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO food_house(fno,name,type,phone,address,score,theme,poster,content) "
					+ "VALUES(fh_fno_seq.nextval,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getType());
			ps.setString(3, vo.getPhone());
			ps.setString(4, vo.getAddress());
			ps.setDouble(5, vo.getScore());
			ps.setString(6, vo.getTheme());
			ps.setString(7, "https://www.menupan.com"+vo.getPoster());
			ps.setString(8, vo.getContent());
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
}
