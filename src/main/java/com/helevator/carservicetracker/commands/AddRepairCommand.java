package com.helevator.carservicetracker.commands;

import com.helevator.carservicetracker.data.repair.Repairment;
import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.repositories.CarRepository;
import com.helevator.carservicetracker.repositories.RepairRepository;
import com.helevator.carservicetracker.validators.CarValidator;
import com.helevator.carservicetracker.validators.RepairmentValidator;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class AddRepairCommand implements Command{
    private static final String COMMAND_NAME = "add repair";

    private final RepairRepository repairRepository;
    private final CarRepository carRepository;

    public AddRepairCommand(RepairRepository repairRepository, CarRepository carRepository){
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * @param commandArg The command arguments(the data for the repair) starting with
     *                   the Vin for the car this repair is associated with.
     *                   Adds repairs only to cars which are already added.
     *                   Arguments are written after the command name.
     * @return If successful, prints the details for the repair in a table format,
     *         if not, prints the what the error was
     */
    @Override
    public String execute(String commandArg) {
        String output;
        //cast each input into a variable by using the Validator classes
        try {
            String vin = CarValidator.validateVin
                    (StringUtils.substringBetween
                            (commandArg, "vin=", " date="));

            LocalDate date = RepairmentValidator.validateDate
                    (StringUtils.substringBetween
                            (commandArg, "date=", " mileage="));

            int mileage = RepairmentValidator.validateMileage
                    (StringUtils.substringAfter
                            (commandArg, "mileage="));

            //we will add repairs only to cars which have already been added to our car repository
            if(carRepository.containsCarByVin(vin)) {
                Repairment repairment = new Repairment(vin, date, mileage, new HashSet<>());
                repairRepository.addData(repairment);

                output = "repairId\t\tvin\t\t\t\t\tdate\t\tmileage(in km)\n" +
                        repairRepository.getIdForRepair(repairment) + "\t\t"
                        + vin + "\t"
                        + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date) + "\t"
                        + mileage;
            }else{
                throw new IncorrectInputException("This Vin is not added.");
            }

        }catch (IncorrectInputException inputException){
            output = inputException.getMessage();
        }catch (NullPointerException nullPointerException){
            output = "Unknown input";
        }
        return output;
    }
}