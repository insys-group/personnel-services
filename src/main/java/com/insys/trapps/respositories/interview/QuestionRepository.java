package com.insys.trapps.respositories.interview;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insys.trapps.model.interview.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
