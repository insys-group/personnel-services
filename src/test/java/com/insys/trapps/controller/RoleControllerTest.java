package com.insys.trapps.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.Skill;
import com.insys.trapps.service.BusinessService;
import com.insys.trapps.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MockServletContext.class)
public class RoleControllerTest {
	
	protected MockMvc mvc;
	
	@InjectMocks
	RoleController roleController;

	protected ObjectMapper objectMapper;

	@BeforeClass
	static public void setup() {
	}

	@AfterClass
	static public void teardown() {
	}

	@Mock
	private RoleService roleService;

	private Role testRole;

	@Before
	public void setUpMock() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(roleController).build();
		objectMapper = new ObjectMapper();

		Set<Skill> skills = new HashSet<Skill>();
		skills.add(Skill.builder().name("Swift 3").build());
		skills.add(Skill.builder().name("Objective-C").build());
		
		testRole = Role.builder().name("iOS Developer").skills(skills).build();
	}
	
	@Test
	public void testListRoles() throws Exception {
		Mockito.when(roleService.listRoles()).then(new Answer() {
			@Override
			public List<Role> answer(InvocationOnMock invocation) throws Throwable {
				return Arrays.asList(testRole);
			}
		});

		MvcResult result = mvc.perform(get("/roles")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertNotNull(content);
	}
}
