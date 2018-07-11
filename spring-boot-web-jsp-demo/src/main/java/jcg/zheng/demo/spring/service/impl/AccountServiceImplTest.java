package jcg.zheng.demo.spring.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jcg.zheng.demo.spring.TestConfig;
import jcg.zheng.demo.spring.entity.Account;
import jcg.zheng.demo.spring.repository.AccountRepository;
import jcg.zheng.demo.spring.service.AccountService;
import jcg.zheng.demo.spring.util.TestData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class AccountServiceImplTest {

    @Autowired
    private AccountService classToTest;
    
    @Autowired
    private AccountRepository acctRepo;

    @Test
    public void findByUsername_not_found() {
        Account foundAcct = classToTest.findByUsername("testUser");
        assertNull(foundAcct);
    }

    @Test
    public void findByUsername_found() {
        
        acctRepo.save(TestData.createDummy("MaryZheng", "Mary", "Zheng"));
        Account foundAcct = classToTest.findByUsername("MaryZheng");
        assertNotNull(foundAcct);
        assertEquals(1, foundAcct.getId().intValue());
    }
}
