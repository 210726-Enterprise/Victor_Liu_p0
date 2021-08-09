package com.revature.model;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.HashSet.RevaHashSet;
import com.revature.collection.RevArrayList;

public class BankAccount
{
    private RevaHashSet<UserAccount> owners;
    private double balance;
    private String accountType;
    private final int id;

    public BankAccount(int id, String accountType, double balance, RevaHashSet<UserAccount> owners)
    {
        this.owners = owners;
        this.balance = balance;
        this.accountType = accountType;
        this.id = id;
    }

    public BankAccount(int id, String accountType, double balance, UserAccount owner)
    {
        this.owners = new RevaHashSet<>();
        this.owners.add(owner);
        this.balance = balance;
        this.accountType = accountType;
        this.id = id;
    }

    public BankAccount(int id, String accountType, double balance)
    {
        this.owners = new RevaHashSet<>();
        this.owners = null;
        this.balance = balance;
        this.accountType = accountType;
        this.id = id;
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
        owners.add(owner);
    }

    public void removeOwner(UserAccount owner)
    {
        owners.remove(owner);
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

    public int getId()
    {
        return id;
    }
}
