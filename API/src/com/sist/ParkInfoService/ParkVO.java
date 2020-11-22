package com.sist.ParkInfoService;
/*
 * CREATE TABLE park(
	no NUMBER,
	name VARCHAR2(1000),
	img VARCHAR2(1000),
	content CLOB,
	zone VARCHAR2(100),
	addr VARCHAR2(1000),
	visit_road CLOB,
	latitude VARCHAR2(100),
	longitude VARCHAR2(100),
	star VARCHAR2(10),
	time VARCHAR2(10),
	regdate DATE DEFAULT SYSDATE,
	hit NUMBER DEFAULT 0,
	tag1 VARCHAR2(10),
	tag2 VARCHAR2(10),
	tag3 VARCHAR2(10)
);
 */
import java.util.*;
public class ParkVO {
	private int no, hit;
	private String name, img, content, zone, addr, visit_road,
	latitude, longitude, star, time, tag1, tag2, tag3;
	private Date regdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
