package com.golforyou.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.golforyou.service.IndivService;
import com.golforyou.service.ScBoardService;
import com.golforyou.vo.ScboardVO;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class ScBoardController {
	@Autowired
	private ScBoardService scBoardService;
	@Autowired
	private IndivService indivService;
	
	//스코어카드게시판 목록
	@RequestMapping(value="/tier/scorecard_list")
	public String scorecard_list(Model listM, HttpServletRequest request, HttpServletResponse response, @ModelAttribute ScboardVO sb, HttpSession session) {
		response.setContentType("text/html;charest=utf-8");
		
		int page = 1;
		int limit = 10;
		String find_field = null;
		String find_name = null;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		find_field = request.getParameter("find_field");
		if(request.getParameter("find_name") != null) { //검색어가 있는 경우
			find_name = request.getParameter("find_name").trim(); //trim()으로 양쪽 공백 제거
		}
		sb.setFind_field(find_field);
		sb.setFind_name("%"+find_name+"%");
		
		int listcount = scBoardService.getListCount(sb);
		
		sb.setStartrow((page-1)*10+1);
		sb.setEndrow(sb.getStartrow()+limit-1);
		
		List<ScboardVO> sclist = scBoardService.getBoardList(sb);
		
		String id = (String)session.getAttribute("id");
		
		int maxpage = (int)((double)listcount/limit + 0.95); //총 페이지 수;
		int startpage = (((int)((double)page/10 + 0.9))-1)*10 + 1; //시작 페이지;
		int endpage = maxpage;
		
		if(endpage > startpage + 9) {
			endpage = startpage + 9;
		}
		
		listM.addAttribute("id", id);
		listM.addAttribute("sclist", sclist);
		listM.addAttribute("page", page);
		listM.addAttribute("startpage", startpage);
		listM.addAttribute("endpage", endpage);
		listM.addAttribute("maxpage", maxpage);
		listM.addAttribute("listcount", listcount);
		listM.addAttribute("find_field", find_field);
		listM.addAttribute("find_name", find_name);
		
		return "/tier/scorecard_list";
	}
	
	//스코어카드 게시판 내용보기(+수정,답변,삭제)
	@RequestMapping(value="/tier/scorecard_cont")
	public ModelAndView scorecard_cont(@RequestParam("sc_no") int sc_no, String state, int page, ScboardVO sb, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView scm = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		String sc_id = (String)session.getAttribute("id");
		
		page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		state = request.getParameter("state");
		
		if(state.equals("cont")) {
			sb = this.scBoardService.getScBoardCont(sc_no); //list에서 들어가면 조회수증가
		}else {
			sb = this.scBoardService.getScBoardCont2(sc_no); //다른데서 들어가면 조회수증가 없음
		}
		System.out.println(sb.getSc_cont());
		String sc_cont = sb.getSc_cont().replace("\n", "<br>");
		
		scm.addObject("id",sc_id);
		scm.addObject("page", page);
		scm.addObject("sb",sb);
		scm.addObject("sc_cont",sc_cont);
		
		if(state.equals("cont")) { //내용보기폼
			scm.setViewName("tier/scorecard_cont");
		}else if(state.equals("reply")){ //답변폼
			scm.setViewName("tier/scorecard_reply");
		}else if(state.equals("edit")) { //수정폼
			scm.setViewName("tier/scorecard_edit");
		}else if(state.equals("del")) { //삭제폼
			scm.setViewName("tier/scorecard_del");
		}
		
		return scm;
	}
	
	//스코어카드 글쓰기
	@RequestMapping(value="/tier/scorecard_write")
	public ModelAndView scorecard_write(HttpServletRequest request, HttpServletResponse response, int page, HttpSession session) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		ModelAndView swm = new ModelAndView("tier/scorecard_write");
		
		page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		PrintWriter out = response.getWriter();
		
		String sc_id = (String)session.getAttribute("id");
		
		if(sc_id == null) {
			out.println("<script>");
			out.println("alert('로그인부터 하세요')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			page = 1;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			return swm;
		}
		
		return null;
	}
	
	//스코어카드 글쓰기 저장
	@RequestMapping(value="/tier/scorecard_write_ok")
	public String scorecard_write_ok(ScboardVO sb, HttpServletResponse response, HttpServletRequest request, HttpSession session, int page) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String saveFolder = request.getRealPath("/resources/upload");
		
		page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int fileSize = 10*1024*1024; //이진파일 업로드 최대크기를 10mb로 제한
		MultipartRequest multi = null; //이진파일 업로드
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "utf-8");
		
		String sc_id = (String)session.getAttribute("id");
		String sc_title = multi.getParameter("sc_title");
		String sc_playdate = multi.getParameter("sc_playdate");
		sc_playdate = sc_playdate.replace("-", "_");
		String sc_cont = multi.getParameter("sc_cont");
		
		String fileoutput = null;
		
		File upfile = multi.getFile("sc_file");
		if(upfile != null) { //첨부파일 있는경우
			String fileName = upfile.getName(); //첨부한 파일명
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); //년도
			int month = c.get(Calendar.MONTH)+1; //1월이 0이라 +1
			int date = c.get(Calendar.DATE);
			
			String homedir = saveFolder+"/"+year+"-"+"0"+month+"-"+date; //폴더경로 저장
			File path01 = new File(homedir);
			if(!(path01.exists())) {
				path01.mkdir(); //풀더 생성
			}
			
			int index = fileName.lastIndexOf("."); //첨부파일에서 마침표 위치번호
			String fileExtendsion = fileName.substring(index+1); //마침표 이후부터 마지막 문자까지 구함(파일 확장자)
			String refileName = sc_playdate+"_"+sc_id+"."+fileExtendsion; //새로운 파일첨부명
			String fileDBName = "/"+year+"-"+"0"+month+"-"+date+"/"+refileName;
			upfile.renameTo(new File(homedir+"/"+refileName)); //새롭게 생성된 폴더 경로에 변경된 파일로 실제 업로드
			sb.setSc_file(fileDBName);
			fileoutput = fileName;
		}
		
		sb.setSc_id(sc_id);
		sb.setSc_title(sc_title);
		sb.setSc_playdate(sc_playdate);
		sb.setSc_cont(sc_cont);
		
		scBoardService.insertBoard(sb);
		indivService.autoInsert(sb);
		
		String strdate = indivService.makeDate(sb);
		int numdate = Integer.parseInt(strdate);
		sb.setNumdate(numdate);
		indivService.sortDate(sb);
		
		return "redirect:/scorecard_list?page="+page;
	}
	
	//답글 저장
	@RequestMapping(value="/tier/scorecard_reply_ok")
	public String scorecard_reply_ok(HttpServletRequest request, HttpServletResponse response, ScboardVO sb, int page) {
		page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//답변글 히든값 3개
		int sc_ref = Integer.parseInt(request.getParameter("sc_ref"));
		int sc_step = Integer.parseInt(request.getParameter("sc_step"));
		int sc_level = Integer.parseInt(request.getParameter("sc_level"));
		
		String sc_id = request.getParameter("sc_id");
		String sc_title = request.getParameter("sc_title");
		String sc_cont = request.getParameter("sc_cont");
		
		sb.setSc_ref(sc_ref);
		sb.setSc_step(sc_step);
		sb.setSc_level(sc_level);
		sb.setSc_id(sc_id);
		sb.setSc_title(sc_title);
		sb.setSc_cont(sc_cont);
		
		scBoardService.replyBoard(sb);
		
		return "redirect:/scorecard_list?page="+page;
	}
	
	//수정
	@RequestMapping(value="/tier/scorecard_edit_ok")
	public String scorecard_edit_ok(HttpServletRequest request, HttpServletResponse response, ScboardVO sb) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		
		String saveFolder = request.getRealPath("/resources/upload"); //이진파일 업로드 실제경로
		int fileSize = 10*1024*1024; //이진파일 업로드 최대크기
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request,saveFolder,fileSize,"utf-8");
		
		int sc_no = Integer.parseInt(multi.getParameter("sc_no"));
		int page = 1;
		if(multi.getParameter("page") != null) {
			page = Integer.parseInt(multi.getParameter("page"));
		}
		
		String sc_id = multi.getParameter("sc_id");
		String sc_playdate = multi.getParameter("sc_playdate");
		String sc_title = multi.getParameter("sc_title");
		String sc_cont = multi.getParameter("sc_cont");
		
		ScboardVO sb2 = scBoardService.getScBoardCont2(sc_no);
		
		File upfile = multi.getFile("sc_file");
		if(upfile != null) { //첨부파일 있는경우
			String fileName = upfile.getName(); //첨부한 파일명
			File delFile = new File(saveFolder+sb2.getSc_file()); //삭제할 파일 객체 생성
			if(delFile.exists()) { //삭제할 파일이 존재한다면
				delFile.delete(); //기존에 있던 파일 삭제
			}
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); //년도
			int month = c.get(Calendar.MONTH)+1; //1월이 0이라 +1
			int date = c.get(Calendar.DATE);
			
			String homedir = saveFolder+"/"+year+"-"+month+"-"+date; //폴더경로 저장
			File path01 = new File(homedir);
			if(!(path01.exists())) {
				path01.mkdir(); //풀더 생성
			}
			
			int index = fileName.lastIndexOf("."); //첨부파일에서 마침표 위치번호
			String fileExtendsion = fileName.substring(index+1); //마침표 이후부터 마지막 문자까지 구함(파일 확장자)
			String refileName = year+"_"+month+"_"+sc_id+"."+fileExtendsion; //새로운 파일첨부명
			String fileDBName = "/"+year+"-"+month+"-"+date+"/"+refileName;
			upfile.renameTo(new File(homedir+"/"+refileName)); //새롭게 생성된 폴더 경로에 변경된 파일로 실제 업로드
			sb.setSc_file(fileDBName);
		}		
		
		sb.setSc_no(sc_no);
		sb.setSc_id(sc_id);
		sb.setSc_playdate(sc_playdate);
		sb.setSc_title(sc_title);
		sb.setSc_cont(sc_cont);
		
		scBoardService.updateBoard(sb);
		
		return "redirect:/tier/scorecard_cont?sc_no="+sc_no+"&page="+page+"&state=cont";
	}
	
	//삭제
	@RequestMapping(value="/tier/scorecard_del_ok")
	public String scorecard_del_ok(int page,String del_pwd,HttpServletResponse response,HttpServletRequest request, ScboardVO sb) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		
		int sc_no = Integer.parseInt(request.getParameter("sc_no"));
		String sc_playdate = request.getParameter("sc_playdate");
		page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		HttpSession session = request.getSession();
		String sc_id = (String)session.getAttribute("id");
		
		sb.setSc_no(sc_no);
		sb.setSc_id(sc_id);
		sb.setSc_playdate(sc_playdate);
		
		scBoardService.delBoard(sb);
		
		return "redirect:/tier/scorecard_list?page="+page;
	}		
	
}
