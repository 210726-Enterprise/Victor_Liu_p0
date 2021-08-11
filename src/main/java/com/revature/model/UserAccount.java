package com.revature.model;

import com.revature.collection.RevArrayList;

public class UserAccount
{
    private String username;
    private String password;
    private RevArrayList<BankAccount> accounts;
    private final int id;

    public UserAccount(int id, String username, String password, RevArrayList<BankAccount> accounts)
    {
        this.username = username;
        this.password = password;
        this.accounts = accounts;
        this.id = id;
    }

    public UserAccount(int id, String username, String password)
    {
        this.username = username;
        this.password = password;
        this.accounts = new RevArrayList<>();
        this.id = id;
    }

    public UserAccount(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.accounts = new RevArrayList<>();
        this.id = -1;
    }

    public boolean verifyCredentials(String username, String password)
    {
        if(username.equals(this.username) && password.equals(this.password))
            return true;
        return false;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public BankAccount getAccount(int accountNumber)
    {
        return accounts.get(accountNumber);
    }

    public void addAccount(BankAccount newAccount)
    {
        accounts.add(newAccount);
    }

    public void removeAccount(BankAccount oldAccount)
    {
        accounts.remove(oldAccount);
    }

    public double getBalanceOfAccount(int accountNumber)
    {
        return getAccount(accountNumber).getBalance();
    }

    public int getId()
    {
        return id;
    }
}
