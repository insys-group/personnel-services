package com.insys.trapps.model.interview;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.Role;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by areyna on 2/14/17.
 */
@Projection(name = "inline", types = { Interview.class })
public interface InterviewInlineProjection {

    Long getId();

    Date getDate();

    String getName();

    String getPhone();

    Person getCandidate();

    Role getRole();

    Set<Person> getInterviewers();

    Set<Question> getQuestions();

    Set<Feedback> getFeedbacks();

}
