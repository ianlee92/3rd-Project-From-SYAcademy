package com.sist.news;

import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.net.*;

import org.springframework.stereotype.Component;

@Component
public class NewsManager {

	public List<Item> newsAllData(String fd){
		
		// XML 파싱 방식 JAXB
		
		List<Item> list = new ArrayList<Item>();
		try {
			String strUrl = "http://newssearch.naver.com/search.naver?where=rss&query=" + URLEncoder.encode(fd,"UTF-8");		// 검색할 단어를 바이트 형식으로 인코딩해서 보내야 함
			URL url = new URL(strUrl);
			
			JAXBContext jb = JAXBContext.newInstance(Rss.class);		// 파싱을 시작하는 위치 (최상위 태그가 있는 위치)
			
			// XML을 자바 클래스로 변환
			Unmarshaller un = jb.createUnmarshaller();
			Rss rss = (Rss)un.unmarshal(url);
			list = rss.getChannel().getItem();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
