package com.helevator.carservicetracker.repositories;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to load files for the repositories
 * All the file locations are stored in the application.properties file
 */
public class DataLoader {
    private static final Properties PROPERTIES = new Properties();

    /**
     * Given it's property's name, load the file's location
     * from the application.properties file
     * @param filePropertyName The file's property name, as it is written in application.properties
     * @return The file for the given file property name, if the file was not loaded, null is returned
     */
    public static File loadFile(String filePropertyName){
        try {
            PROPERTIES.load(DataLoader.class.getResourceAsStream("/application.properties"));

            return new File(PROPERTIES.getProperty(filePropertyName));
        } catch (IOException e) {
            System.out.println("Could not initialize " + filePropertyName);
        }
        return null;
    }
}