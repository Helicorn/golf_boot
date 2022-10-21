package com.golforyou.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.golforyou.config.auth.PrincipalDetails;
import com.golforyou.service.MemberService;
import com.golforyou.service.MypageService;
import com.golforyou.vo.GolforyouMemberNEW;
import com.golforyou.vo.RankingVO;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class MypageController {

	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private MemberService memberService;
	

	//마이페이지 메인
	@GetMapping("mypage")
	public String mypage(PrincipalDetails principalDetails, HttpServletRequest request, HttpSession session) {
	
		return "mypage/main"; //뷰페이지 경로 WEB-INF/views/mypage/main.jsp		
	}//mypage()
	
//	//회원 정보 수정 
//	@RequestMapping("profile")
//	public String profile() {
//		return "mypage/profile"; 
//	}//profile()
//	
//	
	//회원 정보 수정 
	@RequestMapping("profile")
	public ModelAndView profile(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String id=(String)session.getAttribute("id");
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='login';");
			out.println("</script>");
		
		}else {
					
			GolforyouMemberNEW em=this.memberService.getMember(id); //아이디에 해당하는 회원정보를 dB로 부터 가져옴

			String m_phone =em.getMPhone();		
			String m_email =em.getMEmail();
			String m_addr = em.getMAddr();
			
			//ModelAndView m=new ModelAndView();
			
			System.out.println(m_phone);
			System.out.println(m_email);
			System.out.println(m_addr);
			
			//m.addObject("m",em);//m 키이름에 em객체 저장 
			
			//m.addObject("m_phone",m_phone);		
			//m.addObject("m_email",m_email);
			//m.addObject("m_addr",m_addr);
			
			//System.out.println(m.addObject("m_phone",m_phone));
		
		}
		
		return new ModelAndView("mypage/profile");

		
	}
	
	
	@RequestMapping("profileEdit_ok")
	public ModelAndView profileEdit_ok(GolforyouMemberNEW m, RankingVO r, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String saveFolder=request.getRealPath("/resources/upload");//이진파일 업로드 실제 경로를 구함.
		int fileSize=5*1024*1024; //이진파일 업로드 최대 크기 
		
		MultipartRequest multi=null;//첨부한 파일을 받을 참조변수 
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		String id=(String)session.getAttribute("id");
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='login';");
			out.println("</script>");
		
		}else {
			String m_phone=multi.getParameter("m_phone");
			String m_email=multi.getParameter("m_email");
			String m_addr=multi.getParameter("m_addr");
			
		File upFile=multi.getFile("m_file");//첨부할 파일을 가져온다. 
		if(upFile != null) {//첨부한 파일이 있는 경우
			String fileName=upFile.getName();//첨부한 파일명을 구함
			File delFile=new File(saveFolder+m.getMFile()); //삭제할 파일 객체 생성 
			if(delFile.exists()) {//삭제할 파일이 존재하면 
				delFile.delete();//기존 첨부파일 삭제 
			}
			Calendar cal=Calendar.getInstance();
			int year=cal.get(Calendar.YEAR);
			int month=cal.get(Calendar.MONTH)+1;
			int date=cal.get(Calendar.DATE);
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
			File path01=new File(homedir);
			if(!(path01.exists())) {path01.mkdir();}
			Random ran=new Random();
			int random=ran.nextInt(100000000);
			
			/*첨부파일 확장자 구함*/
			int index=fileName.lastIndexOf(".");
			//마침표를 맨 오른쪽부터 찾아서 가장 먼저 나오는 .의 위치 번호를  맨 왼쪽부터 카운터해서 반환 
			//첫 문자는 0부터 시작 
			String fileExtendsion=fileName.substring(index+1);//마침표 이후부터 마지막 문자까지 구함. 
			//즉 첨부파일  확장자를 구함. 
			String refileName="bbs"+year+month+date+random+"."+fileExtendsion;//새로운 파일명 저장 
			String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;//데이터베이스에 저장될 레코드 값
			upFile.renameTo(new File(homedir+"/"+refileName)); //생성된 폴더에 변경된 파일명으로 실제 업로드 
			
			m.setMFile(fileDBName);
			
			}else {//수정 첨부파일을 하지 않았을 때
				String fileDBName="";
				if(m.getMFile()!=null) {
					m.setMFile(m.getMFile());
												
				}else {
					m.setMFile(fileDBName);
				}
			}//수정 첨부파일을 첨부한 경우와 안한 경우 분기 조건문
		
		m.setUsername(id); m.setMPhone(m_phone); m.setMEmail(m_email); m.setMAddr(m_addr);
		
		this.mypageService.updateMember(m);//번호를 기준으로 글쓴이, 글제목, 글내용, 첨부파일 수정 
		this.mypageService.updateProvince(r);

	
		}
		return new ModelAndView("redirect:/mypage");
		
	}//profileEdit_ok()

	
	
	//비밀번호 수정 
	@RequestMapping("changepwd")
	public String changepwd() {
		return "mypage/pwdChange"; 
	}//profile()
	
	@RequestMapping("changepwd_ok")
	public String changepwd_ok(GolforyouMemberNEW m,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
					
		String id=(String)session.getAttribute("id");
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='login';");
			out.println("</script>");
		
		}else {
		
		}
		
		return "mypage/pwdChange";
	}//changepwd_ok()
		
	@RequestMapping("withdrawal")
	public String withdrawal() {
		return "mypage/withdrawal"; 
	}//withdrawal()
	
	@RequestMapping("withdrawal_ok")
	public String withdrawal_ok(GolforyouMemberNEW m,HttpServletRequest request, HttpServletResponse response, HttpSession session, String salt_pw) throws Exception{	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
					
		String id=(String)session.getAttribute("id");
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='login';");
			out.println("</script>");
		
		}else {
			
			String m_delcont=request.getParameter("m_delcont");
			String m_pw=request.getParameter("m_pw");

			
			m.setUsername(id); m.setMDelcont(m_delcont); 
			
			String salt = memberService.getSaltById(m.getUsername());
			System.out.println("salt:" +salt);
			
			salt_pw = SHA256Util.getEncrypt(m_pw, salt);
			System.out.println("dbSaltpw:" +salt_pw);
			m.setM_pw(salt_pw);
			
			this.memberService.getMember(m.getUsername());
			
			
			GolforyouMemberNEW db_pw=this.memberService.getMember(id); 
			//id와 비밀번호 대조를 위해 비밀번호 정보를 db에서 가져옴
				if(!db_pw.getPassword().equals(m.getPassword())) {
					out.println("<script>");
					out.println("alert('비밀번호를 다시 확인해주세요');");
					out.println("history.back();");
					out.println("</script>");
				}else {							
					this.mypageService.withdrawal(m_pw);
					out.println("window.close();");
				}
			 return "redirect:/"; 	
			
		}
		return null;
	}//withdrawal_ok()
	
}
