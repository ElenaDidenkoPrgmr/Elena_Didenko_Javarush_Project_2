package factory;

import population.EntityInterface;
import population.herbivore.Duck;
import service.InitialApplication;

import java.io.IOException;

public class DuckCreator extends Creator{
    private static DuckCreator instance;

    private DuckCreator() {
    }

    public static DuckCreator getInstance(){
        if(instance == null){
            instance = new DuckCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Duck animal = new Duck();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
