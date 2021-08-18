package com.helevator.carservicetracker.repositories;

/**
 * A generic repository interface
 * @param <T> The type of data the implementing repository will store
 */
public interface Repository<T> {
    void addData(T object);
    void readData();
}