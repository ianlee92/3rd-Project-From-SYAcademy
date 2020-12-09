package com.sist.dao;
import java.util.*;
/*
 * no NUMBER, --댓글번호
    bno NUMBER NOT NULL, --글번호
    name VARCHAR2(100),
    id VARCHAR2(20) NOT NULL, --글쓴이아이디
    msg CLOB NOT NULL, --댓글내용
    regdate DATE DEFAULT SYSDATE, --댓글작성일시
    group_id NUMBER,
    group_step NUMBER DEFAULT 0,
    group_tab NUMBER DEFAULT 0,
    root NUMBER DEFAULT 0,
    depth NUMBER DEFAULT 0
 */
public class DogReplyVO {
	private int no;
	private int bno;
	private String id;
	private String name;
	private String msg;
	private Date regdate;
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getGroup_step() {
		return group_step;
	}
	public void setGroup_step(int group_step) {
		this.group_step = group_step;
	}
	public int getGroup_tab() {
		return group_tab;
	}
	public void setGroup_tab(int group_tab) {
		this.group_tab = group_tab;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
}