package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;

public interface BankAccountDAO
{
    void insertBankAccount(BankAccount account);

    void deleteBankAccount(BankAccount account);

    BankAccount getBankAccount(BankAccount account);

    RevArrayList<BankAccount> getAllBankAccounts(int ownerId);

    void updateBankAccount(BankAccount account);
}
