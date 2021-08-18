package com.helevator.carservicetests.validationtests.carvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.CarValidator;
import com.helevator.carservicetracker.data.vehicle.EngineType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EngineTypeValidationTest {

    @Test
    public void validateEngineTypeTest(){
        EngineType engineType = EngineType.DIESEL;

        Assertions.assertEquals(engineType, CarValidator.validateEngineType("diesel"));
    }

    @Test
    public void validateEngineTypeExceptionTest(){
        String noSuchEngine = "battery";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateEngineType(noSuchEngine));

    }
}