package com.helevator.carservicetracker.commands;

import com.helevator.carservicetracker.data.repair.Repairment;
import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.repositories.RepairRepository;
import com.helevator.carservicetracker.validators.RepairmentValidator;
import org.apache.commons.lang3.StringUtils;
import java.time.format.DateTimeFormatter;

public class PrintRepairCommand implements Command{
    private static final String COMMAND_NAME = "print repair";

    private final RepairRepository repairRepository;

    public PrintRepairCommand(RepairRepository repairRepository){
        this.repairRepository = repairRepository;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }


    /**
     * @param commandArg The command argument is the Id for
     *                   the repair  we want to print.
     * @return If successful, prints the repair details in a table format,
     *         if not, prints the what the error was
     */
    @Override
    public String execute(String commandArg) {
        String output;
        //cast the input into a variable by using the RepairmentValidator class
        try {
            long repairId = RepairmentValidator.validateRepairId
                    (StringUtils.substringAfter
                            (commandArg, "repairId="));

            //the repair will be printed only if it exists
            if(repairRepository.containsRepair(repairId)){
                Repairment repairToPrint = repairRepository.getRepairById(repairId);

                output = "repairId\t\tvin\t\t\t\t\tdate\t\tmileage(in km)\t\ttotal\n"
                        + repairId + "\t\t"
                        + repairToPrint.getVinForRepair() + "\t"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(repairToPrint.getDateOfRepair()) + "\t"
                        + repairToPrint.getMileage() + "\t\t\t\t"
                        + repairToPrint.getTotalCostForRepair();
            }else{
                throw new IncorrectInputException("This repairId does not exist.");
            }
        }catch (IncorrectInputException inputException){
            output = inputException.getMessage();
        }catch (NullPointerException nullPointerException){
            output = "Unknown input";
        }
        return output;
    }
}