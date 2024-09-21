import Controller.AnimalController;
import Model.*;
import View.AnimalView;


public class Main {
    public static void main(String[] args) {

        Commands allCommand = new Commands();
        Animals animalRegistry = new Animals();
        AnimalView av = new AnimalView();

        AnimalController ac = new AnimalController(av, animalRegistry, allCommand);
        ac.start();

    }
}
