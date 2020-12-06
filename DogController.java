package com.sist.web;
import java.io.File;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.sist.dao.*;
import com.sist.naver.NaverCafeManager;
import com.sist.utils.UploadFileUtils;

@Controller
public class DogController {
	@Autowired
	private DogDAO dao;
	
	@Autowired
	private NaverCafeManager ncm;
	
	@Autowired
	private RManager_park rmp;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	// 메인 ////////////////////////////////////////////////////////
	
	@RequestMapping("dog/parkmain.do")
	public String park_main(String page, Model model){
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=9;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=dao.dogTotalPage();
		map.put("start", start);
		map.put("end", end);
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		List<DogVO> list=dao.dogListData(map);
		for(DogVO vo:list){
            String s=vo.getContent();
            if(s.length()>100){
                s=s.substring(0,100)+"..";
                vo.setContent(s);
            }
        }
		for(DogVO vo:list){
            String s=vo.getName();
            if(s.length()>8){
                s=s.substring(0,8)+"..";
                vo.setName(s);
            }
        }
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("BLOCK", BLOCK);
		return "dog/parkmain";
	}
	
	// 메인 검색 
	@RequestMapping("dog/parksearch.do")
	public String park_search(HttpServletRequest request, String page, Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		String searchkey=request.getParameter("searchkey");
		Map map=new HashMap();
		int rowSize=9;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		map.put("start", start);
		map.put("end", end);
		map.put("searchkey", searchkey);
		int searchcount=dao.dogSearchCount(map);
		int totalpage=dao.dogSearchPage(map);
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		List<DogVO> list=dao.dogSearch(map);
		for(DogVO vo:list){
            String s=vo.getContent();
            if(s.length()>100){
                s=s.substring(0,100)+"..";
                vo.setContent(s);
            }
        }
		for(DogVO vo:list){
            String s=vo.getName();
            if(s.length()>8){
                s=s.substring(0,8)+"..";
                vo.setName(s);
            }
        }
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("BLOCK", BLOCK);
		model.addAttribute("searchkey",searchkey);
		model.addAttribute("searchcount",searchcount);
		return "dog/parksearch";
	}
	// 상세 쿠키
	@RequestMapping("dog/park_before.do")
	public String park_detail_before(HttpServletRequest request, HttpServletResponse response) {
		String no="";
		try {
			no=request.getParameter("no");
			HttpSession session=request.getSession();
			String id=(String)session.getAttribute("id");
			Cookie cookie=new Cookie(id+"park"+no, no);
			cookie.setMaxAge(180);
			cookie.setPath("/");
			response.addCookie(cookie);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "redirect:../dog/parkdetail.do?no="+no;
	}
		
	// 메인 상세
	@RequestMapping("dog/parkdetail.do")
	public String park_detail(String no, Model model, HttpSession session, HttpServletRequest request){
		try {
			DogVO vo=dao.dogDetailData(Integer.parseInt(no));
			if(session.getAttribute("id")!=null) {
			session=request.getSession();
			String id=(String)session.getAttribute("id");
			// 쿠키 읽기
			List<DogVO> cList=new ArrayList<DogVO>();
			Cookie[] cookies=request.getCookies();
			if(cookies!=null){
				for(int i=cookies.length-1;i>=0;i--){
					if(cookies[i].getName().startsWith(id+"park")){
						String cno=cookies[i].getValue();
						DogVO cvo=dao.dogDetailData(Integer.parseInt(cno));
						cList.add(cvo);
					}
				}
			}
			//request.setAttribute("cList", cList);	
			model.addAttribute("cList", cList);
			}
			ncm.naverData(vo.getName());
			rmp.graph(Integer.parseInt(no));
			model.addAttribute("vo", vo);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return "dog/parkdetail";
	}
	
	// 메인 난이도 별 검색
	@RequestMapping("dog/parkstar.do")
	public String park_star(HttpServletRequest request, String page, Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		String star=request.getParameter("star");
		int parkstar=Integer.parseInt(star);
		Map map=new HashMap();
		int rowSize=9;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		map.put("start", start);
		map.put("end", end);
		map.put("star", star);
		int starcount=dao.dogStarCount(map);
		int totalpage=dao.dogStarPage(map);
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		List<DogVO> list=dao.dogStar(map);
		for(DogVO vo:list){
            String s=vo.getContent();
            if(s.length()>100){
                s=s.substring(0,100)+"..";
                vo.setContent(s);
            }
        }
		for(DogVO vo:list){
            String s=vo.getName();
            if(s.length()>8){
                s=s.substring(0,8)+"..";
                vo.setName(s);
            }
        }
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("BLOCK", BLOCK);
		model.addAttribute("parkstar",parkstar);
		model.addAttribute("starcount",starcount);
		return "dog/parkstar";
	}
	
	// 게시판1 /////////////////////////////////////////////////////
	
	// 게시판1 목록 출력
	
	@RequestMapping("dogboard/list.do")
	public String dog_board_list(String page, Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=8;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=dao.boardTotalPage();
		map.put("start", start);
		map.put("end", end);
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		List<DogBoardVO> list=dao.boardListData(map);
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("BLOCK", BLOCK);
		return "dogboard/list";
	}
	
	// 게시판1 글쓰기 폼
	
	@RequestMapping("dogboard/insert.do")
	public String dog_board_insert(HttpSession session, HttpServletRequest request) {
		session=request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("id : " + id);
		return "dogboard/insert";
	}
	
	// 게시판1 글쓰기
	
	@RequestMapping("dogboard/insert_ok.do")
	public String dog_board_insert_ok(@ModelAttribute("vo") DogBoardVO vo, HttpSession session, HttpServletRequest request, MultipartFile file) {
		try {
		session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("id : " + id);
		vo.setId(id);
		//String uploadPath = "C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/3rdTeamProject/resources";
		String uploadPath = "/Users/yongpro/SpringDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/3rdTeamProject/";
//		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String imgUploadPath = uploadPath + File.separator;
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			  // 파일 인풋박스에 첨부된 파일이 없다면(=첨부된 파일이 이름이 없다면)
			  
			  fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);

			  // poster에 원본 파일 경로 + 파일명 저장
			  vo.setPoster(ymdPath + File.separator + fileName);
			  // filename에 썸네일 파일 경로 + 썸네일 파일명 저장
			  vo.setFilename("..\\resources" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
			  
			 } else {  // 첨부된 파일이 없으면
			  vo.setPoster("/images/dog_8.jpg");
			 }

		dao.boardInsert(vo);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "redirect:list.do#yong";
	}
	
	// 게시판1 상세보기
	
	@RequestMapping("dogboard/detail.do")
	public String dog_board_detail(String no, Model model) {
		DogBoardVO vo=dao.boardDetailData(Integer.parseInt(no));
		model.addAttribute("vo", vo);
		return "dogboard/detail";
	}
	
	// 게시판1 삭제하기
	
	@RequestMapping("dogboard/delete.do")
	public String diaryDelete(String no){
		dao.boardDelete(Integer.parseInt(no));
		return "redirect:list.do#yong";
	}
	
	// 익명게시판 /////////////////////////////////////////////////////
	
	@RequestMapping("dogboard/anonymous.do")
	public String dog_anonyboard_list(String page, Model model) {
		if(page==null)
			page="1";
		Map map=new HashMap();
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=dao.anonyTotalPage();
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		map.put("start", start);
		map.put("end", end);
		List<DogAnonymousVO> list=dao.anonyListData(map);
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("BLOCK", BLOCK);
		return "dogboard/anonymous";
	}
		
	@PostMapping("dogboard/ano_insert_ok.do")
	public String dog_board_insert_ok(@ModelAttribute("vo") DogAnonymousVO vo) {
		dao.anonyInsert(vo);
		return "redirect:anonymous.do#yong";
	}
		
}
