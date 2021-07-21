package com.test.dao.lostDao;

import static util.common.JDBCTemplate.close;
import static util.common.JDBCTemplate.commit;
import static util.common.JDBCTemplate.getConnection;
import static util.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.test.dto.lostDto.LostDto;
import com.test.dto.replyDto.ReplyDto;

public class LostDaoImpl implements LostDao{
	private static LostDaoImpl dao = new LostDaoImpl();
	Connection conn 		   = null;
	Statement  stat		       = null;
	PreparedStatement ps 	   = null;
	ResultSet  rs 			   = null;
	SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");//transfer date to string(.format)
	
	private LostDaoImpl() {
		
	}
	
	public static LostDaoImpl getInstance() {
		return dao;
	}
	
	
	//모든 게시글 조회
	
	@Override
	public List<LostDto> selectAllLost(int page) {
		// TODO Auto-generated method stub
		//"SELECT * FROM (SELECT ROWNUM CNT, NO, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, LOSTPLACE,LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH FROM (SELECT * FROM REGLOST ORDER BY NO DESC)) WHERE CNT BETWEEN ? AND ?"
		List<LostDto> list = new ArrayList<>();
		
		conn = getConnection();

		//9개씩 출력 할 것
		//final int items = 9;
		int     startIdx= (page-1)*9+1;//1 10 19
		int     finIdx  = startIdx +8;//9 18 27
		try {
			ps  = conn.prepareStatement(selectAllQuery);
			
			ps.setInt(1,startIdx);
			ps.setInt(2, finIdx);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LostDto        dto = new LostDto();
				
				dto.setNum(rs.getInt(2));
				dto.setTitle(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setTel(rs.getString(5));
				dto.setLostDate(rs.getString(6));
				dto.setProvince(rs.getString(7));
				dto.setReserv(rs.getString(8));
				dto.setLostPlace(rs.getString(9));
				dto.setLostPic(rs.getString(10));
				dto.setDetail(rs.getString(11));
				dto.setSpecies(rs.getString(12));
				dto.setCate(rs.getString(13));
				dto.setEtc(rs.getString(14));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]SELECT LOST ALL ARTICLE FAILED");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return list;
	}
	//게시글 수 조회
	@Override
	public int countAllLostArticle() {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			stat = conn.createStatement();
			
			rs   = stat.executeQuery(countQuery);
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]Failed to count all article");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stat);
			close(conn);
		}
		return cnt;
	}
	//게시글 상세조회
	@Override
	public LostDto selectOne(int no) {
		// TODO Auto-generated method stub
		/*
		 * SELECT NO, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, LOSTPLACE,LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH FROM REGLOST WHERE NO=?
		 * */
		LostDto target = new LostDto();

		
		try {
			
			conn  = getConnection();
			ps    = conn.prepareStatement(selectOneQuery);
			
			ps.setInt(1, no);
			
			rs    = ps.executeQuery();
			
			if(rs.next()) {
				
				target.setNum(rs.getInt(1));
				target.setWriter(rs.getString(2));
				target.setTitle(rs.getString(3));
				target.setTel(rs.getString(4));
				
				Date tmp = rs.getDate(5);
				String        lostDate= sf.format(tmp);
				
				target.setLostDate(lostDate);
				target.setProvince(rs.getString(6));
				target.setReserv(rs.getString(7));
				target.setLostPlace(rs.getString(8));
				target.setLostPic(rs.getString(9));
				target.setDetail(rs.getString(10));
				target.setSpecies(rs.getString(11));
				target.setCate(rs.getString(12));
				target.setEtc(rs.getString(13));
				target.setWatch(rs.getInt(14));
				System.out.println(target);
			}
		}catch(Exception e) {
			System.out.println("[ERR]FAILED IN SELECT ONE!");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return target;
	}
	//게시글 등록
	@Override
	public int registerLost(LostDto dto, ReplyDto reply) {
		// TODO Auto-generated method stub
		/*"INSERT INTO REGLOST(NUM, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, 
		 * RESERV, LOSTPLACE,LOSTPIC, 
		 * DETAIL, SPECIES, CATE, ETC, WATCH) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0)"
		 * */
		
		int  regRes = 0;
		int  result =0;
		int tempRes = 0;
		
		tempRes = registerRootReply(reply);
		System.out.println("reply: "+reply);
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(insertQuery);
			
			ps.setInt(1, dto.getNum());
			ps.setString(2, dto.getWriter());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getTel());
			ps.setString(5, dto.getLostDate());
			ps.setString(6, dto.getProvince());
			ps.setString(7, dto.getReserv());
			ps.setString(8,dto.getLostPlace());
			ps.setString(9, dto.getLostPic());
			ps.setString(10, dto.getDetail());
			ps.setString(11, dto.getSpecies());
			ps.setString(12, dto.getCate());
			ps.setString(13, dto.getEtc());
			
			regRes = ps.executeUpdate();
			
			System.out.println("regRes: "+regRes+", tempRes: "+tempRes);
			if(regRes > 0 && tempRes>0) {
				System.out.println("실종신고 등록 성공");
				commit(conn);
				result = 1;
			}else {
				System.out.println("실종신고 등록 실패");
				rollback(conn);
				result=-1;
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]FAILED TO REGISTER LOST ANIMAL");
			e.printStackTrace();
		}finally {
			close(ps);
			close(conn);
		}
		
		return result;
	}
	//게시글 수정
	@Override
	public int updateLostInfo(LostDto dto, ReplyDto reply) {
		// TODO Auto-generated method stub
		int temp1 = updateReply(reply);
		int temp2 = updateWatch(dto.getNum());
		int temp3 =0;
		int tot  = 0;
		/*
		 * UPDATE REGLOST SET TITLE=?, LOSTDATE=?,PROVINCE=?,RESERV=?,LOSTPLACE=?,
		 * LOSTPIC=?,DETAIL=?,
		 * SPECIES=?, CATE=?,ETC=? WHERE NUM=?
		 * */
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(updateOriginLostArticleQuery);
			
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getLostDate());
			ps.setString(3, dto.getProvince());
			ps.setString(4, dto.getReserv());
			ps.setString(5, dto.getLostPlace());
			ps.setString(6, dto.getLostPic());
			ps.setString(7, dto.getDetail());
			ps.setString(8, dto.getSpecies());
			ps.setString(9, dto.getCate());
			ps.setString(10, dto.getEtc());
			ps.setInt(11,dto.getNum());
			
			temp3 = ps.executeUpdate();
			
			if(temp1 >0 && temp2>0 && temp3>0) {
				tot=1;
				commit(conn);
				System.out.println("실종신고 게시판 원글 수정 성공");
			}else {
				tot=-1;
				rollback(conn);
				System.out.println("실종신고 게시판 원글 수정 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]원글 수정 실패-실종신고등록게시판");
		}finally {
			close(ps);
			close(conn);
		}
		
		return tot;
	}	

	@Override
	public int totalPage() {
		// TODO Auto-generated method stub
		int articleNum = countAllLostArticle();
		int pageNum    = 0;
		int temp       = articleNum%9;
		
		if(temp >=1 && temp <9) {
			pageNum = articleNum/9 + 1;
		}else {
			pageNum = articleNum/9;
		}
		
		return pageNum;
	}

	@Override
	public int updateWatch(int no) {
		// TODO Auto-generated method stub
		/*UPDATE REGLOST SET (WATCH=WATCH+1) WHERE NO=?*/
		int resUpWatch = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(updateWatchQuery);
			
			ps.setInt(1, no);
			
			resUpWatch = ps.executeUpdate();
			
			if(resUpWatch > 0) {
				System.out.println("조회수 갱신 성공");
				commit(conn);
			}else {
				System.out.println("조회수 갱신 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]조회수 갱신 실패");
			e.printStackTrace();
		}finally {
			close(ps);
			close(conn);
		}
		
		return resUpWatch;
	}

	@Override
	public int countAllReply(int boardId, int num) {
		// TODO Auto-generated method stub
		//SELECT COUNT(REPLY_ORDER) FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND LEV > 0
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(countReplyQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			
			rs   = ps.executeQuery();
			
			if(rs.next()) {
				cnt  = rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]COUNT ALL REPLY ON ARTICLE#"+num);
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return cnt;
	}


	@Override
	public List<ReplyDto> selectAllReply(int boardId, int num) {
		// TODO Auto-generated method stub
		List<ReplyDto> list = new ArrayList<>();
		/*
		 SELECT TARGET.* FROM (SELECT BOARD_ID, REPLY_ORDER, NUM, LEV,LEV_SEQ, DEPTH,
		 REPLYTAB, ID, CONTENT, REGDATE , DEL_FLAG FROM REPLY_ON_LOSTDETAIL 
		 START WITH DEPTH IS NULL CONNECT BY NOCYCLE 
		 PRIOR REPLY_ORDER=NUM ORDER SIBLINGS BY LEV,LEV_SEQ) TARGET WHERE BOARD_ID=? AND NUM=?
		 * */
		//댓글은 전체로 보여줄 것
//		final int size = countAllReply(boardId, num);
//		int startIdx=1;
//		int finIdx  = size;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectAllReplyQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
//			ps.setInt(3, startIdx);
//			ps.setInt(4, finIdx);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReplyDto dto = new ReplyDto();

				dto.setBoardId(rs.getInt(1));
				dto.setReplyOrder(rs.getInt(2));
				dto.setNum(rs.getInt(3));
				dto.setLev(rs.getInt(4));
				dto.setLevSeq(rs.getInt(5));
				dto.setDepth(String.valueOf(rs.getInt(6)));
				dto.setReplyTab(rs.getInt(7));
				dto.setId(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setRegDate(sf.format(rs.getDate(10)));
				dto.setDelFlag(rs.getInt(11));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]SELECT ALL REPLY ON ARTICLE PAGE");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return list;
	}

	@Override
	public ReplyDto selectSpecificReply(int boardId, int num, int lev, int levSeq) {
		// TODO Auto-generated method stub
		/*
		SELECT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, DEPTH, REPLYTAB, ID, CONTENT, REGDATE,, DEL_FLAG
		FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND LEV=? AND LEV_SEQ=?
		 * 
		 * */
		ReplyDto target = new ReplyDto();
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectOneReplyQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, lev);
			ps.setInt(4, levSeq);
			
			rs  = ps.executeQuery();
			
			if(rs.next()) {
				target.setBoardId(rs.getInt(1));
				target.setReplyOrder(rs.getInt(2));
				target.setNum(rs.getInt(3));
				target.setLev(rs.getInt(4));
				target.setLevSeq(rs.getInt(5));
				target.setDepth(String.valueOf(rs.getInt(6)));
				target.setReplyTab(rs.getInt(7));
				target.setId(rs.getString(8));
				target.setContent(rs.getString(9));
				target.setRegDate(sf.format(rs.getDate(10)));
				target.setDelFlag(rs.getInt(11));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]특정 댓글 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return target;
	}

	@Override
	public int registerReplyToArticle(ReplyDto reParent,ReplyDto child) {
		// TODO Auto-generated method stub
		/*
		INSERT INTO REPLY_ON_LOSTDETAIL(BOARD_ID,REPLY_ORDER,NUM,LEV,LEV_SEQ,DEPTH,REPLYTAB,ID,CONTENT,
		REGDATE) 
		VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)
		 * 
		 * */
		int regRes = 0;
		int maxOrd = selectMaxOrderOfBoard();
		int maxSeq = 0;
		String depth =child.getDepth();
		int  dep = 0;
		int  lev = getMaxLevelInReplyBoard(reParent.getBoardId(), reParent.getNum());
		
		System.out.println("max order: "+maxOrd);
		//levSeq 최댓값 찾기
	//	maxSeq  = selectMaxLevOrderOfBoard(child.getBoardId(), child.getNum(),child.getLev());
		System.out.println("부모노드의 시퀀스: "+maxSeq);
		
		
		if(depth==null) {
			dep=1;
			maxSeq =  0;
		}else {
			dep=Integer.valueOf(depth);
		}
		
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(insertReplyQuery);
			
			ps.setInt(1, reParent.getBoardId());
			ps.setInt(2, maxOrd+1);
			ps.setInt(3, reParent.getNum());
			ps.setInt(4, lev+1);
			ps.setInt(5, reParent.getLevSeq());
			ps.setInt(6, dep);
			ps.setInt(7, 1);//들여쓰기
			ps.setString(8, child.getId());
			ps.setString(9, child.getContent());
			
			regRes = ps.executeUpdate();
			
			if(regRes >0) {
				commit(conn);
				System.out.println("댓글 작성 성공");
			}else {
				rollback(conn);
				System.out.println("댓글 작성 실패");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]댓글 작성 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return regRes;
	}

	@Override
	public int selectMaxOrderOfBoard() {
		// TODO Auto-generated method stub
		
		/*
		 * String  selectMaxOrderQuery = "SELECT MAX(REPLY_ORDER) FROM 
		 * REPLY_ON_LOSTDETAIL
		 * */
		int max =0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectMaxOrderQuery);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				max = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("해당 게시판의 최대 order 조회 완료");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return max;
	}

	@Override
	public int selectMaxLevOrderOfBoard(int boardId, int num, int lev) {
		// TODO Auto-generated method stub
		/*
		SELECT MAX(LEV_SEQ) FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND LEV=?
		 * */
		int getMax = 0;
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectMaxLevSeqQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, lev);
			
			rs =ps.executeQuery();
			
			if(rs.next()) {
				getMax = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]MAX LEV ORDER FINDING FAILED");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return getMax;
	}

