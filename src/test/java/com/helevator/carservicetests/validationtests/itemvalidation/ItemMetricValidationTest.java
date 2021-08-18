package com.helevator.carservicetests.validationtests.itemvalidation;

import com.helevator.carservicetracker.data.repair.Metric;
import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.ItemValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemMetricValidationTest {

    @Test
    public void validateMetricTypeTest(){
        Metric metric = Metric.LITER;

        Assertions.assertEquals(metric, ItemValidator.validateItemMetric("liter"));
    }

    @Test
    public void validateMetricTypeExceptionTest(){
        String noSuchMetric = "pounds";

        Assertions.assertThrows(IncorrectInputException.class, () -> ItemValidator.validateItemMetric(noSuchMetric));

    }
}