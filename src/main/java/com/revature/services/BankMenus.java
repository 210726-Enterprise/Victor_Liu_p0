package com.revature.services;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.repo.UserAccountDAO;
import com.revature.repo.UserAccountDAOImplementation;

import java.util.Scanner;

public class BankMenus
{
    public static void initialMenu()
    {
        System.out.println("Would you like to log in or create a new user account? (Select the number/letter of your choice)");
        System.out.println("1) Log in");
        System.out.println("2) Create a new account");
        System.out.println("Q) Quit application");
    }

    public static UserAccount createNewUserAccountMenu()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        return new UserAccount(username, password);
    }

    public static void bankActionsMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1) Create a new bank account");
        System.out.println("2) View the balance of a bank account");
        System.out.println("3) Deposit into a bank account");
        System.out.println("4) Withdraw from a bank account");
    }

    public static BankAccount openNewBankAccountMenu()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter they type of account you want to open: ");
        String accountType = scanner.nextLine();

        System.out.print("Enter an initial balance: ");
        String balance = scanner.nextLine();

        System.out.print("Enter a name for this account: ");
        String accountName = scanner.nextLine();

        try
        {
            return new BankAccount(accountType, Double.parseDouble(balance), accountName);
        }
        catch (NegativeAmountException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String enterUsername()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        return scanner.nextLine();
    }

    private String enterPassword()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your password: ");
        return scanner.nextLine();
    }

    public UserAccount loginMenu()
    {
        UserAccount user = null;

        UserAccountDAO userAccountDAO = new UserAccountDAOImplementation();
        UserServices userServices = new UserServicesImplementation();

        String username = "";
        boolean userFound = false;
        do
        {
            username = enterUsername();
            if(userServices.findUser(username))
            {
                System.out.println("User not found. Please try again.\n");
            }
            else
            {
                userFound = true;
            }
        } while(!userFound);

        String password = "";
        boolean verified = false;
        do
        {
            password = enterPassword();
            if(userServices.verifyPassword(username, password))
            {
                System.out.println("User not found. Please try again.\n");
            }
            else
            {
                verified = true;
            }
        } while (!verified);

        return userServices.getUser(username);
    }
}
