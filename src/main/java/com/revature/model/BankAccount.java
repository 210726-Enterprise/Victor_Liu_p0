package com.revature.model;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.HashSet.RevaHashSet;
import com.revature.collection.RevArrayList;

public class BankAccount
{
    private String accountNumber;
    private String routingNumber;
    private double balance;
    private String accountType;
    private String name;

    public BankAccount(String accountNumber, String routingNumber, double balance, String accountType, String name)
    {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.name = name;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getRoutingNumber()
    {
        return routingNumber;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public String getName()
    {
        return name;
    }
}
