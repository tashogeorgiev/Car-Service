package com.helevator.carservicetracker.commands;

/**
 * The interface which is the template for all the commands
 */
public interface Command {
    String getCommandName();
    String execute(String commandArg);
}