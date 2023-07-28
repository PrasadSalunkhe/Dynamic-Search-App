package com.insurance.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insurance.dynamicsearchdomain.SearchRequest;
import com.insurance.dynamicsearchdomain.SearchResponce;
import com.insurance.entity.EligibilityDetailsEntity;
import com.insurance.repository.EligibilityDtlsRepo;
import com.insurance.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private EligibilityDtlsRepo elgDtlsrRepo;

	@Override
	public List<String> getPlanNames() {
		return elgDtlsrRepo.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return elgDtlsrRepo.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponce> searchPlans(SearchRequest searchRequest) {
		
		List<EligibilityDetailsEntity> elgRecords = null;
		if(isSearchRequestIsEmpty(searchRequest)) {
			 elgRecords = elgDtlsrRepo.findAll();		
		}
		else {
			String planName = searchRequest.getPlanName();
			String planStatus = searchRequest.getPlanStatus();
			LocalDate startDate = searchRequest.getStartDate();
			LocalDate endDate = searchRequest.getEndDate();
			
			EligibilityDetailsEntity entity=new EligibilityDetailsEntity();
			
			if(planName!=null && !planName.equals("")) {
				entity.setPlanName(planName);
			}
			if(planStatus!=null && !planStatus.equals("")) {
				entity.setPlanStatus(planStatus);
			}
			if(startDate!=null && endDate!=null) {
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}
			
			// If anything(planname,status etc) come into request that will be added to where clause
			// Example will prepare query dynamically
			Example<EligibilityDetailsEntity> of= Example.of(entity);
			elgRecords = elgDtlsrRepo.findAll(of);
			
			
		}
		List<SearchResponce> responceList= new ArrayList<>();
		for(EligibilityDetailsEntity elgrecord:elgRecords ) {
			SearchResponce sr= new SearchResponce();
			BeanUtils.copyProperties(elgrecord, sr);
			responceList.add(sr);
		}
		
		return responceList;
	}
	
	private boolean isSearchRequestIsEmpty(SearchRequest searchRequest) {
		if(searchRequest==null) {
			return true;
		}
		
		if(searchRequest.getPlanName()!=null && searchRequest.getPlanName().equals("")) {
			return false;
		}
		if(searchRequest.getPlanStatus()!=null && searchRequest.getPlanStatus().equals("")) {
			return false;
		}
		if(searchRequest.getStartDate()!=null && searchRequest.getStartDate().equals("")) {
			return false;
		}
		
		return true;
		
	}

}
