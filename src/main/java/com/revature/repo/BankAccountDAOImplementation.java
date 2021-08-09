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
        String sqlStatement = "insert into \"Bank Accounts\" (\"Account Type\", \"Balance\") values (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
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
        String sqlStatement1 = "delete from \"Bank Accounts\" where \"BankID\" = (?)";
        String sqlStatement2 = "delete from \"Bank User Junction\" where \"BankID\" = (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement2);
            preparedStatement.setInt(1, account.getId());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(sqlStatement1);
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
        String sqlStatement = "select * from \"Bank Accounts\" ba inner join \"Bank User Junction\" buj on ba.\"BankID\" = buj.\"BankID\" and buj.\"UserID\" = (?)";

        PreparedStatement preparedStatement;

        RevArrayList<BankAccount> bankAccountList = new RevArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection())
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
        String sql = "UPDATE \"Bank Accounts\" SET Balance = ? where BankID = ?";

        try(Connection connection = ConnectionFactory.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getId());

            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
