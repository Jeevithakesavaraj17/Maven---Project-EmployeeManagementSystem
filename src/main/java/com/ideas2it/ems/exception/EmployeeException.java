package com.ideas2it.ems.exception;

/**
 * <p>
 * This class is for custom exception for employee management system and extends Exception
 * </p>
 *
 * @author  JeevithaKesavaraj
 */
public class EmployeeException extends Exception{

    public EmployeeException(String message, Throwable e) {
        super(message, e);
    }

}