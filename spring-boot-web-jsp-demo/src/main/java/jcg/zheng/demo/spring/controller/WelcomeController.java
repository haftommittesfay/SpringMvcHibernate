package jcg.zheng.demo.spring.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jcg.zheng.demo.spring.entity.Account;

import jcg.zheng.demo.spring.model.User;
import jcg.zheng.demo.spring.repository.AccountRepository;
import jcg.zheng.demo.spring.service.AccountService;
import jcg.zheng.demo.spring.util.TestData;

@Controller
public class WelcomeController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository acctRep;

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Locale locale, Map model) {
        model.put("message", this.message);
        System.out.println("Home Page Requested, locale = " + locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.put("serverTime", formattedDate);
        return "home";
    }

    @RequestMapping("/loadTestData")
    public String setup() {
        Account maryZheng = TestData.createDummy("maryZheng", "Mary", "Zheng");
        maryZheng.addTransactions(TestData.createTransaction("KOHL", "Birthday gifts", new BigDecimal(300)));
        maryZheng.addTransactions(TestData.createTransaction("Macy", "Allen clothes", new BigDecimal(100)));
        maryZheng.addTransactions(TestData.createTransaction("Home Depot", "house items", new BigDecimal(1000)));
        maryZheng.addTransactions(TestData.createTransaction("Wal-mart", "small items", new BigDecimal(60)));
        acctRep.save(maryZheng);

        Account demo = TestData.createDummy("demo", "Demo", "JCG");
        demo.addTransactions(TestData.createTransaction("Shop&Save", "food items", new BigDecimal(60)));
        demo.addTransactions(TestData.createTransaction("Webster", "school supplies", new BigDecimal(260)));
        acctRep.save(demo);

        return "home";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String user(@Validated User user, Model model) {
        System.out.println("User Page Requested");
        model.addAttribute("userName", user.getUserName());

        Account foundUser = accountService.findByUsername(user.getUserName());
        if (foundUser != null) {
            model.addAttribute("account", foundUser);
            return "user";
        } else {
            return "invalidUser";
        }
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest request, Exception ex, Model model) {
        model.addAttribute("content", request.getRequestURL());
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

}