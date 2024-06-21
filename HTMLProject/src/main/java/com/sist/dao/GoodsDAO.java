package com.sist.dao;
import java.util.*;
import java.sql.*;
public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public static GoodsDAO dao;
	public GoodsDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		if(ps!=null)
			ps.close();
		if(conn!=null)
			conn.close();
		}catch(Exception ex) {}
	}
	
	public static GoodsDAO newInstance() {
		if (dao==null) {
			dao=new GoodsDAO();
		}
		return dao;
	}
	
	public List<GoodsVO> goodsListData(int page){
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		try {
			getConnection();
			String sql="SELECT goods_name,goods_poster,num "
					 + "FROM (SELECT goods_name,goods_poster,rownum as num "
					 + "FROM (SELECT goods_name,goods_poster "
					 + "FROM goods_all ORDER BY no ASC)) "
					 + "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=(rowSize*page);
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setName(rs.getString(1));
				vo.setPoster(rs.getString(2));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			disConnection();
		}
		return list;
	}
	
}
