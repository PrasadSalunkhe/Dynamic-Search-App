package com.insurance.dynamicsearchdomain;

import java.time.LocalDate;

import lombok.Data;
@Data
public class SearchResponce {
	
	private Long caseNo;
	private String planName;
	private String planStatus;
	private Double benefitAmt;
	private LocalDate startDate;
	private LocalDate endDate;
	private String denialReason;
	private String holderName;
	private String holderSSN;
}
