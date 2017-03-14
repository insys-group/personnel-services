package com.insys.trapps.respositories.interview;

import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by areyna on 2/16/17.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
