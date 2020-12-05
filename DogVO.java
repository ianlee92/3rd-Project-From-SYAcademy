package com.sist.dao;
/*
 * 이름         널? 유형             
---------- -- -------------- 
NO            NUMBER         
NAME          VARCHAR2(1000) 
IMG           VARCHAR2(1000) 
CONTENT       CLOB           
ZONE          VARCHAR2(100)  
ADDR          VARCHAR2(1000) 
VISIT_ROAD    CLOB           
LATITUDE      VARCHAR2(100)  
LONGITUDE     VARCHAR2(100)  
STAR          VARCHAR2(10)   
TIME          VARCHAR2(20)   
REGDATE       DATE           
HIT           NUMBER         
LOVE          NUMBER         
TAG1          VARCHAR2(10)   
TAG2          VARCHAR2(10)   
TAG3          VARCHAR2(10)   
ESWN          VARCHAR2(20)
 */
import java.util.*;
public class DogVO {
	private int no;
	private String name;
	private String img;
	private String content;
	private String zone;
	private String addr;
	private String visit_road;
	private String latitude;
	private String longitude;
	private String star;
	private String time;
	private Date regdate;
	private int hit;
	private int love;
	private String tag1;
	private String tag2;
	private String tag3;
	private String eswn;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getVisit_road() {
		return visit_road;
	}
	public void setVisit_road(String visit_road) {
		this.visit_road = visit_road;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	public String getTag3() {
		return tag3;
	}
	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
	public String getEswn() {
		return eswn;
	}
	public void setEswn(String eswn) {
		this.eswn = eswn;
	}
	
}
