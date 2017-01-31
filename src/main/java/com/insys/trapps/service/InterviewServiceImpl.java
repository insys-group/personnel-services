package com.insys.trapps.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.interview.Question;
import com.insys.trapps.respositories.interview.InterviewRepository;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {
	@Autowired
	private InterviewRepository repository;
	
	private Logger logger = Logger.getLogger(InterviewService.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	@SuppressWarnings("unchecked")
	public void patchInterview(Long id, Map<String, Object> mappedVals) {
		Interview dbInterview = repository.findOne(id);
		
		if (dbInterview == null) {
			logger.error("Interview " + id + " not found");
			return;
		}
		
		logger.debug(dbInterview.getQuestions().size());
		
		if (mappedVals.get("questions") != null) {
			dbInterview.getQuestions().clear();
			dbInterview.getQuestions().addAll(
					extractQuestions((Map<String, Object>) mappedVals.get("questions")));
		}
		
		repository.saveAndFlush(dbInterview);
		
		logger.debug("updateInterview called");
	}
	
	@Override
	public Interview getInterview(Long id) {
		return repository.findOne(id);
	}
	
	private List<Question> extractQuestions(Map<String, Object> map) {
		List<Question> questions = new LinkedList<>();
		
		try {
			Iterator<String> iterator = map.keySet().iterator();
			while(iterator.hasNext()) {
				questions.add(mapper.readValue(
						mapper.writeValueAsString(map.get(iterator.next())), 
						Question.class));
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		return questions;
	}
}
