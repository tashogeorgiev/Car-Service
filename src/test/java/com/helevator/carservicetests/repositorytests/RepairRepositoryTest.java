package com.helevator.carservicetests.repositorytests;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.data.repair.Item;
import com.helevator.carservicetracker.data.repair.Metric;
import com.helevator.carservicetracker.data.repair.Repairment;
import com.helevator.carservicetracker.repositories.RepairRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;

public class RepairRepositoryTest {
    private final File repairsTestDataFile = TestDataHandler.loadTestFile("repairsDataTestFile");
    RepairRepository repairRepository = new RepairRepository(repairsTestDataFile);


    @Test
    public void addRepairTest(){
        Repairment repairment = new Repairment("ABCDE123455432100", LocalDate.now(),100000, new HashSet<>());
        repairRepository.addData(repairment);

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertTrue(repairRepository.getRepairsData().size() > 0);
    }

    @Test
    public void readRepairsData(){
        Repairment repairment = new Repairment("ABCDE123455432100",LocalDate.now(),100000, new HashSet<>());
        repairRepository.addData(repairment);

        RepairRepository readRepairRepository = new RepairRepository(repairsTestDataFile);

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        //the readRepository should have the same data as the first repository
        Assertions.assertEquals(repairRepository.getRepairsData(),readRepairRepository.getRepairsData());
    }

    @Test
    public void addItemToRepair(){
        HashSet<Item> expectedItems = new HashSet<>();
        Item item = new Item("testItem",4, Metric.UNIT,20.2);
        expectedItems.add(item);

        Repairment repairment = new Repairment("ABCDE123455432100",LocalDate.now(),100000, new HashSet<>());
        repairRepository.addData(repairment);
        long repairId = repairRepository.getIdForRepair(repairment);

        repairRepository.addItemToRepair(repairId,item);

        TestDataHandler.clearDataFromFile(repairsTestDataFile);
        Assertions.assertEquals(expectedItems,repairment.getItems());
    }

    @Test
    public void doesNotContainRepair(){
        Assertions.assertFalse(repairRepository.containsRepair(1234567890));
    }

    @Test
    public void containsRepair(){
        Repairment repairment = new Repairment("ABCDE123455432100",LocalDate.now(),100000, new HashSet<>());

        repairRepository.addData(repairment);
        long repairId = repairRepository.getIdForRepair(repairment);

        TestDataHandler.clearDataFromFile(repairsTestDataFile);

        Assertions.assertTrue(repairRepository.containsRepair(repairId));
    }
}
