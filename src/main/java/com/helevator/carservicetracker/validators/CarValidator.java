package com.helevator.carservicetracker.validators;

import com.helevator.carservicetracker.data.vehicle.EngineType;
import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;

/**
 * A class to validate all the attributes for a Car.
 * If the input passes the called validation method,
 * the value is returned in it's correct type,
 * if not, an IncorrectInputException is thrown
 * with a message for the specific input
 */

public class CarValidator {

    /**
     * A VIN should not contain the letters 'I', 'O' or 'Q'
     * and should be exactly 17 characters long
     */
    public static String validateVin(String vin){
        if(StringUtils.containsAny(vin, "IOQ") || vin.length() != 17) {
            throw new IncorrectInputException("This vin is not valid: " + vin);
        }else return vin;
    }

    /**
     * The longest allowed manufacturer name is with length 50
     */
    public static String validateManufacturer(String manufacturer){
        if(manufacturer.length() > 50){
            throw new IncorrectInputException("Manufacturer name is too long.");
        }else return manufacturer;
    }

    /**
     * The longest allowed model name is with length 50
     */
    public static String validateModel(String model){
        if(model.length() > 50){
            throw new IncorrectInputException("Model name is too long.");
        }else return model;
    }

    /**
     * A car cannot have a model year after the current year,
     * or a year before the creation of the first car.
     */
    public static int validateYear(String inputYear){
        try{
            int year = Integer.parseInt(inputYear);

            if(year < 1886 || year > Year.now().getValue()){
                throw new NumberFormatException();
            }

            return year;
        }catch (NumberFormatException exception){
            throw new IncorrectInputException(inputYear + " is not a valid year.");
        }
    }

    /**
     * The given engine type should be either Diesel or Petrol
     */
    public static EngineType validateEngineType(String engineTypeInput){
        try{
            return EngineType.valueOf(engineTypeInput.toUpperCase());
        }catch (IllegalArgumentException exception){
            throw new IncorrectInputException(engineTypeInput + " is not an engine type.");
        }
    }
}