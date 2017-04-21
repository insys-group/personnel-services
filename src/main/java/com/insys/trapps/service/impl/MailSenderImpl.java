package com.insys.trapps.service.impl;

import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.security.User;
import com.insys.trapps.service.MailSender;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailSenderImpl implements MailSender {

	private org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(MailSenderImpl.class);

	@Autowired
	private JavaMailSender javaMailService;

	@Autowired
	private  Configuration  freemarkerConfiguration;

	private static final String DEFAULT_ENCODING = "utf-8";

	private static final Logger LOG = LoggerFactory.getLogger(MailSenderImpl.class);

	@Value("${spring.mail.cc}")
	public String MAIL_CC;

	@Value("${spring.mail.username}")
	public String MAIL_FROM;

	private void sendMail(String to, String subject, String text) {
		MimeMessage message = javaMailService.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(MAIL_FROM);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		javaMailService.send(message);
	}

	private void sendMail(String to, String[] cc, String subject, String text) {
		MimeMessage message = javaMailService.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(MAIL_FROM);
			helper.setTo(to);
			helper.setCc(cc);
			helper.setSubject(subject);
			helper.setText(text, true);
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		javaMailService.send(message);
	}

	private void sendMail(String[] to, String subject, String text) {
		MimeMessage message = javaMailService.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(MAIL_FROM);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		javaMailService.send(message);
	}

	private String generateContent(String templateName, Map context) throws MessagingException {
		try {
			Template template = freemarkerConfiguration.getTemplate(templateName, DEFAULT_ENCODING);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
		} catch (Exception e) {
			logger.error("FreeMarker template doesn't exist", e);
			throw new MessagingException("FreeMarker template doesn't exist", e);
		}
	}

	@Async
	public void sendEmailNewAccount(User user, Person person) {
		try {
			Map context = new HashMap();
			context.put("name", person.getFirstName() + " " + person.getLastName());
			context.put("user", user.getUsername());
			context.put("password", user.getPassword());
			String []cc = MAIL_CC.split(",");
			sendMail(person.getEmail(), cc, "New Account", generateContent("newAccount.ftl", context));
		} catch (final Exception e) {
			e.printStackTrace();
			LOG.error("The user was created successfully, however the email send out failed.");
		}

	}

	@Async
	public void sendEmailPasswordChanged(User user, Person person) {
		try {
			Map context = new HashMap();
			context.put("name", person.getFirstName() + " " + person.getLastName());
			context.put("user", user.getUsername());
			context.put("password", user.getPassword());
			String []cc = MAIL_CC.split(",");
			sendMail(person.getEmail(), cc, "Password Changed", generateContent("passwordChanged.ftl", context));
		} catch (final Exception e) {
			e.printStackTrace();
			LOG.error("The user was created successfully, however the email send out failed.");
		}

	}

	@Async
	public void sendEmailPasswordReset(User user, Person person) {
		try {
			Map context = new HashMap();
			context.put("name", person.getFirstName() + " " + person.getLastName());
			context.put("user", user.getUsername());
			context.put("password", user.getPassword());
			String []cc = MAIL_CC.split(",");
			sendMail(person.getEmail(), cc, "Password Reset", generateContent("passwordReset.ftl", context));
		} catch (final Exception e) {
			e.printStackTrace();
			LOG.error("The user was created successfully, however the email send out failed.");
		}

	}

}
