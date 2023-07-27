package com.insurance.excelandpdfreport;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.insurance.dynamicsearchdomain.SearchResponce;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportGenerator {
	
	public void generatePdf(List<SearchResponce> responce, HttpServletResponse httpResponce) throws IOException {
	
	   Document doc = new Document();
	   PdfWriter writer = PdfWriter.getInstance(doc,httpResponce.getOutputStream());
	   //setting font family, color
	   Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, Color.RED);
	   doc.open();
	   Paragraph para = new Paragraph("Eligibility Details", font);
	   doc.add(para);
	   
	   PdfPTable table = new PdfPTable(9);
	      table.setWidthPercentage(100);
	      table.addCell("Sr.NO");
	      table.addCell("Holder Name");
	      table.addCell("Holder SSN");
	      table.addCell("Plan Name");
	      table.addCell("Plan Status");
	      table.addCell("Start Date");
	      table.addCell("End Date");
	      table.addCell("Benefit Amount");
	      table.addCell("Denial Reason");
	      
	      int srno=1;
	      for(SearchResponce record: responce) {
	    	  table.addCell(String.valueOf(srno));
	    	  table.addCell(record.getHolderName());
	    	  table.addCell(record.getHolderSSN());
	    	  table.addCell(record.getPlanName());
	    	  table.addCell(record.getPlanStatus());
	    	  table.addCell(String.valueOf(record.getStartDate()));
	    	  table.addCell(String.valueOf(record.getEndDate()));
	    	  table.addCell(String.valueOf(record.getBenefitAmt()));
	    	  table.addCell(record.getDenialReason());   
	    	  srno++;
	      }
	      
	      doc.add(table);
	      doc.close();
		  writer.close(); 
	 }
}


