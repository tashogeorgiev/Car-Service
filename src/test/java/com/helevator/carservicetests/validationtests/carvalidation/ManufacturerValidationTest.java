package com.helevator.carservicetests.validationtests.carvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.CarValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManufacturerValidationTest {

    @Test
    public void validateManufacturerTest(){
        String manufacturer = "Mercedes";

        Assertions.assertEquals(manufacturer, CarValidator.validateManufacturer(manufacturer));
    }


    @Test
    public void validateManufacturerExceptionTest(){
        String manufacturerOver50Chars = "INSERTSOMEREALLYLONGBRANDLIKEWHOWOULDNAMETHEIRCOMPANYTHISWAY";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateManufacturer(manufacturerOver50Chars));
    }
}
