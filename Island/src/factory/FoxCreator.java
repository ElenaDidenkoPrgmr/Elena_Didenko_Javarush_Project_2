package factory;

import population.EntityInterface;
import population.carnivore.Fox;
import service.InitialApplication;

import java.io.IOException;

public class FoxCreator extends Creator{
    private static FoxCreator instance;

    public static FoxCreator getInstance(){
        if(instance == null){
            instance = new FoxCreator();
        }
        return instance;
    }

    private FoxCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Fox animal = new Fox();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
