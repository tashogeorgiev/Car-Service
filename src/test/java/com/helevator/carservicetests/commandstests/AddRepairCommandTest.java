package com.helevator.carservicetests.commandstests;


import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.commands.AddRepairCommand;
import com.helevator.carservicetracker.commands.Command;
import com.helevator.carservicetracker.data.vehicle.Car;
import com.helevator.carservicetracker.data.vehicle.EngineType;
import com.helevator.carservicetracker.repositories.CarRepository;
import com.helevator.carservicetracker.repositories.RepairRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;

public class AddRepairCommandTest {
    private final File carsTestDataFile = TestDataHandler.loadTestFile("carsDataTestFile");
    private final File currencyTestFile = TestDataHandler.loadTestFile("currencyTestFile");
    private final File repairsTestDataFile = TestDataHandler.loadTestFile("repairsDataTestFile");

    CarRepository carRepository = new CarRepository(carsTestDataFile,currencyTestFile);
    RepairRepository repairRepository = new RepairRepository(repairsTestDataFile);
    Command addRepairCommand = new AddRepairCommand(repairRepository, carRepository);


    @Test
    public void addRepairTest(){
        carRepository.addData(new Car("ABCDE123455432100","Opel","Corsa", 2010,EngineType.PETROL));

        String commandArgs = "vin=ABCDE123455432100 date=31/01/2021 mileage=220354";

        /*
        In the result output of addRepair we also print the generated repairId
        but here we cannot know what generated Id will be. Still, if the command
        has executed correctly, we will receive the top row of the output, so
        we can see if our output contains it
         */
        String output = addRepairCommand.execute(commandArgs);
        String expectedOutput = "repairId\t\tvin\t\t\t\t\tdate\t\tmileage(in km)\n";

        TestDataHandler.clearDataFromFile(carsTestDataFile);
        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertTrue(output.contains(expectedOutput));
    }


    @Test
    public void addRepairWithNoCarInRepository(){
        String commandArgs = "vin=ABCDE123455432100 date=31/01/2021 mileage=220354";
        String output = addRepairCommand.execute(commandArgs);

        Assertions.assertEquals(output,"This Vin is not added.");

    }


    @Test
    public void addRepairWrongInputTest(){
        String commandArgs = "vin=ABCDE123455432100-date=31/01/2021-mileage=220354";

        String result = addRepairCommand.execute(commandArgs);
        String expectedOutput = "Unknown input";

        Assertions.assertEquals(expectedOutput, result);
    }
}