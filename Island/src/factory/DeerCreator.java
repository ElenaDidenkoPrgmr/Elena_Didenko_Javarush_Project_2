package factory;

import population.EntityInterface;
import population.herbivore.Deer;
import service.InitialApplication;

import java.io.IOException;

public class DeerCreator extends Creator{
    private static DeerCreator instance;

    private DeerCreator() {
    }

    public static DeerCreator getInstance(){
        if(instance == null){
            instance = new DeerCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Deer animal = new Deer();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
