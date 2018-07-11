package jcg.zheng.demo.spring.entity;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import jcg.zheng.demo.spring.util.TestData;

public class AccountTest {

    @Test
    public void test_Account_getRewardPoint() {
        Account acct = TestData.createDummy("test", "firstNm", "lastNm");
        acct.addTransactions(TestData.createTransaction("payee", "desp", new BigDecimal(10)));
        acct.addTransactions(TestData.createTransaction("payee2", "desp2", new BigDecimal(15)));
        
        assertEquals(25, acct.getRewardPoint().intValue());
    }

}
