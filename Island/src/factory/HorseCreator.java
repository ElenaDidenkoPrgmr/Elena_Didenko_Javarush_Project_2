package factory;

import population.EntityInterface;
import population.herbivore.*;
import service.InitialApplication;

import java.io.IOException;

public class HorseCreator extends Creator{
    private static HorseCreator instance;

    public static HorseCreator getInstance(){
        if(instance == null){
            instance = new HorseCreator();
        }
        return instance;
    }

    private HorseCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Horse animal = new Horse();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
