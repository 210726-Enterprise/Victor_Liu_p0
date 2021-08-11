package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;

import javax.jws.soap.SOAPBinding;

public interface BankAccountDAO
{
    void insertBankAccount(BankAccount account, UserAccount owner);

    void deleteBankAccount(BankAccount account);

    RevArrayList<BankAccount> getAllBankAccounts(UserAccount owner);

    void updateBankAccount(BankAccount account);

    void addOwner(BankAccount account, UserAccount newOwner);
}
