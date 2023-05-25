package com.study.springboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
