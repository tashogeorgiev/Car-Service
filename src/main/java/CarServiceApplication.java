import com.helevator.carservicetracker.commands.CommandsHandler;

public class CarServiceApplication {
    public static void main(String[] args){
        CommandsHandler commandsHandler = new CommandsHandler();
        commandsHandler.consoleInput();
    }
}