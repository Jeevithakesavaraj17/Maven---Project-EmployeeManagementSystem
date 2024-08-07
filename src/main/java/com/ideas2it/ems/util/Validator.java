package com.ideas2it.ems.util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * This class contains methods for validating the user inputs like date, name, phone number 
 * of the user.
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public class Validator {

    /**
     * <p>
     * This method is used to validate name from the user
     * </p>
     *
     * @param inputName     name which we have to validate 
     * @return boolean      If input name contains empty space or number or empty string returns false,
     *                     else return true.
     */
    public static boolean isValidName(String inputName) {
        String emptyString = "";
        if (inputName.equals(emptyString)) {
            return false;
        } else {
            for (int i = 0; i < inputName.length(); i++) {
                char ch = inputName.charAt(i);
                if (!(ch >= 'a' && ch <= 'z'|| ch >= 'A' && ch <= 'Z')) {
                    return false;
                }
            }
            return true;
        }
    } 
  
    /**
     * <p>
     * This method is used to validate date.
     * </p>
     *
     * @param  inputDate   date which we have to validate
     * @return boolean     If input date is in YYYY/MM/DD format, return true
     *                    else return false.
     */
    public static boolean isValidDate(String inputDate) { 
        try {
             LocalDate.parse(inputDate);
             return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>
     * This method is used to validate phone from the user
     * </p>
     *
     * @param phoneNumber   phone number which we have to validate
     * @return  boolean     If given phone number has ten-digit number, returns true
     *                     else return false. 
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    } 

    /**
     * <p>
     * This method is used to validate mail Id.
     * </p>
     *
     * @param  mailId    mail id which we have to validate 
     * @return boolean    If mail id has alphabets and number with @, returns true
     *                   else returns false.
     */
    public static boolean isValidMailId(String mailId) {
        String emailId = mailId.toLowerCase();
        String regex = "^[a-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }  
    
    /**
     * <p>
     * This method is used to validate experience
     * </p>
     *
     * @param experience   experience which we have to validate
     * @return boolean    If experience is between 0 and 30, returns true
     *                   else false.
     */
    public static boolean isValidExperience(int experience) {
        return experience >= 0 && experience < 30;
    }

}