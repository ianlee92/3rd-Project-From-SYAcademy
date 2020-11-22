package com.sist.tourstreet;

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
       
       SeoulTourStreetDAO dao = new SeoulTourStreetDAO();
       
       
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse("http://openapi.seoul.go.kr:8088/757058706673686834316a526d4570/xml/SebcTourStreetKor/1/135/");
    
        Element root = doc.getDocumentElement();
        
//        System.out.println(root.getTagName());
    
        NodeList list = root.getElementsByTagName("row");
        
        
        int no = 1;
        for(int i = 0; i < list.getLength(); i++) {
           Element element = (Element)list.item(i);
           System.out.println("번호 : " + no);
           System.out.println("지명 : " + element.getElementsByTagName("NAME_KOR").item(0).getTextContent());
           System.out.println("주소 : " + element.getElementsByTagName("ADD_KOR").item(0).getTextContent());
           System.out.println("시 : " + element.getElementsByTagName("LAW_SIDO").item(0).getTextContent());
           System.out.println("구 : " + element.getElementsByTagName("LAW_SGG").item(0).getTextContent());
           System.out.println("동 : " + element.getElementsByTagName("LAW_HEMD").item(0).getTextContent());
           System.out.println("X좌표 : " + element.getElementsByTagName("WGS84_X").item(0).getTextContent());
           System.out.println("Y좌표 : " + element.getElementsByTagName("WGS84_Y").item(0).getTextContent());
           System.out.println("\n ======================================================== \n");
           
//           SeoulTourStreetVO vo = new SeoulTourStreetVO();
//           vo.setNo(no);
//           vo.setName(element.getElementsByTagName("NAME_KOR").item(0).getTextContent());
//           vo.setAddr(element.getElementsByTagName("ADD_KOR").item(0).getTextContent());
//           vo.setSi(element.getElementsByTagName("LAW_SIDO").item(0).getTextContent());
//           vo.setGu(element.getElementsByTagName("LAW_SGG").item(0).getTextContent());
//           vo.setDong(element.getElementsByTagName("LAW_HEMD").item(0).getTextContent());
//           vo.setLatitude(element.getElementsByTagName("WGS84_X").item(0).getTextContent());
//           vo.setLongitude(element.getElementsByTagName("WGS84_Y").item(0).getTextContent());
//           
//           dao.musicInsert(vo);
           
           no++;
           
        }
        
        System.out.println("완료");
        
        
    }
}