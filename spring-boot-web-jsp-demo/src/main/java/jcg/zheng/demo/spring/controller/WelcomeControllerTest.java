package jcg.zheng.demo.spring.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import jcg.zheng.demo.spring.model.User;
import jcg.zheng.demo.spring.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class WelcomeControllerTest {
    
    @Autowired
    private WelcomeController welController;
    
    @Autowired
    private AccountService accountService;

    @Test
    public void welcome_view() {        
        Map model= new HashMap();
        Locale locale = new Locale.Builder().setLanguage("en").setRegion("MO").build();
        String viewName = welController.welcome(locale, model);
        assertEquals("home", viewName);
    }
    
    @Test
    public void invalidUser_view() {                
        
        User user = new User();
        user.setUserName("not exist");
        Model model = new BindingAwareModelMap();
        String viewName = welController.user(user , model );
        assertEquals("invalidUser", viewName);
    }
    
}
