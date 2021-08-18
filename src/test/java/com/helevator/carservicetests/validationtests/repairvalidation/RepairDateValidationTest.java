package com.helevator.carservicetests.validationtests.repairvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.RepairmentValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RepairDateValidationTest {

    @Test
    public void validateDateTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String validationDate = "18/08/2021";
        Assertions.assertEquals(validationDate, formatter.format(RepairmentValidator.validateDate(validationDate)));
    }

    @Test
    public void wrongDateFormatExceptionTest(){
        String validationDateMonth = "12/15/2021";
        String validationDateDay = "32/01/2021";
        String validationDateReverse = "2021/05/04";

        Assertions.assertThrows(IncorrectInputException.class, () -> RepairmentValidator.validateDate(validationDateMonth));
        Assertions.assertThrows(IncorrectInputException.class, () -> RepairmentValidator.validateDate(validationDateDay));
        Assertions.assertThrows(IncorrectInputException.class, () -> RepairmentValidator.validateDate(validationDateReverse));
    }
}