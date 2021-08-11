package com.revature.services;

import com.revature.model.UserAccount;

public interface UserServices
{
    boolean findUser(String username);

    boolean verifyPassword(String username, String password);

    UserAccount getUser(String username);
}
