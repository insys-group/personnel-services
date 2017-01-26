package com.insys.trapps.service;

import java.util.Map;

import com.insys.trapps.model.interview.Interview;

public interface InterviewService {
	void patchInterview(Long id, Map<String, Object> mappedVals);
	
	Interview getInterview(Long id);
}
