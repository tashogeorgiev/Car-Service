package com.helevator.carservicetests.repositorytests;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.data.vehicle.Car;
import com.helevator.carservicetracker.data.vehicle.EngineType;
import com.helevator.carservicetracker.repositories.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;

public class CarRepositoryTest {
    private final File carsTestDataFile = TestDataHandler.loadTestFile("carsDataTestFile");
    private final File currencyFile = TestDataHandler.loadTestFile("currencyTestFile");
    CarRepository carRepository = new CarRepository(carsTestDataFile,currencyFile);

    @Test
    public void addCarTest(){
        Car testCar = new Car("ABCDE123455432100","Opel","Corsa", 2010, EngineType.PETROL);
        carRepository.addData(testCar);

        HashSet<Car> testList = new HashSet<>();
        testList.add(testCar);

        TestDataHandler.clearDataFromFile(carsTestDataFile);
        Assertions.assertIterableEquals(testList,carRepository.getCars());
    }

    @Test
    public void readCarsTest(){
        Car testCar = new Car("ABCDE123455432100","Opel","Corsa", 2010, EngineType.PETROL);
        carRepository.addData(testCar);

        CarRepository readCarRepository =  new CarRepository(carsTestDataFile,currencyFile);

        TestDataHandler.clearDataFromFile(carsTestDataFile);

        //the second repository has to read the same data which was from the first repository
        Assertions.assertIterableEquals(carRepository.getCars(),readCarRepository.getCars());
    }

    @Test
    public void setCurrencyTest(){
        String expectedCurrency = "bgn";

        //the currency will be set from the CurrencyTestData file
        String result = carRepository.getCurrency();
        Assertions.assertEquals(expectedCurrency,result);
    }


    @Test
    public void doesNotContainCarTest(){
        Assertions.assertFalse(carRepository.containsCarByVin("ABCDE123455432100"));
    }

    @Test
    public void containsCarTest(){
        Car testCar = new Car("ABCDE123455432100","Opel","Corsa", 2010, EngineType.PETROL);
        carRepository.addData(testCar);

        Assertions.assertTrue(carRepository.containsCarByVin("ABCDE123455432100"));
    }
}