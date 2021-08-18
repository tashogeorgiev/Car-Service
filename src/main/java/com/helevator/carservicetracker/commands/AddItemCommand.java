package com.helevator.carservicetracker.commands;

import com.helevator.carservicetracker.data.repair.Item;
import com.helevator.carservicetracker.data.repair.Metric;
import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.repositories.RepairRepository;
import com.helevator.carservicetracker.validators.ItemValidator;
import com.helevator.carservicetracker.validators.RepairmentValidator;
import org.apache.commons.lang3.StringUtils;


public class AddItemCommand implements Command{
    private static final String COMMAND_NAME = "add item";

    private final RepairRepository repairRepository;

    public AddItemCommand(RepairRepository repairRepository){
        this.repairRepository = repairRepository;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     *
     * @param commandArg The command arguments(the data for the item) starting with
     *                   the Id for the repair to which the item will be added.
     *                   Arguments are written after the command name.
     * @return If successful, prints the item description and the repair Id,
     *         if not, prints the what the error was
     */
    @Override
    public String execute(String commandArg) {
        String output;
        //parse each input into a variable by using the Validator classes
        try {
            long repairId = RepairmentValidator.validateRepairId
                    (StringUtils.substringBetween
                            (commandArg,"repairId="," description="));

            String description = ItemValidator.validateItemDescription
                    (StringUtils.substringBetween
                            (commandArg,"description=", " quantity="));

            int quantity = ItemValidator.validateItemQuantity
                    (StringUtils.substringBetween
                            (commandArg,"quantity="," metric="));

            Metric metric = ItemValidator.validateItemMetric
                    (StringUtils.substringBetween
                            (commandArg,"metric="," price="));

            double price = ItemValidator.validatePrice
                    (StringUtils.substringAfter
                            (commandArg,"price="));


            //we will add the item only if a repair with the given Id exists
            if(repairRepository.containsRepair(repairId)) {
                Item item = new Item(description, quantity, metric, price);
                repairRepository.addItemToRepair(repairId, item);

                output = "Added item '" + item.getDescription() + "' to repair: " + repairId;
            }else{
                throw new IncorrectInputException("Repair Id does not exist.");
            }

        }catch (IncorrectInputException inputException){
            output = inputException.getMessage();
        }catch (NullPointerException nullPointerException){
            output = "Unknown input";
        }
        return output;
    }
}