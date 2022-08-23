package factory;

import population.EntityInterface;
import population.carnivore.Bear;
import service.InitialApplication;

import java.io.IOException;

public class BearCreator extends Creator{
    private static BearCreator instance;

    public static BearCreator getInstance(){
        if(instance == null){
            instance = new BearCreator();
        }
        return instance;
    }

    private BearCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Bear animal = new Bear();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
