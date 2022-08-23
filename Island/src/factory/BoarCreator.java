package factory;

import population.EntityInterface;
import population.herbivore.Boar;
import service.InitialApplication;

import java.io.IOException;

public class BoarCreator extends Creator{
    private static BoarCreator instance;

    public static BoarCreator getInstance(){
        if(instance == null){
            instance = new BoarCreator();
        }
        return instance;
    }

    private BoarCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Boar animal = new Boar();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
