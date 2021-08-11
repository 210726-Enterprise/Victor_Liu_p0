package com.revature.bankexceptions;

/**
 * an exception for overdrafting
 */
public class OverdraftException extends Throwable
{
    public OverdraftException()
    {
        super("Amount entered is greater than the balance");
    }
}
