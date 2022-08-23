package factory;

import population.EntityInterface;
import population.carnivore.*;
import service.InitialApplication;

import java.io.IOException;

public class WolfCreator extends Creator{
    private static WolfCreator instance;

    public static WolfCreator getInstance(){
        if(instance == null){
            instance = new WolfCreator();
        }
        return instance;
    }

    private WolfCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Wolf animal = new Wolf();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
