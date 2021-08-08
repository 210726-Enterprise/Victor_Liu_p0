package com.revature.model;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.RevArrayList;

public class UserAccount
{
    private String username;
    private String password;
    private RevArrayList<BankAccount> accounts;

    public UserAccount(String username, String password, RevArrayList<BankAccount> accounts)
    {
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }

    public boolean verifyCredentials(String username, String password)
    {
        if(username.equals(this.username) && password.equals(this.password))
            return true;
        return false;
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
}
