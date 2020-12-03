package com.sist.dao;
/*
 * no NUMBER 
    id VARCHAR2(20) 
    subject VARCHAR2(100)
    content CLOB 
    pwd VARCHAR2(20) 
    regdate DATE 
    hit NUMBER 
    poster VARCHAR2(100), --이미지
    filename VARCHAR2(1000),
    filesize VARCHAR2(1000),
    filecount NUMBER
 */
import java.util.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DogBoardVO {
	private int no;
	@NotNull
	private String id;
	@NotNull
	private String subject;
	@NotNull
	private String content;
	@NotNull
	@Size(min=4,max=20)
	private String pwd;
	private Date regdate;
	private int hit;
	private String poster;
	private String filename;
	private String filesize;
	private int filecount;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public int getFilecount() {
		return filecount;
	}
	public void setFilecount(int filecount) {
		this.filecount = filecount;
	}
	
}
