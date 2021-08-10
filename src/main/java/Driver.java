import com.revature.bankexceptions.NegativeAmountException;
import com.revature.collection.RevArrayList;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.repo.BankAccountDAO;
import com.revature.repo.BankAccountDAOImplementation;
import com.revature.repo.UserAccountDAO;
import com.revature.repo.UserAccountDAOImplementation;
import com.revature.util.ConnectionFactory;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {

        UserAccountDAO userDAO = new UserAccountDAOImplementation();
        BankAccountDAO bankAccountDAO = new BankAccountDAOImplementation();

        userDAO.insertUserAccount(new UserAccount("Test3", "Test3"));
        UserAccount testUser = userDAO.getUserAccount("Test3");
        bankAccountDAO.insertBankAccount(new BankAccount("Savings", 5000), testUser.getId());
        bankAccountDAO.insertBankAccount(new BankAccount("Checking", 9000), testUser.getId());
        RevArrayList<BankAccount> testBankList = bankAccountDAO.getAllBankAccounts(testUser.getId());

        for(int i = 0; i < testBankList.size(); i++)
        {
            System.out.println(testBankList.get(i).getBalance());
        }
        System.out.println();

        try {
            testBankList.get(0).deposit(500);
        } catch (NegativeAmountException e) {
            e.printStackTrace();
        }
        bankAccountDAO.updateBankAccount(testBankList.get(0));
        testUser.setUsername("Test4");
        userDAO.updateUserAccount(testUser);

        UserAccount testUser2 = userDAO.getUserAccount("Test4");
        RevArrayList<BankAccount> testBankList2 = bankAccountDAO.getAllBankAccounts(testUser2.getId());
        for(int i = 0; i < testBankList2.size(); i++)
        {
            System.out.println(testBankList.get(i).getBalance());
        }
        System.out.println();

        bankAccountDAO.deleteBankAccount(testBankList2.get(0));

//        UserAccount testUser2 = userDAO.getUserAccount("Test4");
        RevArrayList<BankAccount> testBankList3 = bankAccountDAO.getAllBankAccounts(testUser2.getId());
        for(int i = 0; i < testBankList3.size(); i++)
        {
            System.out.println(testBankList.get(i).getBalance());
        }
        userDAO.deleteUserAccount(testUser);
        System.out.println();
    }

    public static void initialMenu()
    {
        System.out.println("Would you like to log in or create a new user account? (Select the number of your choice)");
        System.out.println("1) Log in");
        System.out.println("2) Create a new account");
    }
}

//        Scanner scanner = new Scanner(System.in);
//        String input = "";
//
//        RevArrayList<UserAccount> userAccountList = new RevArrayList<>();
//
//        initialMenu();
//        input = scanner.nextLine();
//        switch(input)
//        {
//            case "1":
//                System.out.print("Enter new username: ");
//                String username = scanner.nextLine();
//                System.out.print("Enter new password: ");
//                String password = scanner.nextLine();
//                userAccountList.add(new UserAccount(username, password, null, userAccountList.size()+1));
//                break;
//            case "2":
//                System.out.print("Enter new username: ");
//                String username = scanner.nextLine();
//                System.out.print("Enter new password: ");
//                String password = scanner.nextLine();
//                break;
//            default:
//                System.out.println("Input invalid, please try again");
//        }