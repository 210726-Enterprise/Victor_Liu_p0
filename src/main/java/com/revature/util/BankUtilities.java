package com.revature.util;

public class BankUtilities
{
    public static String generateAccountNumber()
    {
        return "" + (int) (Math.random()*1000000000);
    }

    public static String generateRoutingNumber()
    {
        return "" + (int) (Math.random()*10000000);
    }

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
