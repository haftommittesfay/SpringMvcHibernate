package jcg.zheng.demo.spring.service;

import jcg.zheng.demo.spring.entity.Account;

public interface AccountService {
    Account findByUsername(String username);
}
