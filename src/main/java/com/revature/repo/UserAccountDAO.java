package com.revature.repo;

import com.revature.model.UserAccount;

public interface UserAccountDAO
{
    void insertUserAccount(UserAccount account);

    void deleteUserAccount(UserAccount account);

    UserAccount getUserAccount(String username);

    void updateUserAccount(UserAccount account);
}
