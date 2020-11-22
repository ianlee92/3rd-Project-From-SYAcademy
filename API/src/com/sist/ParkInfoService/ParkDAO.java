package com.sist.ParkInfoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import com.sist.ParkInfoService.ParkVO;

public class ParkDAO {
	private Connection conn;
    //     Ŭ   SQL      ϴ      
    private PreparedStatement ps;
    // URL
    private final String URL = "jdbc:oracle:thin:@211.238.142.195:1521:XE";
    //     ̹     
    public ParkDAO () {
       try {
          Class.forName("oracle.jdbc.driver.Oracle.Driver");
       } catch (Exception e) {
          System.out.println(e.getMessage());
       }
    }
    
    //     
    public void getConnection() {
       try {
          conn = DriverManager.getConnection(URL, "hr", "happy");
       } catch (Exception e) {
          System.out.println(e.getMessage());
       }
    }
    
    //     
    public void disConnection() {
       try {
          if(ps != null) {
             ps.close();
          }
          if(conn != null) {
             conn.close();
          }
       } catch (Exception e) {
          
       }
    }
    
    /*
    mno NUMBER(3),
    title VARCHAR2(300),
    singer VARCHAR2(100),
    album VARCHAR2(200),
    poster VARCHAR2(1000),
    state CHAR(6),
    idcrement NUMBER(3),
    key VARCHAR2(50)
     */
    
    //      ߰ 
    public void parkInsert(ParkVO vo) {   //  Ű        MusicVO  ϳ             ϱ 
       try {
          getConnection();   //     
          
//      no NUMBER,
//      name VARCHAR2(1000),
//    	img VARCHAR2(1000),
//    	content CLOB,
//    	zone VARCHAR2(100),
//    	addr VARCHAR2(1000),
//    	visit_road CLOB,
//    	latitude VARCHAR2(100),
//    	longitude VARCHAR2(100),
//    	star VARCHAR2(10),
//    	time VARCHAR2(10),
//    	regdate DATE DEFAULT SYSDATE,
//    	hit NUMBER DEFAULT 0,
//    	tag1 VARCHAR2(10),
//    	tag2 VARCHAR2(10),
//    	tag3 VARCHAR2(10)
// 
//       PreparedStatement       ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ 
          String sql = "INSERT INTO park VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
          ps = conn.prepareStatement(sql);
          ps.setInt(1, vo.getNo());
          ps.setString(2, vo.getName());
          ps.setString(3, vo.getImg());
          ps.setString(4, vo.getContent());
          ps.setString(5, vo.getZone());
          ps.setString(6, vo.getAddr());
          ps.setString(7, vo.getVisit_road());
          ps.setString(8, vo.getLatitude());
          ps.setString(9, vo.getLongitude());
          // SQL        ޱ 
          ps.executeUpdate();      // executeQuery :       ޾ƿ    (SELECT)      executeUpdate :          ,           (INSERT, UPDATE)
       } 
       
       
       catch (Exception e) {
          System.out.println(e.getMessage());
       }
       finally {
          disConnection();   //     
       }
    }
}
