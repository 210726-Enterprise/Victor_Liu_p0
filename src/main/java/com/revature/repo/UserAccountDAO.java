package com.revature.repo;

import com.revature.model.UserAccount;

public interface UserAccountDAO
{
    void insertUserAccount(UserAccount account);

    void deleteUserAccount(UserAccount account);

    UserAccount getUserAccount(UserAccount account);

    void updateUserAccount(UserAccount account);
}
