package factory;

import population.EntityInterface;
import population.carnivore.*;
import service.InitialApplication;

import java.io.IOException;

public class BoaCreator extends Creator{
    private static BoaCreator instance;

    private BoaCreator() {
    }

    public static BoaCreator getInstance(){
        if(instance == null){
            instance = new BoaCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Boa animal = new Boa();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
