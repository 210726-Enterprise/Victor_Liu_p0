package com.revature.bankexceptions;

public class OverdraftException extends Throwable
{
    public OverdraftException()
    {
        super("Amount entered is greater than the balance");
    }
}
