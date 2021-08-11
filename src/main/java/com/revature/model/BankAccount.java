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
    private String name;

    public BankAccount(int id, String accountType, double balance, RevaHashSet<UserAccount> owners, String name) throws NegativeAmountException
    {
        if(balance < 0)
        {
            throw new NegativeAmountException();
        }
        else
        {
            this.balance = balance;
        }
        this.owners = owners;
        this.balance = balance;
        this.accountType = accountType;
        this.id = id;
        this.name = name;
    }

    public BankAccount(int id, String accountType, double balance, UserAccount owner, String name) throws NegativeAmountException
    {
        if(balance < 0)
        {
            throw new NegativeAmountException();
        }
        else
        {
            this.balance = balance;
        }
        this.owners = new RevaHashSet<>();
        this.owners.add(owner);
        this.balance = balance;
        this.accountType = accountType;
        this.id = id;
        this.name = name;
    }

    public BankAccount(int id, String accountType, double balance, String name) throws NegativeAmountException
    {
        if(balance < 0)
        {
            throw new NegativeAmountException();
        }
        else
        {
            this.balance = balance;
        }
        this.owners = new RevaHashSet<>();
        this.owners = null;
        this.accountType = accountType;
        this.id = id;
        this.name = name;
    }

    public BankAccount(String accountType, double balance, String name) throws NegativeAmountException
    {
        if(balance < 0)
        {
            throw new NegativeAmountException();
        }
        else
        {
            this.balance = balance;
        }
        this.owners = new RevaHashSet<>();
        this.owners = null;
        this.accountType = accountType;
        this.id = 0;
        this.name = name;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
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

    public RevaHashSet<UserAccount> getOwners()
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
