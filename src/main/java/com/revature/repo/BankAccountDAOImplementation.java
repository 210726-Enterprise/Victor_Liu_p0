package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDAOImplementation implements BankAccountDAO
{

    @Override
    public void insertBankAccount(BankAccount account)
    {
        Connection connection = ConnectionFactory.getConnection();

        String sqlStatement = "insert into \"Bank Accounts\" (\"BankID\", \"Account Type\", \"Balance\", \"UserID\") values (?)";

        PreparedStatement preparedStatement;

        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(2, account.getAccountType());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setInt(4, account.getOwners().get(0).getId());

            preparedStatement.execute();

            connection.close();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBankAccount(BankAccount account)
    {
        Connection connection = ConnectionFactory.getConnection();

        String sqlStatement = "delete from \"Bank Accounts\" where \"BankID\" = (?)";

        PreparedStatement preparedStatement;

        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, account.getId());

            preparedStatement.execute();

            connection.close();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public BankAccount getBankAccount(BankAccount account)
    {

        return account;
    }

    @Override
    public RevArrayList<BankAccount> getAllBankAccounts(int ownerId)
    {
        Connection connection = ConnectionFactory.getConnection();

        String sqlStatement = "select * from \"Bank Accounts\" ba where \"UserID\" = (?)";

        PreparedStatement preparedStatement;

        RevArrayList<BankAccount> bankAccountList;

        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, ownerId);

            ResultSet results = preparedStatement.executeQuery();
            while(results.next())
            {
                BankAccount newAccount = new BankAccount(
                        results.getInt("BankId"),
                        results.getString("Account Type"),
                        results.getDouble("Balance"),
                        results.getInt("UserId"));
            }

            connection.close();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBankAccount(BankAccount account)
    {

    }
}
