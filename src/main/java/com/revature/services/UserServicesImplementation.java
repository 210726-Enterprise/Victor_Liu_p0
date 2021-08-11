package com.revature.services;

import com.revature.model.UserAccount;
import com.revature.repo.UserAccountDAO;
import com.revature.repo.UserAccountDAOImplementation;

public class UserServicesImplementation implements UserServices
{
    private static UserAccountDAO userAccountDAO = new UserAccountDAOImplementation();

    @Override
    public boolean findUser(String username)
    {
        return userAccountDAO.findUser(username);
    }

    @Override
    public boolean verifyPassword(String username, String password)
    {
        return userAccountDAO.verifyLogin(username, password);
    }

    @Override
    public UserAccount getUser(String username)
    {
        return userAccountDAO.getUserAccount(username);
    }

    @Override
    public void addUser(UserAccount account)
    {
        userAccountDAO.insertUserAccount(account);
    }
}
