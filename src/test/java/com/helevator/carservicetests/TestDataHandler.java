package com.helevator.carservicetests;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * This class is used to load files for the test repositories
 * All the file locations are stored in the application.properties file in the test module.
 */
public class TestDataHandler {
    private static final Properties TEST_PROPERTIES = new Properties();

    /**
     * Load a file from the application.properties available properties
     * @param testFilePropertyName The name of the property containing the file name
     * @return Returns the file if it was found, if not, returns null
     */
    public static File loadTestFile(String testFilePropertyName){
        try {
            TEST_PROPERTIES.load(TestDataHandler.class.getResourceAsStream("/application.properties"));
            return new File(TEST_PROPERTIES.getProperty(testFilePropertyName));
        } catch (IOException e) {
            System.out.println("Could not initialize " + testFilePropertyName);
        }
        return null;
    }

    /**
     * Deletes the contents of a file. Used to clear all
     * the data in a file after a Test function is done
     * reading/writing to it so the next function can
     * use a clean file
     * @param file The file for clearing
     */
    public static void clearDataFromFile(File file){
        try {
            PrintWriter writerToClearFile = new PrintWriter(file);
            writerToClearFile.print("");
            writerToClearFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}