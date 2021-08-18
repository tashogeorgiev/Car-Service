package com.helevator.carservicetracker.validators;

import com.helevator.carservicetracker.data.repair.Metric;
import com.helevator.carservicetracker.exceptions.IncorrectInputException;

/**
 * A class to validate all the attributes for an Item.
 * If the input passes the called validation method,
 * the value is returned in it's correct type,
 * if not, an IncorrectInputException is thrown
 * with a message for the specific input
 */
public class ItemValidator {

    /**
     * Validates if an Item description is under 255 characters long
     */
    public static String validateItemDescription(String description){
        if(description.length() > 255){
            throw new IncorrectInputException("Description is too long.");
        }else return description;
    }

    /**
     * Validates if an Item quantity is at least 0
     */
    public static int validateItemQuantity(String inputQuantity){
        try{
            int quantity = Integer.parseInt(inputQuantity);

            if(quantity <= 0){
                throw new NumberFormatException();
            }

            return quantity;
        }catch (NumberFormatException exception){
            throw new IncorrectInputException(inputQuantity + " is not a valid quantity.");
        }
    }

    /**
     * Validates if an Item Metric exists
     */
    public static Metric validateItemMetric(String metricInput){
        try{
            return Metric.valueOf(metricInput.toUpperCase());
        }catch (IllegalArgumentException exception){
            throw new IncorrectInputException(metricInput + " is not a valid metric.");
        }
    }

    /**
     * Validates if an Item price is at least 0
     */
    public static double validatePrice(String inputPrice){
        try{
            double price = Double.parseDouble(inputPrice);

            if(price <= 0){
                throw new NumberFormatException();
            }

            return price;
        }catch (NumberFormatException exception){
            throw new IncorrectInputException(inputPrice + " is not a valid price.");
        }
    }
}