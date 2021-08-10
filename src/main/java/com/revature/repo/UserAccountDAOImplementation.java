package com.revature.repo;

import com.revature.model.UserAccount;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDAOImplementation implements UserAccountDAO
{
    @Override
    public void insertUserAccount(UserAccount account)
    {
        String sqlStatement = "insert into \"User Accounts\" (\"Username\", \"Password\") values (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserAccount(UserAccount account)
    {
        String sqlStatement1 = "delete from \"User Accounts\" where \"UserID\" = (?)";
        String sqlStatement2 = "delete from \"Bank User Junction\" where \"UserID\" = (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement2);
            preparedStatement.setInt(1, account.getId());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(sqlStatement1);
            preparedStatement.setInt(1, account.getId());
            preparedStatement.execute();
        }

        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public UserAccount getUserAccount(UserAccount account)
    {
        String sqlStatement = "select * from \"User Accounts\" ua where \"UserID\" = (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserAccount newUserAccount = new UserAccount(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"));
            return newUserAccount;
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUserAccount(UserAccount account)
    {
        String sqlStatement = "update \"User Accounts\" set \"Username\" = (?), \"Password\" = (?) where \"UserID\" = (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, account.getId());

            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
