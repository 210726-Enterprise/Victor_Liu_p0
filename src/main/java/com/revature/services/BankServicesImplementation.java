package com.revature.services;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.repo.BankAccountDAO;
import com.revature.repo.BankAccountDAOImplementation;

public class BankServicesImplementation implements BankServices
{
    private static BankAccountDAO bankAccountDAO = new BankAccountDAOImplementation();

    @Override
    public void makeDeposit(BankAccount account, double amount) throws NegativeAmountException
    {
        if(amount < 0)
        {
            throw new NegativeAmountException();
        }
        account.setBalance(account.getBalance()+amount);
        bankAccountDAO.updateBankAccount(account);
    }

    @Override
    public void makeWithdrawal(BankAccount account, double amount) throws NegativeAmountException, OverdraftException
    {
        if(amount < 0)
        {
            throw new NegativeAmountException();
        }
        if(account.getBalance() - amount < 0)
        {
            throw new OverdraftException();
        }
        account.setBalance(account.getBalance()-amount);
        bankAccountDAO.updateBankAccount(account);
    }

    @Override
    public void transfer(BankAccount accountToWithdraw, BankAccount accountToDeposit, double amount) throws NegativeAmountException, OverdraftException
    {
        if(amount < 0)
        {
            throw new NegativeAmountException();
        }
        if(accountToWithdraw.getBalance() - amount < 0)
        {
            throw new OverdraftException();
        }
        accountToWithdraw.setBalance(accountToWithdraw.getBalance()-amount);
        accountToDeposit.setBalance(accountToDeposit.getBalance()+amount);
        bankAccountDAO.updateBankAccount(accountToWithdraw);
        bankAccountDAO.updateBankAccount(accountToDeposit);
    }

    @Override
    public double viewBalance(BankAccount account)
    {

    }

    @Override
    public void addOwner(BankAccount account, UserAccount newOwner) {

    }

    @Override
    public void viewTransactionHistory(BankAccount account) {

    }
}