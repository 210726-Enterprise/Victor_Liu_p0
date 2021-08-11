package com.revature.services;

import com.revature.bankexceptions.NegativeAmountException;
import com.revature.bankexceptions.OverdraftException;
import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.util.BankUtilities;
import java.util.Scanner;

public class BankMenus
{
    private final UserServices userServices = new UserServicesImplementation();
    private final BankServices bankServices = new BankServicesImplementation();
    private final Scanner scanner = new Scanner(System.in);
    private String input = "";

    public void consoleMenuHandler()
    {
        do
        {
            initialMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    UserAccount foundUser = userServices.getUser(loginMenu());
                    bankActionsMenuHandler(foundUser);
                    break;
                case "2":
                    UserAccount currentUser = createNewUserAccountMenu();
                    userServices.addUser(currentUser);
                    bankActionsMenuHandler(currentUser);
                    break;
                case "Q":
                    System.out.println();
                    return;
                default:
                    System.out.println("Input invalid. Please try again.\n");
            }
        } while(true);
    }

    private void initialMenu()
    {
        System.out.println("Would you like to log in or create a new user account? (Select the number/letter of your choice)");
        System.out.println("1) Log in");
        System.out.println("2) Create a new account");
        System.out.println("Q) Quit application");
    }

    private UserAccount createNewUserAccountMenu()
    {
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        return new UserAccount(username, password);
    }

    private void bankActionsMenuHandler(UserAccount currentUser)
    {
        do
        {
            bankActionsMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    bankServices.addBankAccount(openNewBankAccountMenu(), currentUser);
                    break;
                case "2":
                    viewBalanceMenu(currentUser);
                    break;
                case "3":
                    depositIntoBankAccount(currentUser);
                    break;
                case "4":
                    withdrawFromBankAccount(currentUser);
                    break;
                case "Q":
                    System.out.println();
                    return;
                default:
                    System.out.println("Input invalid. Please try again.\n");
            }
        } while(true);
    }

    private void bankActionsMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1) Open a new bank account");
        System.out.println("2) View the balance of a bank account");
        System.out.println("3) Deposit into a bank account");
        System.out.println("4) Withdraw from a bank account");
        System.out.println("Q) Log out");
    }

    private void viewBalanceMenu(UserAccount currentUser)
    {
        RevArrayList<BankAccount> accounts = bankServices.getAllBankAccounts(currentUser);
        if(accounts.size() <= 0)
        {
            System.out.println("You have no accounts.\n");
            return;
        }
        boolean accountViewed = false;
        do
        {
            System.out.println("Which account would you like to view?");
            for (int i = 0; i < accounts.size(); i++) {
                BankAccount currentAccount = accounts.get(i);
                System.out.println("" + i + ") " +
                        currentAccount.getAccountNumber() + " " +
                        currentAccount.getName() + " - " +
                        currentAccount.getAccountType());
            }
            input = scanner.nextLine();
            try
            {
                int integerInput = Integer.parseInt(input);
                if (integerInput >= accounts.size() || integerInput < 0) {
                    System.out.println("Invalid input. Please input a number corresponding to an account.\n");
                }
                else
                {
                    BankAccount accountToView = accounts.get(integerInput);
                    System.out.println("Balance of account number " +
                            accountToView.getAccountNumber() + ": " +
                            BankUtilities.formatIntoCurrency(accountToView.getBalance()));
                    accountViewed = true;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a number.\n");
            }
        } while (!accountViewed);
    }

    private void depositIntoBankAccount(UserAccount currentUser)
    {
        RevArrayList<BankAccount> accounts = bankServices.getAllBankAccounts(currentUser);
        if(accounts.size() <= 0)
        {
            System.out.println("You have no accounts.\n");
            return;
        }
        boolean deposited = false;
        do
        {
            System.out.println("Which account would you deposit into?");
            for (int i = 0; i < accounts.size(); i++) {
                BankAccount currentAccount = accounts.get(i);
                System.out.println("" + i + ") " +
                        currentAccount.getAccountNumber() + " " +
                        currentAccount.getName() + " - " +
                        currentAccount.getAccountType());
            }
            input = scanner.nextLine();
            try
            {
                int integerInput = Integer.parseInt(input);
                if (integerInput >= accounts.size() || integerInput < 0) {
                    System.out.println("Invalid input. Please input a number corresponding to an account.\n");
                }
                else
                {
                    BankAccount accountToDeposit = accounts.get(integerInput);
                    do
                    {
                        System.out.print("Enter the amount you would like to deposit: ");
                        input = scanner.nextLine();
                        try
                        {
                            bankServices.makeDeposit(accountToDeposit, Double.parseDouble(input));
                            deposited = true;
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Invalid input. Please enter a number for the amount.\n");
                        }
                        catch (NegativeAmountException e)
                        {
                            System.out.println("Invalid amount. Please enter a positive number to deposit.");
                        }
                    } while(!deposited);
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid input. Please input a number corresponding to an account.\n");
            }
        } while (!deposited);
    }

    private void withdrawFromBankAccount(UserAccount currentUser)
    {
        RevArrayList<BankAccount> accounts = bankServices.getAllBankAccounts(currentUser);
        if(accounts.size() <= 0)
        {
            System.out.println("You have no accounts.\n");
            return;
        }
        boolean withdrawed = false;
        do
        {
            System.out.println("Which account would you withdraw from?");
            for (int i = 0; i < accounts.size(); i++) {
                BankAccount currentAccount = accounts.get(i);
                System.out.println("" + i + ") " +
                        currentAccount.getAccountNumber() + " " +
                        currentAccount.getName() + " - " +
                        currentAccount.getAccountType());
            }
            input = scanner.nextLine();
            try
            {
                int integerInput = Integer.parseInt(input);
                if (integerInput >= accounts.size() || integerInput < 0) {
                    System.out.println("Invalid input. Please input a number corresponding to an account.\n");
                }
                else
                {
                    BankAccount accountToWithdraw = accounts.get(integerInput);
                    do
                    {
                        System.out.print("Enter the amount you would like to deposit: ");
                        input = scanner.nextLine();
                        try
                        {
                            bankServices.makeWithdrawal(accountToWithdraw, Double.parseDouble(input));
                            withdrawed = true;
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Invalid input. Please enter a number for the amount.\n");
                        }
                        catch (NegativeAmountException e)
                        {
                            System.out.println("Invalid amount. Please enter a positive number to deposit.");
                        }
                        catch (OverdraftException e)
                        {
                            System.out.println("Invalid amount. Amount to withdraw exceeds amount currently in account. Please try a smaller amount.");
                        }
                    } while(!withdrawed);
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid input. Please input a number corresponding to an account.\n");
            }
        } while (!withdrawed);
    }

    private BankAccount openNewBankAccountMenu()
    {
        System.out.print("Enter they type of account you want to open: ");
        String accountType = scanner.nextLine();


        System.out.print("Enter a name for this account: ");
        String accountName = scanner.nextLine();

        boolean accountCreated = false;
        BankAccount bankAccount = null;
        do
        {
            System.out.print("Enter an initial balance (numbers only): ");
            String balance = scanner.nextLine();
            try
            {
                bankAccount = new BankAccount(BankUtilities.generateAccountNumber(),
                        BankUtilities.generateRoutingNumber(),
                        Double.parseDouble(balance),
                        accountType,
                        accountName);
                accountCreated = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a number for the balance.\n");
            }
            catch (NegativeAmountException e)
            {
                System.out.println("Invalid amount. Please enter a balance greater than or equal to 0.\n");
            }
        } while(!accountCreated);

        return bankAccount;
    }

    private String enterUsername()
    {
        System.out.print("Enter your username: ");
        return scanner.nextLine();
    }

    private String enterPassword()
    {
        System.out.print("Enter your password: ");
        return scanner.nextLine();
    }

    private String loginMenu()
    {
        UserAccount user = null;

        String username = "";
        boolean userFound = false;
        do
        {
            username = enterUsername();
            if(!userServices.findUser(username))
            {
                System.out.print("User not found. Please try again.\n");
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
            if(!userServices.verifyPassword(username, password))
            {
                System.out.print("Incorrect Password. Please try again.\n");
            }
            else
            {
                verified = true;
            }
        } while (!verified);

        return username;
    }
}
