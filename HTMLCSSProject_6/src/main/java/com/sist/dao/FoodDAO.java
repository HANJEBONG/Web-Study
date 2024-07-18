package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.database.*;
public class FoodDAO {
	private PreparedStatement ps;
	private Connection conn;
	private static FoodDAO dao;
	private DataBaseConnection dbConn=new DataBaseConnection();
	public static FoodDAO newInstance() {
		if(dao==null) {
			dao=new FoodDAO();
		}
		return dao;
	}
	// 기능
	public List<FoodVO> foodListData(){
		List<FoodVO> list=new ArrayList<FoodVO>();
		try {
			conn=dbConn.getConnection();
			String sql="SELECT fno,name,type,poster "
					+ "FROM food_house "
					+ "WHERE rownum<=50 "
					+ "ORDER BY fno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster(rs.getString(4));
				vo.setType(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbConn.disConnection(conn, ps);
		}
		return list;
		
	}
}
