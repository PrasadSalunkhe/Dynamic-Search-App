package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insurance.entity.EligibilityDetailsEntity;

public interface EligibilityDtlsRepo extends JpaRepository<EligibilityDetailsEntity, Integer> {
	
	@Query("SELECT DISTINCT(planName) FROM EligibilityDetailsEntity")
	public List<String> getUniquePlanNames();
	
	@Query("SELECT DISTINCT(planStatus) FROM EligibilityDetailsEntity")
	public List<String> getUniquePlanStatus();

}
