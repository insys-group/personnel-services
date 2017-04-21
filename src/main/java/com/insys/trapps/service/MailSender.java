package com.insys.trapps.service;

import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.security.User;
import org.springframework.stereotype.Service;

@Service
public interface MailSender {

	void sendEmailNewAccount(User user, Person person);

	void sendEmailPasswordChanged(User user, Person person);

	void sendEmailPasswordReset(User user, Person person);

}
