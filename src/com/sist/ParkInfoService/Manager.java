package com.sist.ParkInfoService;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;

 
public class Manager {
    public static void main(String[] args) throws Exception {
       
    	ParkDAO dao = new ParkDAO();
       
       
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse("http://openapi.seoul.go.kr:8088/466d716a4d69616e35306a4a574f57/xml/SearchParkInfoService/1/132/");
    
        Element root = doc.getDocumentElement();
        
//        System.out.println(root.getTagName());
    
        NodeList list = root.getElementsByTagName("row");
        
        /*
         * ps.setInt(1, vo.getNo());
          ps.setString(2, vo.getName());
          ps.setString(3, vo.getImg());
          ps.setString(4, vo.getContent());
          ps.setString(5, vo.getZone());
          ps.setString(6, vo.getAddr());
          ps.setString(7, vo.getVisit_road());
          ps.setString(8, vo.getLatitude());
          ps.setString(9, vo.getLongitude());
         */
        
        int no = 1;
        for(int i = 0; i < list.getLength(); i++) {
           Element element = (Element)list.item(i);
           System.out.println("번호 : " + no);
           System.out.println("공원명 : " + element.getElementsByTagName("P_PARK").item(0).getTextContent());
           System.out.println("이미지 : " + element.getElementsByTagName("P_IMG").item(0).getTextContent());
           System.out.println("내용 : " + element.getElementsByTagName("P_LIST_CONTENT").item(0).getTextContent());
           System.out.println("지역 : " + element.getElementsByTagName("P_ZONE").item(0).getTextContent());
           System.out.println("주소 : " + element.getElementsByTagName("P_ADDR").item(0).getTextContent());
           System.out.println("가는길 : " + element.getElementsByTagName("VISIT_ROAD").item(0).getTextContent());
           System.out.println("위도 : " + element.getElementsByTagName("LATITUDE").item(0).getTextContent());
           System.out.println("경도 : " + element.getElementsByTagName("LONGITUDE").item(0).getTextContent());
           System.out.println("\n ======================================================== \n");
           
           ParkVO vo = new ParkVO();
           vo.setNo(no);
           vo.setName(element.getElementsByTagName("P_PARK").item(0).getTextContent());
           vo.setImg(element.getElementsByTagName("P_IMG").item(0).getTextContent());
           vo.setContent(element.getElementsByTagName("P_LIST_CONTENT").item(0).getTextContent());
           vo.setZone(element.getElementsByTagName("P_ZONE").item(0).getTextContent());
           vo.setAddr(element.getElementsByTagName("P_ADDR").item(0).getTextContent());
           vo.setVisit_road(element.getElementsByTagName("VISIT_ROAD").item(0).getTextContent());
           vo.setLatitude(element.getElementsByTagName("LATITUDE").item(0).getTextContent());
           vo.setLongitude(element.getElementsByTagName("LONGITUDE").item(0).getTextContent());
           dao.parkInsert(vo);
           
           Thread.sleep(100);
           
           no++;
           
        }
        
        System.out.println("완료");
        
        
    }
}