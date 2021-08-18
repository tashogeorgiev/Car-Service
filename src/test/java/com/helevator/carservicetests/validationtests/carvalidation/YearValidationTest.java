package com.helevator.carservicetests.validationtests.carvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.CarValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YearValidationTest {

    @Test
    public void validateYearTest(){
        int year = 2020;

        Assertions.assertEquals(year, CarValidator.validateYear("2020"));
    }

    @Test
    public void yearAfterCurrentYearExceptionTest(){
        String yearAfterCurrentYear = "2999";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateYear(yearAfterCurrentYear));

    }

    @Test
    public void yearBeforeFirstCarExceptionTest(){
        String yearBeforeFirstCar = "999";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateYear(yearBeforeFirstCar));
    }

    @Test
    public void yearNotANumberExceptionTest(){
        String notANumber = "abc";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateYear(notANumber));
    }

}
