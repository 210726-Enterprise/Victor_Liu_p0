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
        UserAccountDAO userDAO = new UserAccountDAOImplementation();
        UserAccount testUser = userDAO.getUserAccount("Test");

        BankAccountDAO bankAccountDAO = new BankAccountDAOImplementation();
        RevArrayList<BankAccount> testBankList = bankAccountDAO.getAllBankAccounts(testUser.getId());

        System.out.println(testBankList.get(0).getBalance());


        Connection connection = ConnectionFactory.getConnection();
    }

    public static void initialMenu()
    {
        System.out.println("Would you like to log in or create a new user account? (Select the number of your choice)");
        System.out.println("1) Log in");
        System.out.println("2) Create a new account");
    }
}
