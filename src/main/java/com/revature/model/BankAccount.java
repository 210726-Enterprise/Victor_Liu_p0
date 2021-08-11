package com.revature.model;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.HashSet.RevaHashSet;
import com.revature.collection.RevArrayList;

/**
 * models a bank account
 */
public class BankAccount
{
    private String accountNumber;
    private String routingNumber;
    private double balance;
    private String accountType;
    private String name;

    public BankAccount(String accountNumber, String routingNumber, double balance, String accountType, String name) throws NegativeAmountException
    {
        if(balance < 0)
        {
            throw new NegativeAmountException();
        }
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.accountType = accountType;
        this.name = name;
    }

    /**
     * gets for the current balance
     * @return the current account balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * changes the account balance
     * @param balance the new balance
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    /**
     * gets the account number
     * @return the number of the account
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * gets the routing number
     * @return the routing number of the account
     */
    public String getRoutingNumber()
    {
        return routingNumber;
    }

    /**
     * gets the account type
     * @return the type of the account
     */
    public String getAccountType()
    {
        return accountType;
    }

    /**
     * gets the name of the account
     * @return the name of the account
     */
    public String getName()
    {
        return name;
    }
}
