package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.UserAccount;

public class UserAccountDAO {
    private List<UserAccount> accounts;

    public UserAccountDAO() {
    }

    public UserAccountDAO(List<UserAccount> accounts) {
        this.accounts = accounts;
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserAccount> accounts) {
        this.accounts = accounts;
    }
}
