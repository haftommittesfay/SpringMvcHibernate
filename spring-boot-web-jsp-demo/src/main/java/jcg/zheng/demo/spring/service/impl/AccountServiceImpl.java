package jcg.zheng.demo.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jcg.zheng.demo.spring.entity.Account;
import jcg.zheng.demo.spring.repository.AccountRepository;
import jcg.zheng.demo.spring.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository acctDao;

    @Override
    public Account findByUsername(String username) {
        return acctDao.findByUserName(username);
    }
}
