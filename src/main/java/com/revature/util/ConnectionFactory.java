package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * a utility class for handling connections to the database
 */
public class ConnectionFactory
{
    // TODO: change to system environment variables
    private static final String URL = "jdbc:postgresql://project0.c4c3no36zu7c.us-east-2.rds.amazonaws.com/postgres";
    private static final String USERNAME = "cptvictor";
    private static final String PASSWORD = "welpSenior113";

    private static Connection connection;

    /**
     * gets a connection to the database
     * @return the connection to the database
     */
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
