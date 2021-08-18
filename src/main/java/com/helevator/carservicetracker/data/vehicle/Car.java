package com.helevator.carservicetracker.data.vehicle;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Car class stores information about the vehicle which came in for the repair
 */

public class Car implements Serializable {
    private String vin;
    private String manufacturer;
    private String model;
    private int manufacturingYear;
    private EngineType engineType;

    public Car(String vin, String manufacturer, String model, int manufacturingYear, EngineType engineType) {
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineType = engineType;
        this.manufacturingYear = manufacturingYear;
    }

    Car(){}

    public String getVin() {
        return vin;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }


    @Override
    public String toString() {
        return "Car{" + "vin='" + vin + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", manufacturingYear=" + manufacturingYear +
                ", engineType=" + engineType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return vin.equals(car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }
}