package com.insurance.excelandpdfreport;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.insurance.dynamicsearchdomain.SearchResponce;

public class ExcelReportGenerator {

	public void generateExcel(List<SearchResponce> responce, HttpServletResponse httpResponce) throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Plans");
		HSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("Sr.No");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Holder SSN");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.createCell(6).setCellValue("End Date");
		headerRow.createCell(7).setCellValue("Benefit Amount");
		headerRow.createCell(8).setCellValue("Denial Reason");

		for (int i = 0; i < responce.size(); i++) {
			HSSFRow dataRow = sheet.createRow(i + 1);
			SearchResponce record = responce.get(i);
			dataRow.createCell(0).setCellValue(i + 1);
			dataRow.createCell(1).setCellValue(record.getHolderName());
			dataRow.createCell(2).setCellValue(record.getHolderSSN());
			dataRow.createCell(3).setCellValue(record.getPlanName());
			dataRow.createCell(4).setCellValue(record.getPlanStatus());
			dataRow.createCell(5).setCellValue(record.getStartDate());
			dataRow.createCell(6).setCellValue(record.getEndDate());
			dataRow.createCell(7).setCellValue(record.getBenefitAmt());
			dataRow.createCell(8).setCellValue(record.getDenialReason());
		}

		workbook.write(httpResponce.getOutputStream());
		workbook.close();
	}

}
