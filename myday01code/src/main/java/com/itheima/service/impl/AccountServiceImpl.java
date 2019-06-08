package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.AccountService;

/**
 * @author Liu Shugong
 * @description AccountServiceImpl
 * @date 2019/5/24
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDaoImpl = new AccountDaoImpl();

    public void add() {
        accountDaoImpl.add();
    }
}
