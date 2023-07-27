package com.insurance.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ELIGIBILITY_DTLS")
@Data
public class EligibilityDetailsEntity {

	@Id
	@GeneratedValue
	@Column(name="ELG_ID")
	private Integer elgId;
	@Column(name="CASE_NO")
	private Long caseNo;
	@Column(name="PLAN_NAME")
	private String planName;
	@Column(name="PLAN_STATUS")
	private String planStatus;
	@Column(name="BENIFIT_AMT")
	private Double benefitAmt;
	@Column(name="START_DATE")
	private LocalDate startDate;
	@Column(name="END_DATE")
	private LocalDate endDate;
	@Column(name="DENIAL_REASON")
	private String denialReason;
	@Column(name="HOLDER_NAME")
	private String holderName;
	@Column(name="HOLDER_SSN")
	private String holderSSN;
}
