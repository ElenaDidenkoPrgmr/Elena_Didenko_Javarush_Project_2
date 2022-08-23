package factory;

import population.EntityInterface;
import population.herbivore.Duck;
import service.InitialApplication;

import java.io.IOException;

public class DuckCreator extends Creator{
    private static DuckCreator instance;

    public static DuckCreator getInstance(){
        if(instance == null){
            instance = new DuckCreator();
        }
        return instance;
    }

    private DuckCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Duck animal = new Duck();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
