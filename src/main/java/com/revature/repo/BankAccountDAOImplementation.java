package com.revature.repo;

import com.revature.bankexceptions.NegativeAmountException;
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
    public void insertBankAccount(BankAccount account, int ownerId)
    {
        String sqlStatement1 = "insert into \"Bank Accounts\" (\"Account Type\", \"Balance\") values (?,?)";
        String sqlStatement2 = "insert into \"Bank User Junction\" (\"BankID\", \"UserID\") values (?,?)";
        String sqlStatement3 = "select last_value from \"Bank Accounts_BankID_seq\"";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement1);
            preparedStatement.setString(1, account.getAccountType());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(sqlStatement3);
            ResultSet resultSet = preparedStatement.executeQuery();
            int bankId = 0;
            if(resultSet.next())
            {
                bankId = resultSet.getInt("last_value");
            }

            preparedStatement = connection.prepareStatement(sqlStatement2);
            preparedStatement.setInt(1, bankId);
            preparedStatement.setInt(2, ownerId);
            preparedStatement.execute();
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
        }

        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Deprecated
    public BankAccount getBankAccount(BankAccount account)
    {
        String sqlStatement = "select * from \"Bank Accounts\" ba where \"BankID\" = (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, account.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                try
                {
                    BankAccount newAccount = new BankAccount(
                            resultSet.getInt("BankId"),
                            resultSet.getString("Account Type"),
                            resultSet.getDouble("Balance"));
                    return newAccount;
                }
                catch (NegativeAmountException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RevArrayList<BankAccount> getAllBankAccounts(int ownerId)
    {
        String sqlStatement = "select * from \"Bank Accounts\" ba " +
                "inner join \"Bank User Junction\" buj " +
                "on ba.\"BankID\" = buj.\"BankID\" and buj.\"UserID\" = (?)";

        PreparedStatement preparedStatement;

        RevArrayList<BankAccount> bankAccountList = new RevArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, ownerId);

            ResultSet results = preparedStatement.executeQuery();
            while(results.next())
            {
                try
                {
                    BankAccount newAccount = new BankAccount(
                            results.getInt("BankId"),
                            results.getString("Account Type"),
                            results.getDouble("Balance"));
                    bankAccountList.add(newAccount);
                }
                catch (NegativeAmountException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bankAccountList;
    }

    @Override
    public BankAccount getBankAccount(int bankId)
    {
        String sqlStatement = "select * from \"Bank Accounts\" ba " +
                "inner join \"Bank User Junction\" buj " +
                "on ba.\"BankID\" = buj.\"BankID\" and buj.\"UserID\" = (?)";

        PreparedStatement preparedStatement;

        RevArrayList<BankAccount> bankAccountList = new RevArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, ownerId);

            ResultSet results = preparedStatement.executeQuery();
            while(results.next())
            {
                try
                {
                    BankAccount newAccount = new BankAccount(
                            results.getInt("BankId"),
                            results.getString("Account Type"),
                            results.getDouble("Balance"),
                            results.getString("name"));
                    bankAccountList.add(newAccount);
                }
                catch (NegativeAmountException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
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
        String sql = "update \"Bank Accounts\" set \"Balance\" = ? where \"BankID\" = ?";

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
