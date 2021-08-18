package com.helevator.carservicetests.validationtests.carvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.CarValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class VinValidationTest {

    @Test
    public void validateVinTest(){
        String vin = "VAUZZZ10123456789";
        Assertions.assertEquals(vin, CarValidator.validateVin("VAUZZZ10123456789"));
    }

    @Test
    public void vinWithIExceptionTest(){
        String vinWithI = "IAUZZZ10123456789";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateVin(vinWithI));
    }

    @Test
    public void vinWithOExceptionTest(){
        String vinWithO = "OAUZZZ10123456789";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateVin(vinWithO));
    }

    @Test
    public void vinWithQExceptionTest(){
        String vinWithQ = "QAUZZZ10123456789";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateVin(vinWithQ));
    }

    @Test
    public void vinOverMaxLengthExceptionTest(){
        String vinOver17Chars = "QAUZZZ1012345678912345";

        Assertions.assertThrows(IncorrectInputException.class, () -> CarValidator.validateVin(vinOver17Chars));
    }

}