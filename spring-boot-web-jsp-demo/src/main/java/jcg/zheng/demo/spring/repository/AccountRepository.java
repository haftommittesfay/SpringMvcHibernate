package jcg.zheng.demo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jcg.zheng.demo.spring.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT acct from Account acct WHERE acct.username = :username")
    Account findByUserName(@Param("username") String userName);
}
