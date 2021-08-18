package com.helevator.carservicetracker.commands;

import com.helevator.carservicetracker.data.repair.Repairment;
import com.helevator.carservicetracker.jsonfilehandler.JsonRepairmentHandler;
import com.helevator.carservicetracker.repositories.DataLoader;
import com.helevator.carservicetracker.repositories.RepairRepository;

import java.util.ArrayList;

public class ReadJsonRepairsCommand implements Command{
    private static final String COMMAND_NAME = "read JSON_Repairs";
    private final RepairRepository repairRepository;

    public ReadJsonRepairsCommand(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * @param commandArg The command argument is the JSON file name we want read from.
     * @return Returns a message depending on the success of the JSON reading
     */
    @Override
    public String execute(String commandArg) {
        String output = null;
        try {
            JsonRepairmentHandler jsonReader = new JsonRepairmentHandler(DataLoader.loadFile(commandArg));
            ArrayList<Repairment> repairments = jsonReader.readObjectsFromJSON();

            if(repairments != null){
                for(Repairment repairment : repairments){
                    repairRepository.addData(repairment);
                }
                return "Finished reading data from JSON file.";
            }
        }catch (NullPointerException e){
            output = "File not found.";
        }catch (RuntimeException e){
            output = "Empty file or wrong JSON data.";
        }

        return output + "\nCould not read data from JSON file.";
    }
}