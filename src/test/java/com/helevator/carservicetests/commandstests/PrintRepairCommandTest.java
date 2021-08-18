package com.helevator.carservicetests.commandstests;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.commands.Command;
import com.helevator.carservicetracker.commands.PrintRepairCommand;
import com.helevator.carservicetracker.data.repair.Repairment;
import com.helevator.carservicetracker.repositories.RepairRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class PrintRepairCommandTest {
    private final File repairsTestDataFile = TestDataHandler.loadTestFile("repairsDataTestFile");
    RepairRepository repairRepository = new RepairRepository(repairsTestDataFile);
    Command printRepairCommand = new PrintRepairCommand(repairRepository);


    @Test
    public void printRepairTest() {
        LocalDate date = LocalDate.now();
        int mileage = 100000;
        double repairCost = 0;
        Repairment repairment = new Repairment("ABCDE123455432100",date,mileage, new HashSet<>());

        repairRepository.addData(repairment);
        //here we know the repairId because we add it manually, not like through the "add repair" command
        long repairId = repairRepository.getIdForRepair(repairment);

        String result = printRepairCommand.execute("repairId="+repairId);

        String expectedOutput = "repairId\t\tvin\t\t\t\t\tdate\t\tmileage(in km)\t\ttotal\n"
                + repairId + "\t\t" + "ABCDE123455432100" + "\t"
                + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date) + "\t"
                + mileage + "\t\t\t\t" + repairCost;

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertEquals(expectedOutput,result);
    }


    @Test
    public void printRepairNoSuchIdTest() {
        LocalDate date = LocalDate.now();
        int mileage = 100000;
        Repairment repairment = new Repairment("ABCDE123455432100",date,mileage, new HashSet<>());

        repairRepository.addData(repairment);
        long repairId = 1234567890;//this Id will be different from the generated Id

        String result = printRepairCommand.execute("repairId="+repairId);
        String expectedOutput = "This repairId does not exist.";

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertEquals(expectedOutput,result);
    }

    @Test
    public void printRepairWrongInputTest() {
        LocalDate date = LocalDate.now();
        int mileage = 100000;
        Repairment repairment = new Repairment("ABCDE123455432100",date,mileage, new HashSet<>());

        repairRepository.addData(repairment);
        long repairId = repairRepository.getIdForRepair(repairment);

        //input should be repairId, else it will show an error message
        String result = printRepairCommand.execute("repair_Id="+repairId);
        String expectedOutput = "Id format not valid.";

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertEquals(expectedOutput,result);
    }
}
