package com.revature.services;

import com.revature.model.UserAccount;

/**
 * interface for accessing and modifying user accounts
 */
public interface UserServices
{
    /**
     * find a user in the database by their username
     * @param username the username to search for
     * @return true if the user exists in the database
     */
    boolean findUser(String username);

    /**
     * verify the login credentials
     * @param username the name of the account trying to log in
     * @param password the password attempt
     * @return true if the password is correct
     */
    boolean verifyPassword(String username, String password);

    /**
     * gets a user's information
     * @param username the username of the account to get
     * @return the user account with all of their information
     */
    UserAccount getUser(String username);

    /**
     * add a user to the database
     * @param account the user account to add
     */
    void addUser(UserAccount account);
}
