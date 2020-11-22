package com.sist.tourstreet;

/*
 * 지명 : 아현동웨딩거리
주소 : 서울시 서대문구 북아현동 일대
시 : 서울특별시
구 : 서대문구
동 : 북아현동
X좌표 : 126.9545535618
Y좌표 : 37.5637990488
 */


// 서울시 관광거리 ====================================================================

public class SeoulTourStreetVO {
   private int no;
   private String name;
   private String addr;
   private String si;
   private String gu;
   private String dong;
   private String latitude;
   private String longitude;
   
   
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
   
   
}