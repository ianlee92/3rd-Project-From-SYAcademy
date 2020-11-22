package com.sist.ForeignResidence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Seoul_Foreign_ResidenceDAO {
   //      ϴ      
      private Connection conn;
      //     Ŭ   SQL      ϴ      
      private PreparedStatement ps;
      // URL
      private final String URL = "jdbc:oracle:thin:@211.238.142.195:1521:XE";

      
      //     ̹     
      public Seoul_Foreign_ResidenceDAO () {
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
      public void musicInsert(Seoul_Foreign_ResidenceVO vo) {   //  Ű        MusicVO  ϳ             ϱ 
         try {
            getConnection();   //     
            
//         Statement       ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ 
//            String sql = "INSERT INTO genie_music VALUES(" 
//                  + vo.getMno() + ",'" + vo.getTitle() + "','" + vo.getSinger() + "','"
//                  + vo.getAlbum() + "','" + vo.getPoster() + "','" + vo.getState() + "'," + vo.getIdcrement() + ",'" + vo.getKey() + "')";
            
            
         /*
          * no NUMBER,
             name VARCHAR2(100),
             addr VARCHAR2(100),
             si VARCHAR2(100),
             gu VARCHAR2(100),
             dong VARCHAR2(100),
             latitude VARCHAR2(100),
             longitude VARCHAR2(100)
          */
//         PreparedStatement       ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ 
            String sql = "INSERT INTO SEOUL_FOREIGN_RESIDENCE VALUES(?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, vo.getNo());
            ps.setString(2, vo.getName());
            ps.setString(3, vo.getAddr());
            ps.setString(4, vo.getSi());
            ps.setString(5, vo.getGu());
            ps.setString(6, vo.getDong());
            ps.setString(7, vo.getRoom());
            
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