package com.insys.trapps.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;

public interface InterviewService {

    Interview getInterview(long id);

    void updateFeedback(Feedback feedback);

}
