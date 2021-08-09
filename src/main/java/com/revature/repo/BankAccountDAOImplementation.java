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

        String sqlStatement = "insert into \"Bank Accounts\" (\"Account Type\", \"Balance\") values (?)";

        PreparedStatement preparedStatement;

        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, account.getAccountType());
            preparedStatement.setDouble(2, account.getBalance());

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

        String sqlStatement = "select * from \"Bank Accounts\" ba inner join \"Bank User Junction\" buj on ba.\"BankID\" = buj.\"BankID\" and buj.\"UserID\" = (?)";

        PreparedStatement preparedStatement;

        RevArrayList<BankAccount> bankAccountList = new RevArrayList<>();

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
                        results.getDouble("Balance"));
                bankAccountList.add(newAccount);
            }

            connection.close();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bankAccountList;
    }

    @Override
    public void updateBankAccount(BankAccount account)
    {

    }
}
