package com.revature.model;

import com.revature.collection.RevArrayList;

/**
 * models a user account
 */
public class UserAccount
{
    private String username;
    private String password;

    public UserAccount(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    /**
     * changes the username (not used)
     * @param username the new username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * gets the username of the account
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * gets the password of the account
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }
}
