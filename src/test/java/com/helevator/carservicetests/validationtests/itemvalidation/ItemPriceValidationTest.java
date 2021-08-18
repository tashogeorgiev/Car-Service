package com.helevator.carservicetests.validationtests.itemvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.ItemValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemPriceValidationTest {

    @Test
    public void validateItemPriceTest(){
        double price = 20.10;
        String inputPrice = "20.10";

        Assertions.assertEquals(price, ItemValidator.validatePrice(inputPrice));
    }

    @Test
    public void itemPriceUnder0ExceptionTest(){
        String priceUnder0 = "-5.2";

        Assertions.assertThrows(IncorrectInputException.class, () -> ItemValidator.validatePrice(priceUnder0));

    }

    @Test
    public void itemPriceParseExceptionTest(){
        String price = "7,1i";

        Assertions.assertThrows(IncorrectInputException.class, () -> ItemValidator.validatePrice(price));
    }
}