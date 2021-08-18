package com.helevator.carservicetests.validationtests.itemvalidation;

import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.ItemValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemDescriptionValidationTest {
    @Test
    public void itemDescriptionTest(){
        String description = "Engineered for enthusiasts seeking maximum protection and performance.";

        Assertions.assertEquals(description, ItemValidator.validateItemDescription(description));
    }

    @Test
    public void itemDescriptionLengthExceptionTest(){
        String descriptionOver255Chars = "Engineered for enthusiasts seeking maximum protection and performance. " +
                "Precision-formulated with cutting-edge technology and a longstanding devotion to making the world’s best motor oil. " +
                "The result: engine protection that blows the doors off the highest industry standards.";

        Assertions.assertThrows(IncorrectInputException.class, () -> ItemValidator.validateItemDescription(descriptionOver255Chars));
    }
}
