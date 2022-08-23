package factory;

import population.EntityInterface;
import population.carnivore.Eagle;
import service.InitialApplication;

import java.io.IOException;

public class EagleCreator extends Creator{
    private static EagleCreator instance;

    public static EagleCreator getInstance(){
        if(instance == null){
            instance = new EagleCreator();
        }
        return instance;
    }

    private EagleCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Eagle animal = new Eagle();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
