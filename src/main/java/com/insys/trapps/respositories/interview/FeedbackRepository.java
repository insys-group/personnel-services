package com.insys.trapps.respositories.interview;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
