package com.helevator.carservicetests.serializationtest;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.data.repair.Repairment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class SerializeRepairTest {

    private final File repairsTestDataFile = TestDataHandler.loadTestFile("repairsDataTestFile");

    @Test
    public void repairSerializationTest() {
        Repairment repair1 = new Repairment("VAUZZZ10123456789", LocalDate.now(),100000, new HashSet<>());
        Repairment repair2 = null;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(repairsTestDataFile));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream(repairsTestDataFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            objectOutputStream.writeObject(repair1);
            objectOutputStream.close();
            fileOutputStream.close();

            repair2 = (Repairment) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();


            TestDataHandler.clearDataFromFile(repairsTestDataFile);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(repair1, repair2);
    }
}