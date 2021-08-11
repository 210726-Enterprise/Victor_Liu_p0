package com.revature.util;

/**
 * a utility class to assist with services
 */
public class BankUtilities
{
    /**
     * generate a random account number
     * @return an account number
     */
    public static String generateAccountNumber()
    {
        return "" + (int) (Math.random()*1000000000);
    }

    /**
     * generate a random routing number
     * @return a routing number
     */
    public static String generateRoutingNumber()
    {
        return "" + (int) (Math.random()*10000000);
    }

    /**
     * format a balance into currency terms
     * i.e. $XXX.XX
     * @param number the balance to format
     * @return the formated string version of that balance
     */
    public static String formatIntoCurrency(double number)
    {
        String currency =  "$" + number;
        String decimalSplit = currency.substring(currency.indexOf(".")+1);
        if(decimalSplit.length() < 2)
        {
            currency += "0";
        }
        return currency;
    }
}
