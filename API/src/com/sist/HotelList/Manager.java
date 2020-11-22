package com.sist.HotelList;

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
       
    	Seoul_Hotel_ListDAO dao = new Seoul_Hotel_ListDAO();
       
       
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse("http://openapi.seoul.go.kr:8088/757058706673686834316a526d4570/xml/SebcHotelListKor/1/159/");
    
        Element root = doc.getDocumentElement();
        
//        System.out.println(root.getTagName());
    
        NodeList list = root.getElementsByTagName("row");
        
        
        int no = 1;
        for(int i = 0; i < list.getLength(); i++) {
           Element element = (Element)list.item(i);
           System.out.println("번호 : " + no);
           System.out.println("상호명 : " + element.getElementsByTagName("NAME_KOR").item(0).getTextContent());
           System.out.println("주소 : " + element.getElementsByTagName("ADD_KOR_ROAD").item(0).getTextContent());
           System.out.println("\n ======================================================== \n");
           
           Seoul_Hotel_ListVO vo = new Seoul_Hotel_ListVO();
           vo.setNo(no);
           vo.setName(element.getElementsByTagName("NAME_KOR").item(0).getTextContent());
           vo.setAddr(element.getElementsByTagName("ADD_KOR_ROAD").item(0).getTextContent());
           
           dao.musicInsert(vo);
           
           Thread.sleep(100);
           
           no++;
           
        }
        
        System.out.println("완료");
        
        
    }
}