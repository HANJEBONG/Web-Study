package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.database.*;
public class BoardDAO {
	private PreparedStatement ps;
	private Connection conn;
	private static BoardDAO dao;
	private DataBaseConnection dbconn=new DataBaseConnection();
	private String[] mode= {"","name","subject","content"};
	public static BoardDAO newInstance() {
		if(dao==null)
			dao=new BoardDAO();
		return dao;
	}
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list =new ArrayList<BoardVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate , 'YYYY-DD-MM') , hit , num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(board board_no_pk)*/no,subject,name,regdate,hit "
					+ "FROM board)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowsize=10;
			int start=(rowsize*page)-(rowsize-1);
			int end=rowsize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDb_day(rs.getString(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	public int boardTotalPage() {
		int total=0;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT COUNT(*) "
					+ "FROM board";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return total;
	}
	
	public BoardVO boardDetailData(int no) {
		BoardVO vo=new BoardVO();
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE board SET "
					+ "hit=hit+1 "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			sql="SELECT no,name,subject,content,TO_CHAR(regdate , 'YYYY-MM-DD'),hit "
				+ "FROM board "
				+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDb_day(rs.getString(5));
			vo.setHit(rs.getInt(6));
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	
	public void boardInsert (BoardVO vo) {
		try {
			conn=dbconn.getConnection();
			String sql="INSERT INTO board VALUES("
					+ "board_no_seq.nextval,?,?,?,?,SYSDATE,0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public void boardUpdate (BoardVO vo,int no) {
		try {
			conn=dbconn.getConnection();
			String sql="UPDATE board SET "
					+ "name=?,subject=?,content=?,regdate=SYSDATE,hit=0 "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
	}
	
	public void boardDelete(int no) {
		try {
			conn=dbconn.getConnection();
			String sql="DELETE FROM board "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			dbconn.disConnection(conn, ps);
		}
	}
	
	public boolean boardPwdCheck(String pwd,int no) {
		boolean bCheck=false;
		try {
			conn=dbconn.getConnection();
			String sql="SELECT pwd "
					+ "FROM board "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			if(pwd.equals(rs.getString(1))) {
				bCheck=true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			dbconn.disConnection(conn, ps);
		}
		return bCheck;
	}
	
	public List<BoardVO> boardFindListData (int type,String fd, int page){
		List<BoardVO> list=new ArrayList<BoardVO>();
		try {
			conn=dbconn.getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate , 'YYYY-DD-MM') , hit , num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(board board_no_pk)*/no,subject,name,regdate,hit "
					+ "FROM board WHERE REGEXP_LIKE("+mode[type]+",?))) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, fd);
			int rowsize=10;
			int start=(rowsize*page)-(rowsize-1);
			int end=rowsize*page;
			ps.setString(1, fd);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDb_day(rs.getString(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
}
