package com.revature.repo;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDAOImplementation implements BankAccountDAO
{
    @Override
    public void insertBankAccount(BankAccount account, UserAccount owner)
    {
        String sqlStatement1 = "insert into \"Bank Accounts\" " +
                "(\"Account Number\", \"Routing Number\", \"Account Name\", \"Account Type\", \"Balance\") " +
                "values (?, ?, ?, ?, ?) \n";
        String sqlStatement2 = "insert into \"Bank User Junction\" (\"Account Number\", \"Username\") values (?,?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement1);
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getRoutingNumber());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getAccountType());
            preparedStatement.setDouble(5, account.getBalance());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(sqlStatement2);
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, owner.getUsername());
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
        String sqlStatement1 = "delete from \"Bank Accounts\" where \"Account Number\" = (?)";
        String sqlStatement2 = "delete from \"Bank User Junction\" where \"Account Number\" = (?)";

        PreparedStatement preparedStatement;

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement2);
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(sqlStatement1);
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.execute();
        }

        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public RevArrayList<BankAccount> getAllBankAccounts(UserAccount userAccount)
    {
        String sqlStatement = "select * from \"Bank Accounts\" ba " +
                "inner join \"Bank User Junction\" buj " +
                "on ba.\"Account Number\" = buj.\"Account Number\" and buj.\"Username\" = ?";

        PreparedStatement preparedStatement;

        RevArrayList<BankAccount> bankAccountList = new RevArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection())
        {
            preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, userAccount.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                try
                {
                    BankAccount newAccount = new BankAccount(
                            resultSet.getString("Account Number"),
                            resultSet.getString("Routing Number"),
                            resultSet.getDouble("Balance"),
                            resultSet.getString("Account Type"),
                            resultSet.getString("Account Name"));
                    bankAccountList.add(newAccount);
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (NegativeAmountException exception)
                {
                    exception.printStackTrace();
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
        String sql = "update \"Bank Accounts\" set \"Balance\" = ? where \"Account Number\" = ?";

        try(Connection connection = ConnectionFactory.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getAccountNumber());

            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void addOwner(BankAccount account, UserAccount newOwner)
    {
        String sqlStatement = "insert into \"Bank User Junction\" (\"Account Number\" , \"Username\") values (?,?)";

        try(Connection connection = ConnectionFactory.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, newOwner.getUsername());

            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
