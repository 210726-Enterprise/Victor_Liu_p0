package com.revature.bankexceptions;

public class NegativeAmountException extends Throwable
{
    public NegativeAmountException()
    {
        super("Amount entered is negative");
    }
}
