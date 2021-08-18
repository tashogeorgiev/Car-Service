package com.helevator.carservicetracker.validators;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;

/**
 * A class to validate a Currency.
 * If the input passes the called validation method,
 * the value is returned in it's correct type,
 * if not, an IncorrectInputException is thrown
 * with a message for the specific input
 */
public class CurrencyValidator {

    /**
     * Validate a currency, it has to have a length of 3, with alphabet characters only
     */
    public static boolean validateCurrency(String currency){
        if(currency.length() == 3 && currency.matches("[a-zA-Z]+")){
            return true;
        }else throw new IncorrectInputException("Currency must be only 3 letters with no numbers");
    }
}