//	@Override
//	public int updateLevelForReply(int boardId, int num, int lev, int replyTab) {
//		// TODO Auto-generated method stub
//		/*
//		 	String  updateLevQuery="UPDATE REPLY_ON_LOSTDETAIL SET LEV = 
//		 	(LEV + 1), LEV_SEQ=(LEV_SEQ+1) WHERE  BOARD_ID=? AND NUM=? AND LEV=? AND REPLYTAB>?";
//		 * */
//		int resLevUpdate = 0;
//		
//		try {
//			
//			conn = getConnection();
//			 
//			ps   = conn.prepareStatement(updateLevQuery);
//			
//			ps.setInt(1, boardId);
//			ps.setInt(2, num);
//			ps.setInt(3, lev);
//			ps.setInt(4, replyTab);
//		
//			
//			resLevUpdate = ps.executeUpdate();
//			
//			if(resLevUpdate > 0) {
//				System.out.println("레벨 조정 성공");
//				commit(conn);
//			}else {
//				System.out.println("레벨 조정 실패");
//				rollback(conn);
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("[ERR]댓글형 게시판 레벨 조정실패");
//		}finally {
//			close(ps);
//			close(conn);
//		}
//		
//		return resLevUpdate;
//	}

	@Override
	public Map<Integer, Integer> getOrderedReplyReply(int boardId, int num) {
		// TODO Auto-generated method stub
		//treeset은 sorted set으로 추가 정렬작업 필요없음
		Map<Integer, Integer> processed = new TreeMap<>();
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(exportOrdQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				processed.put(rs.getInt(1),rs.getInt(2));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]order추출 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return processed;
	}

	@Override
	public int getReplyOrderValue(int boardId, int num, int order) {
		// TODO Auto-generated method stub
		/*
		 * SELECT REPLY_ORDER FROM REPLY_ON_LOSTDETAIL WHERE  BOARD_ID=? AND NUM=? AND LEV=?
		 * */
		Map<Integer, Integer> middle = getOrderedReplyReply(boardId, num);
		//리스트로 변환해서 GET으로 접근할것
	    List<Integer> listed = new LinkedList<>(middle.values());
	    //결과를 담을 리스트
	    List<Integer> tempList=new ArrayList<>();
		int           level  = 0;
		int  		  idx    = 0;
		int  	     len     = listed.size();
		int          export  = 0;
		int          j       = 0;
		boolean      flag    = false;
		int 	     t       = order+1;//부모노드를 제외하고 시작하되, button을 1로 간주
		System.out.println(listed.toString());
		
		for(int i = 0 ; i < len-1; i++) {
			int item1 = listed.get(i)-1;
			int item2 = listed.get(i+1)-1;
			System.out.println("item1: "+item1+",item2:"+item2);
			System.out.println("order: "+order+", t: "+t);
			if(t> item1 &&t <= item2) {
				level=i+1;
				idx  =(int)(Math.abs(item1-t));
				flag =true;
				break;
			}
		}
		//루트노드인 경우
		if(flag==false) {
			level=0;
			idx=getMaxLevelInReplyBoard(boardId,num);//부모노드가 루트노드인 경우는 여럿이므로
		}
		
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(exportLevOrdQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, level);
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				tempList.add(rs.getInt(1));
			}
			
			export = tempList.get(idx-1);
			
		}catch(Exception e) {
			System.out.println("[ERR]부모 노드 레벨 인식을 위한 과정 중 order 찾기 실패");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		System.out.println("level: "+level+", idx: "+idx+", export: "+export);

		return export;
	}

	@Override
	public ReplyDto getReparentReplyNode(int boardId, int num, int order) {
		// TODO Auto-generated method stub
		int trans = getReplyOrderValue(boardId, num, order);
		System.out.println("res reply order: "+trans);
		ReplyDto dto = new ReplyDto();
		/*
		 * String  selectParentReplyQuery= "SELECT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, DEPTH, 
		 * REPLYTAB,
		 *  ID, CONTENT, REGDATE, DEL_FLAG FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? 
		 * AND NUM=? AND REPLY_ORDER=?
		 * 
		 * */
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectParentReplyQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, trans);
			
			rs  = ps.executeQuery();
			
			if(rs.next()) {
				dto.setBoardId(rs.getInt(1));
				dto.setReplyOrder(rs.getInt(2));
				dto.setNum(rs.getInt(3));
				dto.setLev(rs.getInt(4));
				dto.setLevSeq(rs.getInt(5));
				dto.setDepth(String.valueOf(rs.getInt(6)));
				dto.setReplyTab(rs.getInt(7));
				dto.setId(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setRegDate(sf.format(rs.getDate(10)));
				dto.setDelFlag(rs.getInt(11));

			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]Reparent 과정 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return dto;
	}

	@Override
	public int registerReReplyToArticle(ReplyDto reParent, ReplyDto child) {
		// TODO Auto-generated method stub
		int regRes = 0;
		int maxOrd = selectMaxOrderOfBoard();
		int maxSeq = selectMaxLevOrderOfBoard(reParent.getBoardId(), reParent.getNum(),reParent.getLev());
		String depth =child.getDepth();
		int  dep = 0;
		int  lev = reParent.getLev();
		
		System.out.println("max order: "+maxOrd);
		//levSeq 최댓값 찾기
	//	maxSeq  = selectMaxLevOrderOfBoard(child.getBoardId(), child.getNum(),child.getLev());
		System.out.println("부모노드의 시퀀스: "+maxSeq);
		
		
		if(depth==null) {
			dep=1;
		}else {
			dep=Integer.valueOf(depth);
		}
		

		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(insertReplyQuery);
			
			ps.setInt(1, reParent.getBoardId());
			ps.setInt(2, maxOrd+1);
			ps.setInt(3, reParent.getNum());
			ps.setInt(4, lev);
			ps.setInt(5, maxSeq+1);
			ps.setInt(6, dep+1);
			ps.setInt(7, reParent.getReplyTab()+1);
			ps.setString(8, child.getId());
			ps.setString(9, child.getContent());
			
			regRes = ps.executeUpdate();
			
			if(regRes >0) {
				commit(conn);
				System.out.println("댓글 작성 성공");
			}else {
				rollback(conn);
				System.out.println("댓글 작성 실패");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]댓글 작성 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return regRes;
	}

	@Override
	public int getMaxLevelInReplyBoard(int boardId, int num) {
		// TODO Auto-generated method stub
		int maxLev=0;
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectMaxLevelQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			
			rs    = ps.executeQuery();
			
			if(rs.next()) {
				maxLev = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]최대 레벨 찾기 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return maxLev;
	}

	//댓글 수정
	@Override
	public int updateReply(ReplyDto dto) {
		// TODO Auto-generated method stub
		/*
		 * UPDATE REPLY_ON_LOSTDETAIL SET CONTENT=? WHERE REPLY_ORDER=? AND ID=?
		 * */
		int upRes = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(updateReplyQuery);
			
			ps.setString(1, dto.getContent());
			ps.setInt(2, dto.getReplyOrder());
			ps.setString(3, dto.getId());
			
			upRes = ps.executeUpdate();
			
			if(upRes>0) {
				System.out.println("댓글 수정 성공");
				commit(conn);
			}else {
				System.out.println("댓글 수정 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]댓글 수정 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return upRes;
	}

	//댓글 삭제
	@Override
	public int deleteReply(int replyOrder) {
		// TODO Auto-generated method stub
		/*
		String deleteReplyQuery="UPDATE REPLY_ON_LOSTDETAIL SET DEL_FLAG=1 WHERE REPLY_ORDER=?";
		 */
		int delRes = 0;
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(deleteReplyQuery);
			
			ps.setInt(1, replyOrder);
			
			delRes = ps.executeUpdate();
			
			if(delRes > 0) {
				System.out.println("댓글 삭제 성공");
				commit(conn);
			}else {
				System.out.println("댓글 삭제 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]댓글 삭제");
		}finally {
			close(ps);
			close(conn);
		}
		
		return delRes;
	}

	@Override
	public ReplyDto selectReply(int replyOrder) {
		// TODO Auto-generated method stub
		ReplyDto dto = new ReplyDto();
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectReplyOneQuery);
			
			ps.setInt(1, replyOrder);
			rs   = ps.executeQuery();
			/*SELECT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, DEPTH, REPLYTAB, 
			 * ID, CONTENT, REGDATE, DEL_FLAG FROM REPLY_ON_LOSTDETAIL WHERE REPLY_ORDER=?*/
			if(rs.next()) {
				dto.setBoardId(rs.getInt(1));
				dto.setReplyOrder(rs.getInt(2));
				dto.setNum(rs.getInt(3));
				dto.setLev(rs.getInt(4));
				dto.setLevSeq(rs.getInt(5));
				dto.setDepth(String.valueOf(rs.getInt(6)));
				dto.setReplyTab(rs.getInt(7));
				dto.setId(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setRegDate(sf.format(rs.getDate(10)));
				dto.setDelFlag(rs.getInt(11));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]특정 댓글 하나만 order로 선택");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return dto;
	}

	@Override
	public int registerRootReply(ReplyDto dto) {
		// TODO Auto-generated method stub
		int regRoot =0;
		/*String  insertRootReplyQuery = "INSERT INTO REPLY_ON_LOSTDETAIL
		 * (BOARD_ID,REPLY_ORDER,NUM,LEV,LEV_SEQ,DEPTH,REPLYTAB,ID,CONTENT,REGDATE) 
		 * VALUES(?,?,?,0,1,?,0,?,?,?)";*/
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(insertRootReplyQuery);
			
			ps.setInt(1, dto.getBoardId());
			ps.setInt(2, dto.getReplyOrder());
			ps.setInt(3, dto.getNum());
			ps.setString(4, null);
			ps.setString(5, dto.getId());
			ps.setString(6, dto.getContent());
			ps.setString(7, dto.getRegDate());
			
			regRoot = ps.executeUpdate();
			
			if(regRoot > 0) {
				System.out.println("댓글 루트 설정 성공");
				commit(conn);
			}else {
				System.out.println("댓글 루트 설정 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]FAILED TO ROOT REPLY");
		}finally {
			close(ps);
			close(conn);
		}
		
		return regRoot;
	}

	@Override
	public int selectMaxNum() {
		// TODO Auto-generated method stub
		int resMax = 0;
		
		try {
			
			conn = getConnection();
			stat = conn.createStatement();
			
			rs   = stat.executeQuery(selectMaxNum);
			
			if(rs.next()) {
				resMax  = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]실종신고 등록 게시판 최댓값 조회 실패");
		}finally {
			close(rs);
			close(stat);
			close(conn);
		}
		
		return resMax;
	}

	@Override
	public int deleteLost(int boardId, LostDto dto) {
		// TODO Auto-generated method stub
		/*
		 * String deleteOriginLostArticleQuery="DELETE FROM REGLOST WHERE 
		 * AND NUM=? AND WRITER=?";//writer는 미연을 위함!
		 * 
		 * */
		int temp1 =0;
		int temp2 =0;
		int delRes =0;
		int num = dto.getNum();
		
		temp1 = deleteReplyCascade(boardId,num);
		
		try {
			
			conn = getConnection();
			ps    = conn.prepareStatement(deleteOriginLostArticleQuery);
			
			ps.setInt(1, num);
			ps.setString(2, dto.getWriter());
			
			temp2 = ps.executeUpdate();
			
			if(temp1 >0 && temp2>0) {
				System.out.println("원글 및 원글의 모든 댓글 삭제 성공");
				delRes = 1;
				commit(conn);
			}else {
				System.out.println("원글 및 원글의 모든 댓글 삭제 과정 오류");
				delRes = -1;
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]원글 삭제 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		
		return delRes;
	}

	@Override
	public int deleteReplyCascade(int boardId, int num) {
		// TODO Auto-generated method stub
		/*
		 * String deleteReplyByCascadeQuery="DELETE FROM REPLY_ON_LOSTDETAIL 
		 * WHERE BOARD_ID=? AND NUM=?
		 * 
		 * */
		int delRp = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(deleteReplyByCascadeQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			
			delRp = ps.executeUpdate();
			
			if(delRp > 0) {
				System.out.println("원글에 달린 댓글 모두 삭제 성공");
				//commit(conn);
			}else {
				System.out.println("댓글에 달린 댓글 모두 삭제 실패");
			//	rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]해당 원글의 모든 댓글 삭제 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		
		return delRp;
	}
	
	//해당 지역의 총 게시글 수 카운트하기
	@Override
	public int countProvLostArticle(String province) {
		// TODO Auto-generated method stub
		/*
		 * String countLostArticleByProvQuery=SELECT COUNT(NUM)
FROM (SELECT NUM, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, LOSTPLACE,
LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH
FROM   REGLOST
WHERE  PROVINCE=?);
		 * */
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(countLostArticleByProvQuery);
			
			ps.setString(1, province);
			
			rs  = ps.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]특정 지역 게시물 갯수 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return cnt;
	}

	@Override
	public int totalProvPage(String province) {
		// TODO Auto-generated method stub
		int temp1=countProvLostArticle(province);
		int tot  = 0;
		final int unit = 9;//9개씩 끊어서 보여줄것
		
		tot = (int)(Math.ceil((double)temp1/unit));
		
		return tot;
	}

	@Override
	public List<LostDto> selectLostArticleByProv(String province, int page) {
		// TODO Auto-generated method stub
		/*
		 * String searchLostArticleByProvQuery="SELECT P.* FROM 
		 * (SELECT ROWNUM R, PROV.* FROM (SELECT NUM, WRITER, TITLE, TEL, 
		 * LOSTDATE, PROVINCE, RESERV, LOSTPLACE,LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH FROM   
		 * REGLOST WHERE  PROVINCE=? ORDER  BY NUM DESC) PROV)P 
		 * WHERE P.R BETWEEN ? AND ?";
		 * */
		
		List<LostDto> list = new ArrayList<>();
		final int     unit = 9;
		int           stIdx= unit*page-8;//1,10,...
		int           finIdx=unit*page;//9,18,...
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(searchLostArticleByProvQuery);
			
			ps.setString(1, province);
			ps.setInt(2, stIdx);
			ps.setInt(3, finIdx);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LostDto dto = new LostDto();
				
				dto.setNum(rs.getInt(2));
				dto.setTitle(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setTel(rs.getString(5));
				dto.setLostDate(rs.getString(6));
				dto.setProvince(rs.getString(7));
				dto.setReserv(rs.getString(8));
				dto.setLostPlace(rs.getString(9));
				dto.setLostPic(rs.getString(10));
				dto.setDetail(rs.getString(11));
				dto.setSpecies(rs.getString(12));
				dto.setCate(rs.getString(13));
				dto.setEtc(rs.getString(14));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]지역별 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return list;
	}

	@Override
	public int countMyLostArticle(String writer) {
		// TODO Auto-generated method stub
		
		/*
		 * String  countMyLostArticleQuery="SELECT COUNT(NUM) FROM 
		 * (SELECT NUM, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, 
		 * LOSTPLACE,LOSTPIC, DETAIL, SPECIES, CATE, 
		 * ETC, WATCH FROM REGLOST WHERE WRITER=?)";
		 * 
		 * */
		int cnt = 0;
		
		try {
			
			conn  = getConnection();
			ps    = conn.prepareStatement(countMyLostArticleQuery);
			
			ps.setString(1, writer);
			
			rs    = ps.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]회원이 작성한 총 게시물 수 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		
		return cnt;
	}

	@Override
	public int totalMyLostArticlePage(String writer) {
		// TODO Auto-generated method stub
		int res = countMyLostArticle(writer);
		//9개씩 보여줄 것
		final int unit = 9;
		int tot = (int)(Math.ceil((double)res/unit));
		
		return tot;
	}

	@Override
	public List<LostDto> selectMyLostArticle(String writer, int page) {
		// TODO Auto-generated method stub
		/*
		 * String  searchMyLostArticleQuery ="SELECT DATA.* FROM (SELECT ROWNUM R, MINE.* 
		 * FROM (SELECT NUM, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, LOSTPLACE,
		 * LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH FROM REGLOST WHERE  WRITER=? ORDER  
		 * BY NUM DESC) MINE) 
		 * DATA WHERE  DATA.R BETWEEN ? AND ?";
		 * 
		 * */
		List<LostDto> list = new ArrayList<>();
		//9개씩 끊어서 보여줄 것
		final int     unit = 9;
		int           stIdx= unit*page-8;//1,10,...
		int           finIdx=unit*page;//9,18,...
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(searchMyLostArticleQuery);
			
			ps.setString(1, writer);
			ps.setInt(2, stIdx);
			ps.setInt(3, finIdx);
			
			rs   = ps.executeQuery();
			
			while(rs.next()) {
				LostDto dto = new LostDto();
				
				dto.setNum(rs.getInt(2));
				dto.setTitle(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setTel(rs.getString(5));
				dto.setLostDate(rs.getString(6));
				dto.setProvince(rs.getString(7));
				dto.setReserv(rs.getString(8));
				dto.setLostPlace(rs.getString(9));
				dto.setLostPic(rs.getString(10));
				dto.setDetail(rs.getString(11));
				dto.setSpecies(rs.getString(12));
				dto.setCate(rs.getString(13));
				dto.setEtc(rs.getString(14));
				
				list.add(dto);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]회원이 작성한 게시글 탐색 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return list;
	}
}
