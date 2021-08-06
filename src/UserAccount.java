import BankExceptions.NegativeAmountException;
import BankExceptions.OverdraftException;

public class UserAccount
{
    private String username;
    private String password;
    private BankAccount[] accounts;

    public UserAccount(String username, String password, BankAccount[] accounts)
    {
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }

    public boolean verifyCredentials(String username, String password)
    {
        if(username.equals(this.username) && password.equals(this.password))
            return true;
        return false;
    }

    public BankAccount getAccount(int accountNumber)
    {
        return accounts[accountNumber];
    }

    public double getBalanceOfAccount(int accountNumber)
    {
        return getAccount(accountNumber).getBalance();
    }

    public void depositIntoAccount(double amount, int accountNumber)
    {
        try
        {
            accounts[accountNumber].deposit(amount);
        }
        catch (NegativeAmountException e)
        {
            e.printStackTrace();
        }
    }

    public void withdrawFromAccount(double amount, int accountNumber)
    {
        try
        {
            accounts[accountNumber].withdraw(amount);
        }
        catch (NegativeAmountException e)
        {
            e.printStackTrace();
        }
        catch (OverdraftException e)
        {
            e.printStackTrace();
        }
    }
}
