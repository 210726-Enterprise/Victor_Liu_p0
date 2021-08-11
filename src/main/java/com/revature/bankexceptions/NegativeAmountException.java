package com.revature.bankexceptions;

/**
 * an exception for negative inputs
 */
public class NegativeAmountException extends Throwable
{
    public NegativeAmountException()
    {
        super("Amount entered is negative");
    }
}
