package com.insys.trapps.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Business;
import com.insys.trapps.respositories.BusinessRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BusinessRepoTest {

	private static final Logger logger = LoggerFactory.getLogger(BusinessRepoTest.class);

	@Autowired
	private TestEntityManager manager;
	@Autowired
	private BusinessRepository businessRepository;
	private Business testBusiness;

	@Before
	public void beforeEachMethod() {
	}
}
