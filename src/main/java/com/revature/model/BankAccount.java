package com.revature.model;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.RevArrayList;

public class BankAccount
{
    private RevArrayList<UserAccount> owners;
    private double balance;
    private String accountType;

    public BankAccount(RevArrayList<UserAccount> owners, double balance, String accountType)
    {
        this.owners = owners;
        this.balance = balance;
        this.accountType = accountType;
    }

    public void deposit(double amount) throws NegativeAmountException
    {
        if(amount < 0)
        {
            throw new NegativeAmountException();
        }
        balance += amount;
    }

    public void withdraw(double amount) throws OverdraftException, NegativeAmountException
    {
        if(amount < 0)
        {
            throw new NegativeAmountException();
        }
        if(balance - amount < 0)
        {
            throw new OverdraftException();
        }
        balance -= amount;
    }

    public void addOwner(UserAccount owner)
    {

    }

    public void removeOwner(UserAccount owner)
    {

    }

    public double getBalance()
    {
        return balance;
    }

    public RevArrayList<UserAccount> getOwners()
    {
        return owners;
    }

    public String getAccountType()
    {
        return accountType;
    }
}
