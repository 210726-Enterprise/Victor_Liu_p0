package com.revature.repo;

import com.revature.model.UserAccount;

public interface UserAccountDAO
{
    void insertUserAccount(UserAccount account);

    void deleteUserAccount(UserAccount account);

    UserAccount getUserAccount(String username);

    boolean findUser(String username);

    boolean verifyLogin(String username, String password);

    void updateUserAccount(UserAccount account);
}
