package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.DogMapper;

@Repository
public class DogDAO {
	@Autowired
	private DogMapper mapper;
	
	// 메인 ////////////////////////////////////////////////////////
	
	// 메인 목록 읽기
	public List<DogVO> dogListData(Map map){
		return mapper.dogListData(map);
	}
	
	// 메인 총페이지 읽기
	public int dogTotalPage() {
		return mapper.dogTotalPage();
	}
	
	// 메인 조회수증가,상세보기
	public DogVO dogDetailData(int no) {
		mapper.dogHitIncrement(no);
		return mapper.dogDetailData(no);
	}
	
	// 메인 검색
	public List<DogVO> dogSearch(Map map){
		return mapper.dogSearch(map);
	}
	
	// 메인 검색페이지 수
	public int dogSearchPage(Map map) {
		return mapper.dogSearchPage(map);
	}
	
	// 메인 검색글 갯수
	public int dogSearchCount(Map map) {
		return mapper.dogSearchCount(map);
	}
	
	// 메인 난이도 검색
	public List<DogVO> dogStar(Map map){
		return mapper.dogStar(map);
	}
	
	// 메인 검색페이지 수
	public int dogStarPage(Map map) {
		return mapper.dogStarPage(map);
	}
		
	// 메인 검색글 갯수
	public int dogStarCount(Map map) {
		return mapper.dogStarCount(map);
	}
	
	// 메인 댓글 삽입
	public void dogInsertReply(DogReplyVO vo){
		try {
			mapper.dogInsertReply(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 메인 댓글작성시 게시글 댓글수 증가
	public void dogReplyIncrement(int no){
		try {
			mapper.dogReplyIncrement(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	// 메인 댓글 출력
	public List<DogReplyVO> dogListReply(int bno){	
		return mapper.dogListReply(bno);
	}
		
	// 메인 대댓글 작성
	public void dogReplyReplyInsert(int root, DogReplyVO vo){
		DogReplyVO parent_vo = mapper.dogReplyParentData(root);
		mapper.dogReplyStepIncrement(parent_vo);
		vo.setGroup_id(parent_vo.getGroup_id());
		vo.setGroup_step(parent_vo.getGroup_step() + 1);
		vo.setGroup_tab(parent_vo.getGroup_tab() + 1);
		vo.setRoot(root);
		
		mapper.dogReplyReplyInsert(vo);
		mapper.dogReplyDepthIncrement(root);
	}
		
	// 메인 댓글 수정
	public void dogUpdateReply(DogReplyVO vo){
		mapper.dogUpdateReply(vo);
	}
			
	// 메인 댓글 삭제
	public void dogDeleteReply(int no){
		try {
			DogReplyVO vo = mapper.dogInfoData(no);
			if(vo.getDepth()==0)
			   {
				   mapper.dogDeleteReply(no);
			   }
			   else
			   {
				   mapper.dogAdminMessage(no);
			   }
			mapper.dogDepthDecrement(vo.getRoot());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 게시판1 /////////////////////////////////////////////////////
	
	// 게시판1 목록 읽기
	public List<DogBoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	// 게시판1 총페이지 읽기
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	// 게시판1 조회수증가,상세보기
	public DogBoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	// 게시판1 추가
	public void boardInsert(DogBoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	// 게시판1 수정
	public void boardUpdate(Map map){
		mapper.boardUpdate(map);
	}
	
	// 게시판1 삭제 
	public void boardDelete(int no) {
		mapper.boardDelete(no);
	}
	
	// 게시판1 댓글 삽입
	public void boardInsertReply(DogBoardReplyVO vo){
		try {
			mapper.boardInsertReply(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 게시판1 댓글작성시 게시글 댓글수 증가
	public void boardReplyIncrement(int no){
		try {
			mapper.boardReplyIncrement(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	// 게시판1 댓글 출력
	public List<DogBoardReplyVO> boardListReply(int bno){	
		return mapper.boardListReply(bno);
	}
		
	// 게시판1 대댓글 작성
	public void boardReplyReplyInsert(int root, DogBoardReplyVO vo){
		DogBoardReplyVO parent_vo = mapper.boardReplyParentData(root);
		mapper.boardReplyStepIncrement(parent_vo);
		vo.setGroup_id(parent_vo.getGroup_id());
		vo.setGroup_step(parent_vo.getGroup_step() + 1);
		vo.setGroup_tab(parent_vo.getGroup_tab() + 1);
		vo.setRoot(root);
			
		mapper.boardReplyReplyInsert(vo);
		mapper.boardReplyDepthIncrement(root);
	}
			
	// 게시판1 댓글 수정
	public void boardUpdateReply(DogBoardReplyVO vo){
		mapper.boardUpdateReply(vo);
	}
				
	// 게시판1 댓글 삭제
	public void boardDeleteReply(int no){
		try {
			DogBoardReplyVO vo = mapper.boardInfoData(no);
			if(vo.getDepth()==0)
			   {
				   mapper.boardDeleteReply(no);
			   }
			   else
			   {
				   mapper.boardAdminMessage(no);
			   }
			mapper.boardDepthDecrement(vo.getRoot());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 익명게시판 /////////////////////////////////////////////////////
	
	// 익게 목록 읽기
	public List<DogAnonymousVO> anonyListData(Map map){
		return mapper.anonyListData(map);
	}
		
	// 익게 총페이지 읽기
	public int anonyTotalPage() {
		return mapper.anonyTotalPage();
	}
		
	// 익게 추가
	public void anonyInsert(DogAnonymousVO vo) {
		mapper.anonyInsert(vo);
	}
	
	// 익게 수정
	public void anonyUpdate(DogAnonymousVO vo){
		mapper.annoyUpdate(vo);
	}
	
	// 익게 삭제
	public void anonyDelete(int no){
		mapper.anonyDelete(no);
	}
	
}
