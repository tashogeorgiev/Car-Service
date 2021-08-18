package com.helevator.carservicetests.validationtests.carvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.CarValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelValidationTest {

    @Test
    public void validateModelTest(){
        String model = "C-Class";

        Assertions.assertEquals(model, CarValidator.validateManufacturer(model));
    }


    @Test
    public void validateModelExceptionTest(){
        String modelOver50Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateManufacturer(modelOver50Chars));
    }
}
