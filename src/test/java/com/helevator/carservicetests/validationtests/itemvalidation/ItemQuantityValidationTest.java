package com.helevator.carservicetests.validationtests.itemvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.ItemValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemQuantityValidationTest {

    @Test
    public void validateItemQuantityTest(){
        int expectedQuantity = 20;
        String inputQuantity = "20";

        Assertions.assertEquals(expectedQuantity, ItemValidator.validateItemQuantity(inputQuantity));
    }

    @Test
    public void itemQuantityUnder0ExceptionTest(){
        String quantityUnder0 = "-5";

        Assertions.assertThrows(IncorrectInputException.class, () -> ItemValidator.validateItemQuantity(quantityUnder0));
    }

    @Test
    public void itemQuantityParseExceptionTest(){
        String quantity = "7i";

        Assertions.assertThrows(IncorrectInputException.class, () -> ItemValidator.validateItemQuantity(quantity));
    }
}