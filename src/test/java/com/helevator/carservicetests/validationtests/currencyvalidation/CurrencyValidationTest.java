package com.helevator.carservicetests.validationtests.currencyvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.CurrencyValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrencyValidationTest {

    @Test
    public void validateCurrencyTest(){
        String isCurrency = "eur";

        Assertions.assertTrue(CurrencyValidator.validateCurrency(isCurrency));
    }

    @Test
    public void validateCurrencyWithANumberTest(){
        String notCurrency = "ab1";

        Assertions.assertThrows(IncorrectInputException.class, () -> CurrencyValidator.validateCurrency(notCurrency));
    }

    @Test
    public void validateCurrencyWithBiggerLengthTest(){
        String notCurrency = "abcdef";

        Assertions.assertThrows(IncorrectInputException.class, () -> CurrencyValidator.validateCurrency(notCurrency));
    }
}