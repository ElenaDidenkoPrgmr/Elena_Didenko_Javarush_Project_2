package factory;

import population.EntityInterface;
import population.herbivore.Goat;
import service.InitialApplication;

import java.io.IOException;

public class GoatCreator extends Creator{
    private static GoatCreator instance;

    private GoatCreator() {
    }

    public static GoatCreator getInstance(){
        if(instance == null){
            instance = new GoatCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Goat animal = new Goat();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
