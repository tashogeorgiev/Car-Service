package com.helevator.carservicetests.serializationtest;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.data.vehicle.Car;
import com.helevator.carservicetracker.data.vehicle.EngineType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;

public class SerializeCarTest {

    private final File carsTestDataFile = TestDataHandler.loadTestFile("carsDataTestFile");

    @Test
    public void carSerializationTest(){
        Car car1 = new Car("ABCDEF10123456789","Mercedes","C-Class", 2012 , EngineType.DIESEL);
        Car car2 = null;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(carsTestDataFile));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream(carsTestDataFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            objectOutputStream.writeObject(car1);
            objectOutputStream.close();
            fileOutputStream.close();

            car2 = (Car) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();


            TestDataHandler.clearDataFromFile(carsTestDataFile);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(car1, car2);
    }
}