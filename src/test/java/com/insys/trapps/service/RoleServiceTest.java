package com.insys.trapps.service;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.Skill;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.service.impl.RoleServiceImpl;
import com.insys.trapps.util.BusinessBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MockServletContext.class)
public class RoleServiceTest {

	@InjectMocks
	RoleServiceImpl roleService;

	protected ObjectMapper objectMapper;

	@BeforeClass
	static public void setup() {

	}

	@AfterClass
	static public void teardown() {

	}

	@Mock
	private RoleRepository roleRepository;

	private Role testRole;
	
	@Before
	public void setUpMock() throws Exception {
		MockitoAnnotations.initMocks(this);
		objectMapper = new ObjectMapper();
		
		Set<Skill> skills = new HashSet<Skill>();
		skills.add(Skill.builder().name("Swift 3").build());
		skills.add(Skill.builder().name("Objective-C").build());
		
		testRole = Role.builder().name("iOS Developer").skills(skills).build();
	}
	
	@Test
	public void testListRole() throws Exception {
		Mockito.when(roleRepository.findAll()).then(new Answer() {
			@Override
			public List<Role> answer(InvocationOnMock invocation) throws Throwable {
				return Arrays.asList(testRole);
			}
		});

		List<Role> role = roleService.listRoles();

		assertNotNull(role);
	}
	
}
