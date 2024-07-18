package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.database.*;
public class FoodDAO {
	private PreparedStatement ps; // sql 문장 송수신
	private Connection conn; // 연결
	private static FoodDAO dao; // 싱글턴
	private DataBaseConnection dbconn=new DataBaseConnection();
	
	public static FoodDAO newInstanece() {
		if(dao==null) {
			dao=new FoodDAO();
		}
		return dao;
	}
	// 기능
	public List<FoodVO> foodListData(int page){
		List<FoodVO> list=new ArrayList<FoodVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT fno,poster,name,num "
					+ "FROM (SELECT fno,poster,name,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(food_house fh_fno_pk)*/fno,poster,name "
					+ "FROM food_house)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowsize=12;
			int start=(rowsize*page)-(rowsize-1);
			int end=rowsize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
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
	public int foodTotalPage() {
		int total=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+ "FROM food_house";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return total;
	}
	// 상세보기
	public FoodVO foodDetailData(int fno) {
		FoodVO vo=new FoodVO();
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE food_house SET "
					+ "hit=hit+1 "
					+ "WHERE fno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ps.executeUpdate();
			// 조회수 증가
			sql="SELECT fno,name,type,phone,address,theme,poster,content,score "
			   +"FROM food_house "
			   +"WHERE fno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setName(rs.getString(2).substring(0,rs.getString(2).indexOf("[")));
			vo.setType(rs.getString(3));
			vo.setPhone(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setTheme(rs.getString(6));
			vo.setPoster(rs.getString(7).replace("https", "http"));
			vo.setContent(rs.getString(8));
			vo.setScore(rs.getDouble(9));
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		
		return vo;
	}
	
}
