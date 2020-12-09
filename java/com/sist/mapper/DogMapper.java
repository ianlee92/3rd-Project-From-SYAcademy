package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import com.sist.dao.*;
public interface DogMapper {
	
	// 메인 /////////////////////////////////////////////////////////////
	
	// 메인 리스트 출력
	@Select("SELECT no,name,img,content,zone,addr,star,time,regdate,hit,num "
			+"FROM (SELECT no,name,img,content,zone,addr,star,time,regdate,hit,rownum as num "
			+"FROM (SELECT no,name,img,content,zone,addr,star,time,regdate,hit "
			+"FROM park ORDER BY name DESC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<DogVO> dogListData(Map map);
	
	// 메인 페이지당 8개로 잘랐을 때 총페이지 수
	@Select("SELECT CEIL(COUNT(*)/9.0) FROM park")
	public int dogTotalPage();
	
	// 메인 조회수 증가
	@Update("UPDATE park SET "
			+"hit=hit+1 "
			+"WHERE no=#{no}")
	public void dogHitIncrement(int no);
	
	// 메인 검색
	@Select("SELECT no,name,img,content,zone,addr,star,time,regdate,hit,num "
			+"FROM (SELECT no,name,img,content,zone,addr,star,time,regdate,hit,rownum as num "
			+"FROM (SELECT no,name,img,content,zone,addr,star,time,regdate,hit "
			+"FROM park WHERE name LIKE '%'||'${searchkey}'||'%' ORDER BY name DESC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<DogVO> dogSearch(Map map);
	
	// 메인 검색페이지 수
	@Select("SELECT CEIL(COUNT(*)/9.0) FROM park WHERE name LIKE '%'||'${searchkey}'||'%' ORDER BY name DESC")
	public int dogSearchPage(Map map);
	
	// 메인 검색글 갯수
	@Select("SELECT COUNT(*) FROM park WHERE name LIKE '%'||'${searchkey}'||'%' ORDER BY name DESC")
	public int dogSearchCount(Map map);
	
	// 난이도 별 검색
	@Select("SELECT no,name,img,content,zone,addr,star,time,regdate,hit,num "
			+"FROM (SELECT no,name,img,content,zone,addr,star,time,regdate,hit,rownum as num "
			+"FROM (SELECT no,name,img,content,zone,addr,star,time,regdate,hit "
			+"FROM park WHERE star=${star} ORDER BY name DESC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<DogVO> dogStar(Map map);
	
	// 난이도 검색페이지 수
	@Select("SELECT CEIL(COUNT(*)/9.0) FROM park WHERE star=${star} ORDER BY name DESC")
	public int dogStarPage(Map map);
		
	// 난이도 검색글 갯수
	@Select("SELECT COUNT(*) FROM park WHERE star=${star} ORDER BY name DESC")
	public int dogStarCount(Map map);
	
	// 메인상세 보기
	@Select("SELECT * FROM park WHERE no=#{no}")
	public DogVO dogDetailData(int no);

	// 메인 댓글 삽입
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1, 1) as no FROM park_reply")
	@Insert("INSERT INTO park_reply(no, bno, id, name, msg, group_id) "
			+"VALUES(#{no}, #{bno}, #{id}, #{name}, #{msg}, (SELECT NVL(MAX(group_id)+1,1) FROM park_reply))")
	public void dogInsertReply(DogReplyVO vo);
		
	// 메인 댓글수 증가
	@Update("UPDATE park SET reply = reply + 1 WHERE no = #{no}")
	public void dogReplyIncrement(int no);
		
	// 메인 댓글 출력
	@Select("SELECT no, bno, id, name, msg, regdate, group_tab FROM park_reply WHERE bno=#{bno} ORDER BY group_id DESC , group_step ASC")
	public List<DogReplyVO> dogListReply(int bno);
		
		
	// 메인 부모댓글 정보 가져오기
	@Select("SELECT group_id, group_step, group_tab FROM park_reply WHERE no = #{no}")
	public DogReplyVO dogReplyParentData(int no);	
		
	// 메인댓글 순서 증가
	@Update("UPDATE park_reply SET group_step = group_step + 1 WHERE group_id = #{group_id} AND group_step > #{group_step}")
	public void dogReplyStepIncrement(DogReplyVO vo);
			
	// 메인 대댓글
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1, 1) as no FROM park_reply")
	@Insert("INSERT INTO park_reply(no, bno, id, name, msg, group_id, group_step, group_tab, root, depth)"
			+"VALUES(#{no}, #{bno}, #{id}, #{name}, #{msg}, #{group_id}, #{group_step}, #{group_tab}, ${root}, 0)")
	public void dogReplyReplyInsert(DogReplyVO vo);
		
	// 메인 부모댓글 depth 증가
	@Update("UPDATE park_reply SET depth = depth + 1 WHERE no=#{no}")
	public void dogReplyDepthIncrement(int no);
		
	// 메인 댓글 수정
	@Update("UPDATE park_reply SET msg = #{msg} WHERE no = #{no}")
	public void dogUpdateReply(DogReplyVO vo);
		
	// 메인 댓글 삭제시 부모댓글 정보 가져오기
	@Select("SELECT depth, root FROM park_reply WHERE no = #{no}")
	public DogReplyVO dogInfoData(int no);
		
	// 메인 댓글 삭제
	@Delete("DELETE FROM park_reply WHERE no = #{no}")
	public void dogDeleteReply(int no);
			
	// 메인 댓글 삭제시 관리자 메시지 삽입
	@Update("UPDATE park_reply SET msg = '관리자가 삭제한 댓글입니다' WHERE no = #{no}")
	public void dogAdminMessage(int no);
			
	// 메인 부모댓글의 depth 감소
	@Update("UPDATE park_reply SET depth = depth - 1 WHERE no = #{no}")
	public void dogDepthDecrement(int no);
	
	
	// 게시판1 /////////////////////////////////////////////////////////////
	
	// 게시판1 리스트 출력
	@Select("SELECT no,subject,id,regdate,hit,poster,reply,num "
			+"FROM (SELECT no,subject,id,regdate,hit,poster,reply,rownum as num "
			+"FROM (SELECT no,subject,id,regdate,hit,poster,reply "
			+"FROM dog_board1 ORDER BY no DESC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<DogBoardVO> boardListData(Map map);
	
	// 게시판1 페이지당 8개로 잘랐을 때 총페이지 수
	@Select("SELECT CEIL(COUNT(*)/8.0) FROM dog_board1")
	public int boardTotalPage();
	
	// 게시판1 글번호 자동증가, 글쓰기 데이터추가
	@SelectKey(keyProperty="no",resultType=int.class,before=true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM dog_board1")
	@Insert("INSERT INTO dog_board1(no,subject,id,content,pwd,poster) "
			+"VALUES(#{no},#{subject},#{id},#{content},#{pwd},#{poster})")
	public void boardInsert(DogBoardVO vo);
	
	// 게시판1 조회수증가
	@Update("UPDATE dog_board1 SET "
			+"hit=hit+1 "
			+"WHERE no=#{no}")
	public void boardHitIncrement(int no);
	
	// 게시판1 상세보기
	@Select("SELECT * FROM dog_board1 WHERE no=#{no}")
	public DogBoardVO boardDetailData(int no);
	
	// 게시판1 수정
	@Update("UPDATE dog_board1 SET content=#{content}, subject=#{subject} WHERE no=#{no} AND pwd=#{pwd}")
	public void boardUpdate(Map map);
	
	// 게시판1 삭제 
	@Delete("DELETE FROM dog_board1 "
			+"WHERE no=#{no}")
	public void boardDelete(int no);
	
	// 게시판1 댓글 삽입
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1, 1) as no FROM dog_board_reply1")
	@Insert("INSERT INTO dog_board_reply1(no, bno, id, name, msg, group_id) "
			+"VALUES(#{no}, #{bno}, #{id}, #{name}, #{msg}, (SELECT NVL(MAX(group_id)+1,1) FROM dog_board_reply1))")
	public void boardInsertReply(DogBoardReplyVO vo);
			
	// 게시판1 댓글수 증가
	@Update("UPDATE dog_board1 SET reply = reply + 1 WHERE no = #{no}")
	public void boardReplyIncrement(int no);
				
	// 게시판1 댓글 출력
	@Select("SELECT no, bno, id, name, msg, regdate, group_tab FROM dog_board_reply1 WHERE bno=#{bno} ORDER BY group_id DESC , group_step ASC")
	public List<DogBoardReplyVO> boardListReply(int bno);
	
	// 게시판1 부모댓글 정보 가져오기
	@Select("SELECT group_id, group_step, group_tab FROM dog_board_reply1 WHERE no = #{no}")
	public DogBoardReplyVO boardReplyParentData(int no);
			
	// 게시판1댓글 순서 증가
	@Update("UPDATE dog_board_reply1 SET group_step = group_step + 1 WHERE group_id = #{group_id} AND group_step > #{group_step}")
	public void boardReplyStepIncrement(DogBoardReplyVO vo);
				
	// 게시판1 대댓글
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1, 1) as no FROM dog_board_reply1")
	@Insert("INSERT INTO dog_board_reply1(no, bno, id, name, msg, group_id, group_step, group_tab, root, depth)"
			+"VALUES(#{no}, #{bno}, #{id}, #{name}, #{msg}, #{group_id}, #{group_step}, #{group_tab}, ${root}, 0)")
	public void boardReplyReplyInsert(DogBoardReplyVO vo);
			
	// 게시판1 부모댓글 depth 증가
	@Update("UPDATE dog_board_reply1 SET depth = depth + 1 WHERE no=#{no}")
	public void boardReplyDepthIncrement(int no);
			
	// 게시판1 댓글 수정
	@Update("UPDATE dog_board_reply1 SET msg = #{msg} WHERE no = #{no}")
	public void boardUpdateReply(DogBoardReplyVO vo);
			
	// 게시판1 댓글 삭제시 부모댓글 정보 가져오기
	@Select("SELECT depth, root FROM dog_board_reply1 WHERE no = #{no}")
	public DogBoardReplyVO boardInfoData(int no);
			
	// 게시판1 댓글 삭제
	@Delete("DELETE FROM dog_board_reply1 WHERE no = #{no}")
	public void boardDeleteReply(int no);
				
	// 게시판1 댓글 삭제시 관리자 메시지 삽입
	@Update("UPDATE dog_board_reply1 SET msg = '관리자가 삭제한 댓글입니다' WHERE no = #{no}")
	public void boardAdminMessage(int no);
				
	// 게시판1 부모댓글의 depth 감소
	@Update("UPDATE dog_board_reply1 SET depth = depth - 1 WHERE no = #{no}")
	public void boardDepthDecrement(int no);
	
	// 게시판1 ajax
	@Select("SELECT no,name,img,content,zone,addr,star,time,regdate,hit,rownum FROM park "
			+"WHERE rownum<=12 AND zone LIKE '%'||#{gu}||'%'")
	public List<DogVO> dogLocationFind(String gu);
	
	// 익명게시판 /////////////////////////////////////////////////////////////

	// 익게 리스트 출력
	@Select("SELECT no,msg,regdate,ip,num "
			+"FROM (SELECT no,msg,regdate,ip,rownum as num "
			+"FROM (SELECT no,msg,regdate,ip "
			+"FROM dog_board3 ORDER BY no DESC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<DogAnonymousVO> anonyListData(Map map);
		
	// 익게 페이지당 10개로 잘랐을 때 총페이지 수
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM dog_board3")
	public int anonyTotalPage();
		
	// 익게 글번호 자동증가, 글쓰기 데이터추가
	@SelectKey(keyProperty="no",resultType=int.class,before=true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM dog_board3")
	@Insert("INSERT INTO dog_board3(no,msg,pwd,ip) "
			+"VALUES(#{no},#{msg},#{pwd},#{ip})")
	public void anonyInsert(DogAnonymousVO vo);
	
	// 익게 수정
	@Update("UPDATE dog_board3 SET msg = #{msg} WHERE no = #{no}")
	public void annoyUpdate(DogAnonymousVO vo);
	
	// 익게 삭제 
	@Delete("DELETE FROM dog_board3 WHERE no=#{no}")
	public void anonyDelete(int no);

}
