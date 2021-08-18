package com.helevator.carservicetests.commandstests;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.commands.AddCarCommand;
import com.helevator.carservicetracker.commands.Command;
import com.helevator.carservicetracker.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class AddCarCommandTest {
    private final File carsTestDataFile = TestDataHandler.loadTestFile("carsDataTestFile");
    private final File currencyFile = TestDataHandler.loadTestFile("currencyTestFile");
    Command addCarCommand = new AddCarCommand(new CarRepository(carsTestDataFile,currencyFile));


    @Test
    public void addCarTest(){
        String commandArgs = "vin=VAUZZZ10123456788 make=Mercedes model=C-Class engine=diesel year=2003";

        String output = addCarCommand.execute(commandArgs);
        String expectedOutput = "Added Car{vin='VAUZZZ10123456788', manufacturer='Mercedes', model='C-Class', manufacturingYear=2003, engineType=DIESEL}";

        TestDataHandler.clearDataFromFile(carsTestDataFile);
        Assertions.assertEquals(expectedOutput, output);
    }


    @Test
    public void addCarWrongInputVariableTest(){
        //vin has to be 17 characters long
        String commandArgs = "vin=VAUZZZ101 make=Mercedes model=C-Class engine=diesel year=2003";

        String output = addCarCommand.execute(commandArgs);
        String expectedOutput = "This vin is not valid: VAUZZZ101";

        Assertions.assertEquals(expectedOutput, output);
    }


    @Test
    public void addCarWrongInputTest(){
        String commandArgs = "vin=VAUZZZ101make=Mercedesmodel=C-Classengine=dieselyear=2003";

        String result = addCarCommand.execute(commandArgs);
        String expectedOutput = "Unknown input";
        Assertions.assertEquals(expectedOutput, result);
    }
}