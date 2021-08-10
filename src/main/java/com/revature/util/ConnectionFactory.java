package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
    // TODO: change to system environment variables
    private static final String URL = "jdbc:postgresql://project0.c4c3no36zu7c.us-east-2.rds.amazonaws.com/postgres";
    private static final String USERNAME = "cptvictor";
    private static final String PASSWORD = "welpSenior113";

    private static Connection connection;

    public static Connection getConnection()
    {
        try
        {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return connection;
    }
}
