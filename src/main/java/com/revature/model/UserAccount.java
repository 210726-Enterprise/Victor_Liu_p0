package com.revature.model;

import com.revature.collection.RevArrayList;

public class UserAccount
{
    private String username;
    private String password;

    public UserAccount(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
