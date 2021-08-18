package com.helevator.carservicetests.validationtests.repairvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.RepairmentValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RepairMileageTest {

    @Test
    public void mileageTest(){
        int mileage = 150000;

        Assertions.assertEquals(mileage, RepairmentValidator.validateMileage("150000"));
    }

    @Test
    public void mileageUnder0Test(){
        String mileage = "-20";

        Assertions.assertThrows(IncorrectInputException.class, () -> RepairmentValidator.validateMileage(mileage));
    }

    @Test
    public void mileageParseExceptionTest(){
        String mileage = "123c4";

        Assertions.assertThrows(IncorrectInputException.class, () -> RepairmentValidator.validateMileage(mileage));
    }
}