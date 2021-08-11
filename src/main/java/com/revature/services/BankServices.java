package com.revature.services;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.repo.UserAccountDAO;

/**
 * interface for accessing and modifying bank accounts
 */
public interface BankServices
{
    /**
     * make a deposit into an account
     * @param account the account to deposit into
     * @param amount the amount to deposit
     * @throws NegativeAmountException
     */
    void makeDeposit(BankAccount account, double amount) throws NegativeAmountException;

    /**
     * make a withdrawal from an account
     * @param account the account to withdraw from
     * @param amount the amount to withdraw
     * @throws NegativeAmountException
     * @throws OverdraftException
     */
    void makeWithdrawal(BankAccount account, double amount) throws NegativeAmountException, OverdraftException;

    /**
     * transfer between accounts (not used)
     * @param accountToWithdraw the account to take money from
     * @param accountToDeposit the account to put money into
     * @param amount the amount to move
     * @throws NegativeAmountException
     * @throws OverdraftException
     */
    void transfer(BankAccount accountToWithdraw, BankAccount accountToDeposit, double amount) throws NegativeAmountException, OverdraftException;

    /**
     * view how much money is in an account
     * @param account the account to view
     * @return the balance of the account
     */
    double viewBalance(BankAccount account);

    /**
     * add an owner to an account
     * @param account the new joint account
     * @param newOwner the new owner of the account
     */
    void addOwner(BankAccount account, UserAccount newOwner);

    /**
     * add a bank account
     * @param account the new account to add
     * @param owner the owner of that account
     */
    void addBankAccount(BankAccount account, UserAccount owner);

    /**
     * get all bank accounts owned by a user
     * @param owner the owner of the accounts
     * @return all the accounts they own
     */
    RevArrayList<BankAccount> getAllBankAccounts(UserAccount owner);

    /**
     * delete a closed bank account
     * @param account the account to remove
     */
    void deleteBankAccount(BankAccount account);
}
