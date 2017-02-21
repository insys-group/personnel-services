package com.insys.trapps.service.impl;

import java.io.IOException;
import java.util.*;

import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.respositories.interview.FeedbackRepository;
import com.insys.trapps.service.InterviewService;
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
    private InterviewRepository interviewRepository;

    @Autowired
	private FeedbackRepository feedbackRepository;

    private Logger logger = Logger.getLogger(InterviewService.class);

    public Interview getInterview(long id) {
        return interviewRepository.findOne(id);
    }

    public void updateFeedback(Feedback feedback) {
        Feedback existingFeedback = feedbackRepository.findOne(feedback.getId());
        existingFeedback.setComment(feedback.getComment());
        feedbackRepository.saveAndFlush(feedback);
    }

}
