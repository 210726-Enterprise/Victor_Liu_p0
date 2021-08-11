package com.revature.services;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.repo.UserAccountDAO;

public interface BankServices
{
    void makeDeposit(BankAccount account, double amount) throws NegativeAmountException;

    void makeWithdrawal(BankAccount account, double amount) throws NegativeAmountException, OverdraftException;

    void transfer(BankAccount accountToWithdraw, BankAccount accountToDeposit, double amount) throws NegativeAmountException, OverdraftException;

    double viewBalance(BankAccount account);

    void addOwner(BankAccount account, UserAccount newOwner);

    void viewTransactionHistory(BankAccount account);

    void addBankAccount(BankAccount account, UserAccount owner);

    RevArrayList<BankAccount> getAllBankAccounts(UserAccount owner);

    void deleteBankAccount(BankAccount account);
}
