package com.helevator.carservicetracker.repositories;

import com.helevator.carservicetracker.data.vehicle.Car;

import java.io.*;
import java.util.HashSet;

/**
 * The CarRepository class stores the data for all the cars
 */

public class CarRepository implements Repository<Car>{

    /**
     * All the cars are stored in a HashSet, since each car's VIN
     * is a unique identifier and there will be no duplicating Cars
     */
    private final HashSet<Car> carList = new HashSet<>();
    private final File carsDataFile;
    private final File currencyFile;
    private String currency;

    /**
     * When a Car is instantiated, the data from
     * the carsDataFile and currencyFile will be read
     */
    public CarRepository(){
        this.carsDataFile = DataLoader.loadFile("carsDataFile");
        this.currencyFile = DataLoader.loadFile("currencyFile");
        readData();
        readCurrency();
    }

    /**
     * Instantiate the CarRepository from different repositories
     * @param differentRepositoryFile Another car data source
     * @param differentCurrencyRepository Another currency data source
     */
    public CarRepository(File differentRepositoryFile, File differentCurrencyRepository){
        this.carsDataFile = differentRepositoryFile;
        this.currencyFile = differentCurrencyRepository;
        readData();
        readCurrency();
    }

    /**
     * Each time a Car is added, it will be stored in memory
     * and then the HashSet containing all Cars will be Serialized in the repository file.
     * @param car The car which will be added
     */
    @Override
    public void addData(Car car) {
        try {
            carList.add(car);
            FileOutputStream fileOutputStream = new FileOutputStream(carsDataFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(carList);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Could not add car, error writing to file.");
        }
    }

    /**
     * The cars data is serialized as a HashSet,
     * so when reading it, it is casted from the file.
     * If successful, the read data is stored in the current HashSet.
     */
    @Override
    public void readData() {
        try {
            FileInputStream fileInputStream = new FileInputStream(carsDataFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashSet<Car> readSet = (HashSet<Car>)objectInputStream.readObject();

            if(readSet.size() > 0) {
                carList.addAll(readSet);
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Empty cars data file.");
        } catch (ClassNotFoundException e){
            System.out.println("Error parsing data for Cars from file");
        }
    }

    /**
     * Attempts to read the data from the given currency file.
     * If it is empty, the CommandsHandler class will force the
     * user to enter a currency to work with.
     */
    private void readCurrency(){
        try {
            BufferedReader currencyReader = new BufferedReader(new FileReader(currencyFile));
            this.currency = currencyReader.readLine();
            currencyReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * If the currency file is empty, this function will be called
     * to set the currency from an input
     * @param inputCurrency The currency the user has chosen to use
     */
    public void setCurrency(String inputCurrency){
        try {
            BufferedWriter currencyWriter = new BufferedWriter(new FileWriter(currencyFile));
            currencyWriter.write(inputCurrency);
            this.currency = inputCurrency;
            currencyWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean containsCarByVin(String vin){
        for(Car car: carList){
            if(car.getVin().equals(vin)){
                return true;
            }
        }
        return false;
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * @return Return a deep copy of the cars HashSet
     */
    public HashSet<Car> getCars(){
        return (HashSet<Car>) carList.clone();
    }

}