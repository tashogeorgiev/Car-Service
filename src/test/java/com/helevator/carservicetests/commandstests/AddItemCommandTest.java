package com.helevator.carservicetests.commandstests;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.commands.AddItemCommand;
import com.helevator.carservicetracker.commands.Command;
import com.helevator.carservicetracker.data.repair.Repairment;
import com.helevator.carservicetracker.repositories.RepairRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;

public class AddItemCommandTest {
    private final File repairsTestDataFile = TestDataHandler.loadTestFile("repairsDataTestFile");
    RepairRepository repairRepository = new RepairRepository(repairsTestDataFile);
    Command addItemCommand = new AddItemCommand(repairRepository);


    @Test
    public void addItemTest(){
        Repairment repairment = new Repairment("ABCDE123455432100", LocalDate.now(),100000, new HashSet<>());

        repairRepository.addData(repairment);
        long repairId = repairRepository.getIdForRepair(repairment);

        String commandArgs = "repairId=" + repairId + " description=Motor Oil quantity=4 metric=liter price=10.40";

        String result = addItemCommand.execute(commandArgs);
        String expectedResult = "Added item '" + "Motor Oil" + "' to repair: " + repairId;

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    public void addItemNoSuchIdTest(){
        Repairment repairment = new Repairment("ABCDE123455432100",LocalDate.now(),100000, new HashSet<>());

        repairRepository.addData(repairment);//generates an Id when adding the Repairment
        long repairId = 1234567890;//this Id will be different from the generated Id

        String commandArgs = "repairId=" + repairId + " description=Motor Oil quantity=4 metric=liter price=10.40";

        String result = addItemCommand.execute(commandArgs);
        String expectedResult = "Repair Id does not exist.";

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertEquals(expectedResult,result);
    }


    @Test
    public void addItemWrongInputTest(){
        String commandArgs = "repairId=1234567890 description=Motor Oilquantity=4metric=literprice=10.40";

        String result = addItemCommand.execute(commandArgs);
        String expectedOutput = "Unknown input";

        Assertions.assertEquals(expectedOutput, result);
    }
}