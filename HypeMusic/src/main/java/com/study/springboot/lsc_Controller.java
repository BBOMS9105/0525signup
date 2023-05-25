package com.study.springboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class lsc_Controller {

	// xlsx 파일 읽는 컨트롤러
	@RequestMapping("/signup")
	public String readWrite(Model model) {
				
		try {
			lsc_Functions lscFunctions = new lsc_Functions();
			List<User> userList = lscFunctions.getUserFromXlsx();
			model.addAttribute("userList", userList);
			System.out.println(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "lsc_signup";
		

	}
	
	// 입력받은 form 데이터 저장하는 컨트롤러
	@RequestMapping("/user")
	public String postUser(@ModelAttribute User user) {
	    try {
	        FileInputStream file = new FileInputStream(new File("C:\\Users\\User\\Desktop\\HypeMusic\\src\\main\\resources\\metadata\\user_test.xlsx"));

	        // 엑셀파일의 워크북 로드
	        XSSFWorkbook workbook = new XSSFWorkbook(file);

	        // 시트 선정
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        // 새로운 데이터가 들어가기 위해 엑셀 파일의 마지막 행 찾기
	        int rowNum = sheet.getLastRowNum();

	        // 새로운 열 생성 및 데이터 입력
	        // rank는 기본으로 1로 설정
	        Row row = sheet.createRow(rowNum + 1);
	        row.createCell(0).setCellValue(user.getId());
	        row.createCell(1).setCellValue(user.getPw());
	        row.createCell(2).setCellValue(user.getAge());
	        row.createCell(3).setCellValue(user.getPreference());
	        row.createCell(4).setCellValue(user.getEmail());
	        row.createCell(5).setCellValue(user.getNickname());
	        row.createCell(6).setCellValue(1);

	        // 바뀐 workbook을 파일에 작성
	        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\User\\Desktop\\HypeMusic\\src\\main\\resources\\metadata\\user_test.xlsx"));
	        workbook.write(out);
	        out.close();
	        workbook.close();
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "redirect:/success";
	}
	
	@RequestMapping("/success")
	public String success() {
		return "lsc_success";
	}
	
	// sign in
	@GetMapping("/signin")
	public String signin() {
		return "lsc_login";
	}
	
	// 로그인 처리용 컨트롤러
    @RequestMapping("/signin")
    public String login(@RequestParam("id") String id,
    					@RequestParam("pw") String pw,
    					HttpServletRequest req) throws IOException, ParseException {
    	// 세션 선언
    	HttpSession session = req.getSession();
    	lsc_Functions lscFunctions = new lsc_Functions();
        List<User> userList = lscFunctions.getUserFromXlsx();		// xlsx 에서 유저정보 읽어옴
                for (User user : userList) {
            if (user.getId().equals(id) && user.getPw().equals(pw)) {	// 만약 id, password가 일치한다면
            	
            	// 세션에 모든 정보 저장
            	session.setAttribute("id", id);
            	session.setAttribute("pw", pw);
            	session.setAttribute("age", user.getAge());
            	session.setAttribute("preference", user.getPreference());
            	session.setAttribute("email", user.getEmail());
            	session.setAttribute("nickname", user.getNickname());
            	session.setAttribute("rank", user.getRank());
            	System.out.println(user.getId()+" 로그인 성공, " + "rank : " + user.getRank());		// 동작 확인용
            	
                return "redirect:/main?success=true";	// 로그인 잘 됫는지 확인용으로 success=true 넣었습니다
            } 
            
            System.out.println("로그인 실패");
        } 

        return "redirect:/signin?error=true";		// 실패시 다시 로그인 페이지
    }
    
    // 로그아웃 컨트롤러
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate(); // 세션초기화
        System.out.println("로그아웃 성공");
        return "redirect:/main"; // 메인페이지로 리다이렉트
    }
    
    // mainpage
    @RequestMapping("/main")
    public String main() {
    	return "lsc_main";
    }
    
    
	
}
