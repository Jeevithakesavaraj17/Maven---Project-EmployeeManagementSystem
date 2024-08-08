package com.ideas2it.ems.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * <p>
 *     This class have method for calculating the difference between the dates.
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public class DateUtil {

    /**
     * <p>
     *    This method is used to calculate the difference between
     *    date(which we are giving) and current date
     * </p>
     * @param date   date which user has given
     * @return String of difference between dates in years and months
     */
    public static String calculateDifferenceBetweenDates(LocalDate date){
        LocalDate currentDate = LocalDate.now();
        return Period.between(date, currentDate).getYears() + "y"
                + Period.between(date, currentDate).getMonths() + "m";
    }
}
