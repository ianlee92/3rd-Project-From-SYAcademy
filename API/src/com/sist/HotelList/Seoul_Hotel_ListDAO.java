package com.sist.HotelList;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Seoul_Hotel_ListDAO {
   //      ϴ      
      private Connection conn;
      //     Ŭ   SQL      ϴ      
      private PreparedStatement ps;
      // URL
      private final String URL = "jdbc:oracle:thin:@211.238.142.195:1521:XE";

      
      //     ̹     
      public Seoul_Hotel_ListDAO () {
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
      
      
      public void musicInsert(Seoul_Hotel_ListVO vo) {    
         try {
            getConnection();   
            
            
         
            String sql = "INSERT INTO seoul_hotel_list VALUES(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, vo.getNo());
            ps.setString(2, vo.getName());
            ps.setString(3, vo.getAddr());
            
             
            ps.executeUpdate();      
         } 
         
         
         catch (Exception e) {
            System.out.println(e.getMessage());
         }
         finally {
            disConnection();   //     
         }
      }
}