package com.helevator.carservicetracker.commands;

import com.helevator.carservicetracker.repositories.CarRepository;
import com.helevator.carservicetracker.data.vehicle.Car;
import com.helevator.carservicetracker.data.vehicle.EngineType;
import com.helevator.carservicetracker.exceptions.IncorrectInputException;
import com.helevator.carservicetracker.validators.CarValidator;
import org.apache.commons.lang3.StringUtils;


public class AddCarCommand implements Command{
    private static final String COMMAND_NAME = "add car";
    private final CarRepository carRepository;

    public AddCarCommand(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * @param commandArg The command arguments(the data for the car),
     *                   which are written after the command name.
     * @return If successful, prints the added car's data,
     *         if not, prints the what the error was
     */
    @Override
    public String execute(String commandArg) {
        String output;
        //parse each input into a variable by using the CarValidator class
        try {
            String vin = CarValidator.validateVin
                    (StringUtils.substringBetween
                            (commandArg, "vin=", " make="));

            String make = CarValidator.validateManufacturer
                    (StringUtils.substringBetween
                            (commandArg, "make=", " model="));

            String model = CarValidator.validateModel
                    (StringUtils.substringBetween
                            (commandArg, "model=", " engine="));

            EngineType engine = CarValidator.validateEngineType
                    (StringUtils.substringBetween
                            (commandArg, "engine=", " year="));

            int year = CarValidator.validateYear
                    (StringUtils.substringAfter
                            (commandArg, "year="));

            Car car = new Car(vin, make, model, year, engine);
            carRepository.addData(car);
            output = "Added " + car;

        }catch (IncorrectInputException inputException){
            output = inputException.getMessage();
        }catch (NullPointerException nullPointerException){
            output = "Unknown input";
        }
        return output;
    }
}