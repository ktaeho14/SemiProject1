package join.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import common.JDBCTemplate;
import join.dto.AniDto;

public class AniDao extends JDBCTemplate{
	
	public int joinUser(AniDto dto){
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO ANIMB VALUES(ANINOSEQ.NEXTVAL, ?,?,?,?,?,?,'USER')";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			System.out.println("3 완료");
			
			res = pstm.executeUpdate();
			System.out.println("4 완료");
			
			if(res>0) {
				commit(con);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("34 에러");
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		
		return res;
	}
	
	public String idChk(String myid) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		String sql = "SELECT MYID FROM ANIMB WHERE MYID = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			System.out.println("3 완료");
			
			rs = pstm.executeQuery();
			System.out.println("4 완료");
			
			while(rs.next()) {
				res =rs.getString(1);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("34 에러");
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		return res;
	}
	
	public String emailChk(String myemail) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		String sql = "SELECT MYEMAIL FROM ANIMB WHERE MYEMAIL = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myemail);
			System.out.println("3 완료");
			
			rs = pstm.executeQuery();
			System.out.println("4 완료");
			
			while(rs.next()) {
				res =rs.getString(1);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("34 에러");
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		return res;
	}
	
	
	
	public AniDto loginUser(String myid, String mypw) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		AniDto res = new AniDto();
		ResultSet rs = null;
		String sql = " SELECT * FROM ANIMB WHERE MYID=? AND MYPW=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			System.out.println("03 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4 완료");
			while(rs.next()) {
				
				res.setMyno(rs.getInt(1));;
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyrole(rs.getString(8));
				
			}
			
		} catch (SQLException e) {
			System.out.println("34 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05 종료 \n");
		}
		
		
		return res;
	}
	

	
	
	
	
	
	public int leaveUser(int myno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "DELETE FROM ANIMB WHERE MYNO = ? ";
		/*이름 주소 전화번호 변경*/
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03 완료" +sql);
			
			res = pstm.executeUpdate();
			System.out.println("04 완료"+res);
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("34 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		
		return res;
	}
	
	public int infoch(AniDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANIMB SET MYNAME = ?, MYADDR = ?, MYPHONE = ? WHERE MYNO = ? ";
		/*이름 주소 전화번호 변경*/
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMyaddr());
			pstm.setString(3, dto.getMyphone());
			pstm.setInt(4, dto.getMyno());
			System.out.println("03 완료" +sql);
			
			res = pstm.executeUpdate();
			System.out.println("04 완료");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("34 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		
		return res;
	}
	
	public int pwch(AniDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANIMB SET MYPW = ? WHERE MYNO = ? ";
		/*이름 주소 전화번호 변경*/
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMypw());
			pstm.setInt(2, dto.getMyno());
			System.out.println("03 완료" +sql);
			
			res = pstm.executeUpdate();
			System.out.println("04 완료");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("34 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		
		return res;
	}
	
	public String findid(String myname, String myemail) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String myid = null;
		
		String sql = " SELECT MYID FROM ANIMB WHERE MYNAME = ? AND MYEMAIL = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myname);
			pstm.setString(2, myemail);
			System.out.println("03 완료" +sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 완료");
			while(rs.next()) {
				myid = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("34 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		
		return myid;
	}
	
	public String findpw(String myid, String myname, String myemail) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String mypw = null;
		String temppw = null;
		
		String sql = " SELECT MYPW FROM ANIMB WHERE MYNAME = ? AND MYEMAIL = ? AND MYID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myname);
			pstm.setString(2, myemail);
			pstm.setString(3, myid);
			System.out.println("03 완료" +sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 완료");
			while(rs.next()) {
				mypw = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("34 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		if(mypw != null) {
			for(int i=0; i<8; i++) { 
				int rndVal = (int)(Math.random() * 62); 
				if(rndVal < 10) {
					temppw += rndVal;
				} else if(rndVal > 35) { 
					temppw += (char)(rndVal + 61); 
				} else { 
					temppw += (char)(rndVal + 55); 
				} 
			}
		}
		
		
		return temppw;
	}
	
	public int temppwch(AniDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANIMB SET MYPW = ? WHERE MYNAME = ? AND MYID = ? ";
		/*이름 주소 전화번호 변경*/
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMypw());
			pstm.setString(2, dto.getMyname());
			pstm.setString(3, dto.getMyid());
			System.out.println("03 완료" +sql);
			
			res = pstm.executeUpdate();
			System.out.println("04 완료");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("34 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05 종료");
		}
		
		
		return res;
	}
	
	
	
	
}
