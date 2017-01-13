package com.insys.trapps.service;

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
	public void updateInterview(Long id, Interview interview) {
		// TODO Auto-generated method stub
		logger.debug("updateInterview called");
	}
}
