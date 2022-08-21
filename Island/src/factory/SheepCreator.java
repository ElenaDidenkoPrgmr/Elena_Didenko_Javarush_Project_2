package factory;

import population.EntityInterface;
import population.herbivore.Sheep;
import service.InitialApplication;

import java.io.IOException;

public class SheepCreator extends Creator{
    private static SheepCreator instance;

    private SheepCreator() {
    }

    public static SheepCreator getInstance(){
        if(instance == null){
            instance = new SheepCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Sheep animal = new Sheep();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
