package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;

public interface BankAccountDAO
{
    void insertBankAccount(BankAccount account, int ownerId);

    void deleteBankAccount(BankAccount account);

    RevArrayList<BankAccount> getAllBankAccounts(int ownerId);

    BankAccount getBankAccount(int bankId);

    void updateBankAccount(BankAccount account);
}
