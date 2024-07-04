package com.sist.dao;
import java.util.*;
import com.sist.database.*;
import java.sql.*;
public class GoodsDAO {
	private PreparedStatement ps;
	private Connection conn;
	private DataBaseConnection dbconn=new DataBaseConnection();
	private static GoodsDAO dao;
	private String[] mode= {"","goods_all","goods_best","goods_special","goods_new"};
	
	public static GoodsDAO newInstance() {
		if(dao==null) {
			dao=new GoodsDAO();
		}
		return dao;
	}
	public int goodsTotal() {
		int total=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+ " FROM goods_all";
			ps=conn.prepareStatement(sql);
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
	/*
	 * ----------------- -------- -------------- 
NO                NOT NULL NUMBER(38)     
GOODS_NAME                 VARCHAR2(4000) 
GOODS_SUB                  VARCHAR2(4000) 
GOODS_PRICE                VARCHAR2(26)   
GOODS_DISCOUNT             NUMBER(38)     
GOODS_FIRST_PRICE          VARCHAR2(26)   
GOODS_DELIVERY             VARCHAR2(26)   
GOODS_POSTER               VARCHAR2(4000) 
HIT                        NUMBER(38)     
	 */
	public List<GoodsVO> goodsListData(int type,int page){
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT no,goods_name,goods_poster,num "
					+ "FROM (SELECT no,goods_name,goods_poster,rownum as num "
					+ "FROM (SELECT no,goods_name,goods_poster "
					+ "FROM "+mode[type]+")) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int curpage=12;
			int start=(curpage*page)-(curpage-1);
			int end=(curpage*page);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public GoodsVO goodsDetailData(int type,int no) {
		GoodsVO vo=new GoodsVO();
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE "+mode[type]+" SET "
					+ "hit=hit+1 "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql="SELECT no,goods_discount,hit,goods_name,goods_sub,goods_price,goods_first_price,goods_delivery,goods_poster "
				+ "FROM "+mode[type]+" "
				+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setGoods_discount(rs.getInt(2));
			vo.setHit(rs.getInt(3));
			vo.setGoods_name(rs.getString(4));
			vo.setGoods_sub(rs.getString(5));
			vo.setGoods_price(rs.getString(6));
			vo.setGoods_first_price(rs.getString(7));
			vo.setGoods_delivery(rs.getString(8));
			vo.setGoods_poster(rs.getString(9));
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	public List<GoodsVO> goodsFindlist(int choice,String fd,int page){
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT no,goods_name,goods_poster,num "
					+ "FROM (SELECT no,goods_name,goods_poster,rownum as num "
					+ "FROM (SELECT no,goods_name,goods_poster "
					+ "FROM "+mode[choice]+" WHERE REGEXP_LIKE(goods_name,?))) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int curpage=12;
			int start=(curpage*page)-(curpage-1);
			int end=(curpage*page);
			ps.setString(1, fd);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
}
