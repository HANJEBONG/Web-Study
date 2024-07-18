package com.sist.dao;
import java.sql.*;
import com.sist.database.*;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static MemberDAO dao;
	private DataBaseConnection dbConn=new DataBaseConnection();
	
	public static MemberDAO newInstance() {
		if(dao==null)
			dao=new MemberDAO();
		return dao;
	}
	
	public MemberVO isLogin(String pwd,String id) {
		MemberVO vo=new MemberVO();
		try {
			conn=dbConn.getConnection();
			String sql="SELECT COUNT(*) from member "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			if(count==0) {
				vo.setMsg("noID");
			}else {
				sql="SELECT pwd,name from member "
				  + "WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				rs.next();
				if(pwd.equals(rs.getString(1))) {
					vo.setMsg("ok");
					vo.setName(rs.getString(2));
				}else {
					vo.setMsg("noPWD");
				}
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			dbConn.disConnection(conn, ps);
		}
		return vo;
	}
}
