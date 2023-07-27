package com.insurance.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dynamicsearchdomain.SearchRequest;
import com.insurance.dynamicsearchdomain.SearchResponce;
import com.insurance.excelandpdfreport.ExcelReportGenerator;
import com.insurance.excelandpdfreport.PdfReportGenerator;
import com.insurance.service.ReportService;

@RestController
public class ReportRestController {

	@Autowired
	private ReportService service;

	@GetMapping("/PlanNames")
	public List<String> getPlanNames() {
		return service.getPlanNames();
	}

	@GetMapping("/PlanStatuses")
	public List<String> getPlanStatuses() {
		return service.getPlanStatus();
	}

	@PostMapping("/search")
	public List<SearchResponce> getSearchResponces(@RequestBody SearchRequest request) {
		return service.searchPlans(request);
	}

	@PostMapping("/Excel")
	public void generateExcel(@RequestBody SearchRequest request, HttpServletResponse response) throws IOException {
		// Excel file should be download in browser
		// To send file in a browser as a response we need to set key,header
		response.setContentType("appliction/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.xls";
		response.setHeader(headerKey, headerValue);

		// We will get Only filtered records based on request object
		List<SearchResponce> records = service.searchPlans(request);

		// only for filtered records excel will generated
		ExcelReportGenerator excel = new ExcelReportGenerator();
		excel.generateExcel(records, response);
	}

	@PostMapping("/pdf")
	public void generatePDF(@RequestBody SearchRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("appliction/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.pdf";
		response.setHeader(headerKey, headerValue);
		List<SearchResponce> records = service.searchPlans(request);

		PdfReportGenerator pdf = new PdfReportGenerator();
		pdf.generatePdf(records, response);

	}
}
