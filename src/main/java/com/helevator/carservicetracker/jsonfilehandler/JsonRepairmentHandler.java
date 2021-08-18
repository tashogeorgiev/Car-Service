package com.helevator.carservicetracker.jsonfilehandler;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.helevator.carservicetracker.data.repair.Repairment;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is responsible for reading and writing
 * Repairments from a file in JSON format.
 * It can write and read a Collection of Repairments
 */

public class JsonRepairmentHandler implements JsonOperations<Repairment> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final File jsonFile;

    public JsonRepairmentHandler(File jsonFile){
        this.jsonFile = jsonFile;
    }

    /**
     * Writes a collection of Repairments in JSON format to the file
     * @param values The collection for writing
     */
    @Override
    public void mapObjectsToJSON(Collection<Repairment> values) {
        try {
            OBJECT_MAPPER.writeValue(jsonFile, values);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Read all the Repairments from a file in JSON format
     * and return them as an ArrayList. If any Repairment can not be parsed,
     * an error message is shown and none of the JSON objects will be read
     *
     * @return The ArrayList containing all the Repairments from the file
     */
    @Override
    public ArrayList<Repairment> readObjectsFromJSON() {
        try {
            return OBJECT_MAPPER.readValue(jsonFile,new TypeReference<List<Repairment>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}