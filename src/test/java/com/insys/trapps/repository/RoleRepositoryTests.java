package com.insys.trapps.repository;

import com.insys.trapps.TrappsApiApplication;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.Skill;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.util.RoleBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vnalitkin on 11/21/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrappsApiApplication.class)
@Slf4j
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    private List<Role> testRoleList = new ArrayList<>();

    /*
     * Initialize testRole1 (a subject) before every test method execution.
     */
    @Before
    public void beforeEachMethod() {
        testRoleList = Arrays.asList(
                RoleBuilder.buildRole("Role 1").build()
                , RoleBuilder.buildRole("Role 2").build()
        );
    }

    private void saveAll() {
        deleteAll();
        testRoleList.forEach(item -> roleRepository.save(item));
    }

    private void deleteAll() {
        roleRepository.deleteAll();
    }

    /*
     * Method to test RoleyRepository functionality for creating new Roles.
     */
    @Test
    public void testSave() throws Exception {
        log.debug("Enter: testSave " + roleRepository.getClass().toString());
        saveAll();

        roleRepository.findAll().forEach(item -> assertNotNull(item.getId()));
        Set<Role> rolesFromRepositorySet = new HashSet<>();
        roleRepository.findAll().forEach(rolesFromRepositorySet::add);
        testRoleList.containsAll(rolesFromRepositorySet);
        //TODO Need to fix this.
        /*
        rolesFromRepositorySet.forEach(item -> item
                .getSkills()
                .containsAll(testRoleList.get(testRoleList.indexOf(item)).getSkills())
        );*/
        rolesFromRepositorySet.forEach(item -> log.debug("Role : " + item.toString()));

        deleteAll();
    }

    /*
    * Method to test Repository functionality for update.
    */
    @Test
    public void testUpdate() throws Exception {
        log.debug("Enter: testUpdate " + roleRepository.getClass().toString());

        testRoleList.forEach(item -> item.setName(item.getName() + " upd"));
        saveAll();

        Set<Role> rolesFromRepositorySet = new HashSet<>();
        roleRepository.findAll().forEach(rolesFromRepositorySet::add);
        testRoleList.containsAll(rolesFromRepositorySet);
        //TODO Need to fix this.
        /*
        rolesFromRepositorySet.forEach(item -> {
                    assertTrue(testRoleList.indexOf(item) != -1);
                }
        );
         */
        rolesFromRepositorySet.forEach(item -> log.debug("Role : " + item.toString()));

        deleteAll();
    }

    @Test
    public void testUpdateSub() throws Exception {
        log.debug("Enter: testUpdateSub " + roleRepository.getClass().toString());
        saveAll();

        Role role = roleRepository.findByName("Role 1").get(0);
        Iterator<Skill> iterator = role.getSkills().iterator();
        Skill skill = iterator.next();
        iterator.remove();

        roleRepository.save(role);
        role = roleRepository.findByName("Role 1").get(0);
        log.debug("Finish: testUpdateSub " + roleRepository.getClass().toString());
        deleteAll();
    }
}