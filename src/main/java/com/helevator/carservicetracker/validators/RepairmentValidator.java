package com.helevator.carservicetracker.validators;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * A class to validate all the attributes for a Repairment.
 * If the input passes the called validation method,
 * the value is returned in it's correct type,
 * if not, an IncorrectInputException is thrown
 * with a message for the specific input
 */
public class RepairmentValidator {
    /**
     * Validates if a given Id is in the correct format - 10 digits long
     */
    public static Long validateRepairId(String inputId){
        try {
            if(inputId.length() != 10){
                throw new NumberFormatException();
            }
            return Long.parseLong(inputId);
        }catch (NumberFormatException exception){
            throw new IncorrectInputException("Id format not valid.");
        }
    }

    /**
     * Validates if a given date is in the correct format - dd/MM/yyyy
     */
    public static LocalDate validateDate(String inputDate){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);

            return dateFormat.parse(inputDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }catch (ParseException parseException){
            throw new IncorrectInputException(inputDate + " is not a correct date format, correct format is dd/MM/yyyy.");
        }
    }

    /**
     * Validates if a given mileage is legit - has to be over 0
     */
    public static int validateMileage(String mileageInput){
        try{
            int mileage = Integer.parseInt(mileageInput);

            if(mileage < 0){
                throw new NumberFormatException();
            }
            return mileage;
        }catch (NumberFormatException exception){
            throw new IncorrectInputException(mileageInput + " is not a valid mileage.");
        }
    }
}