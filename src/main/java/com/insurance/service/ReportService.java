package com.insurance.service;

import java.util.List;
import com.insurance.dynamicsearchdomain.SearchRequest;
import com.insurance.dynamicsearchdomain.SearchResponce;

public interface ReportService {
	
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<SearchResponce> searchPlans(SearchRequest searchRequest);

}
