package com.insys.trapps.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.respositories.interview.InterviewRepository;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {
	@Autowired
	private InterviewRepository repository;
	
	private Logger logger = Logger.getLogger(InterviewService.class);
	
	@Override
	public void patchInterview(Long id, Map<String, Object> mappedVals) {
		Interview dbInterview = repository.getOne(id);
		
		logger.debug(mappedVals);
		
//		dbInterview.getQuestions().clear();
//		dbInterview.getQuestions().forEach(question -> {
//			dbInterview.getQuestions().add(question);
//		});
		
		// repository.saveAndFlush(dbInterview);
		
		logger.debug("updateInterview called");
	}
}
