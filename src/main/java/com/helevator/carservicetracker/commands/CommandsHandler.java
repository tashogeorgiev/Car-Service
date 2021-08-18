package com.helevator.carservicetracker.commands;

import com.helevator.carservicetracker.repositories.CarRepository;
import com.helevator.carservicetracker.repositories.RepairRepository;
import com.helevator.carservicetracker.validators.CurrencyValidator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class is responsible for working with the input commands
 */
public class CommandsHandler {
    /**
     * The repositories where the data for all cars and repairs is stored
     */
    private static final CarRepository carRepository = new CarRepository();
    private static final RepairRepository repairRepository = new RepairRepository();

    /**
     * This collection stores all the available commands for the program.
     * Each one is instantiated with the repository it needs for handling the data
     */
    private static final Collection<Command> COMMANDS = List
            .of(new AddCarCommand(carRepository), new AddRepairCommand(repairRepository, carRepository),
                    new AddItemCommand(repairRepository), new PrintRepairCommand(repairRepository),
                    new ReadJsonRepairsCommand(repairRepository));

    /**
     * This map is used to access each command by it's name
     */
    private static final Map<String, Command> COMMAND_MAP = COMMANDS.stream()
            .collect(Collectors.toMap(Command::getCommandName, Function.identity()));


    /**
     * Allows the class to function by getting input from the console.
     * The program will loop until the user types "exit" to leave.
     * Since the Car Service requires a currency to work with, if
     * there is no currency data in the "Currency.txt" file, the program
     * will require the user to enter a valid currency(only 3 characters long)
     */
    public void consoleInput(){
        System.out.println("Welcome to Car Service Tracking System: (type 'exit' to leave)");
        Scanner scanner = new Scanner(System.in);
        String input;

        if(carRepository.getCurrency() == null){
            System.out.println("Enter currency you will work with:");

            while(true){
                input = scanner.nextLine();
                if(CurrencyValidator.validateCurrency(input)){
                    carRepository.setCurrency(input);
                    break;
                }
            }
        }
        System.out.println("You are working in currency: " + carRepository.getCurrency());


        while(true){
            input = scanner.nextLine();

            if("exit".equals(input)){
                break;
            }
            try {
                String[] inputArguments = modifyInput(input);
                processInput(inputArguments);
            }catch (ArrayIndexOutOfBoundsException exception){
                System.out.println("Wrong input format");
            }
        }

        scanner.close();
    }


    /**
     * After the input is modified(by the modifyInput() function),
     * it is processed by getting the command name from the
     * COMMAND_MAP and executing it.
     *
     * @param args The arguments array consisting of 2 values:
     *             [0] - the command name
     *             [1] - the command arguments
     */
    public void processInput(String[] args) {
        if (args.length != 2) {
            System.out.println("Err: Expected only 2 arguments in following format: command command-arguments");
            return;
        }
        String command = args[0];
        String cmdArgs = args[1];

        Command cmd = COMMAND_MAP.get(command);
        if (cmd == null) {
            System.out.println("Err: Unknown command " + command);
            return;
        }

        String result = cmd.execute(cmdArgs);
        System.out.println(result);
    }


    /**
     * Since the input is given as a whole string, it's better to modify it first.
     * It is split by spaces to get each different argument, but the first and second
     * words are the command name, so they have to be concatenated, the rest of the words
     * are joined together(divided by spaces)
     *
     * @param input The input line from the user
     * @return An array consisting of 2 strings - the command name and it's arguments
     * @throws ArrayIndexOutOfBoundsException the input should be split into at least
     *         3 strings - the first and second being the command words, and the rest
     *         being the command arguments
     */
    public String[] modifyInput(String input) throws ArrayIndexOutOfBoundsException{
        String[] arguments = input.split(" ");

        String command = arguments[0] + " " + arguments[1];//first and second words from the input are the command

        StringBuilder commandArgs = new StringBuilder();//we meed to concatenate all the arguments

        for(int i = 2; i < arguments.length; i++){
            commandArgs.append(arguments[i]);
            if(i < arguments.length - 1){//add spaces, we don't want a space after the last word, so we go to the penultimate one
                commandArgs.append(" ");
            }
        }

        //return the command and the arguments
        String[] inputArguments = new String[2];
        inputArguments[0] = command;
        inputArguments[1] = commandArgs.toString();

        return inputArguments;
    }
}