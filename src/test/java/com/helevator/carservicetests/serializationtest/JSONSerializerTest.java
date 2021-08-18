package com.helevator.carservicetests.serializationtest;

import com.helevator.carservicetests.TestDataHandler;
import com.helevator.carservicetracker.data.repair.Repairment;
import com.helevator.carservicetracker.jsonfilehandler.JsonRepairmentHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class JSONSerializerTest {
    File jsonFile = TestDataHandler.loadTestFile("JSONTestFile");
    JsonRepairmentHandler jsonFileReader = new JsonRepairmentHandler(jsonFile);

    /**
     * Write an ArrayList of Repairments to a JSON file and read it
     */
    @Test
    public void writeAndReadObjectsFromJsonFileTest(){
        ArrayList<Repairment> expected = new ArrayList<>();

        Repairment repairment1 = new Repairment("ABCDE123455432191",
                 LocalDate.now(), 10000,new HashSet<>());

        Repairment repairment2 = new Repairment("ABCDE123455432192",
                 LocalDate.now(),20000,new HashSet<>());

        expected.add(repairment1);
        expected.add(repairment2);


        jsonFileReader.mapObjectsToJSON(expected);
        ArrayList<Repairment> readRepairments = jsonFileReader.readObjectsFromJSON();

        TestDataHandler.clearDataFromFile(jsonFile);
        Assertions.assertIterableEquals(expected,readRepairments);
    }

    /**
     * If the JSON is in the wrong input format, the function
     * will not be able to read any of the data
     */
    @Test
    public void repairsWithMissingFieldsTest(){
        try {
            //in order to have a wrong input, the JSON string has to be manually written to the file
            //the second item has a wrong name for the vinForRepair field, so an exception should be thrown
            String jsonString = "[ {\n" +
                    "  \"vinForRepair\" : \"ABCDE123455432191\",\n" +
                    "  \"dateOfRepair\" : [ 2021, 8, 18 ],\n" +
                    "  \"mileage\" : 10000,\n" +
                    "  \"items\" : [ ]\n" +
                    "}, {\n" +
                    "  \"vinForRepair1\" : \"ABCDE123455432192\",\n" +
                    "  \"dateOfRepair\" : [ 2021, 8, 18 ],\n" +
                    "  \"mileage\" : 20000,\n" +
                    "  \"items\" : [ ]\n" +
                    "} ]";

            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(jsonString);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertThrows(RuntimeException.class, () -> jsonFileReader.readObjectsFromJSON());

        TestDataHandler.clearDataFromFile(jsonFile);
    }
}