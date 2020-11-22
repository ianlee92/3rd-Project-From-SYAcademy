package com.sist.ForeignResidence;

/*
 * 번호 : 50
상호명 : Min's House
주소 : 서울특별시 마포구 방울내로11길 173, B동 305호 (망원동, 문화빌라)
시 : 서울특별시
구 : 마포구
동 : 망원2동
객실수 : 2
 */


// 서울시 외국인관광 도시민박업 ====================================================================

public class Seoul_Foreign_ResidenceVO {
   private int no;
   private String name;
   private String addr;
   private String si;
   private String gu;
   private String dong;
   private String room;
   
   
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
   public String getAddr() {
      return addr;
   }
   public void setAddr(String addr) {
      this.addr = addr;
   }
   public String getSi() {
      return si;
   }
   public void setSi(String si) {
      this.si = si;
   }
   public String getGu() {
      return gu;
   }
   public void setGu(String gu) {
      this.gu = gu;
   }
   public String getDong() {
      return dong;
   }
   public void setDong(String dong) {
      this.dong = dong;
   }
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
   
   
   
   
}