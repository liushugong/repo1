package com.itheima.test;


import com.itheima.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Liu Shugong
 * @description 测试类
 * @date 2019/5/24
 */
public class Test {

    @org.junit.Test
    public void test01() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.add();
    }
}
