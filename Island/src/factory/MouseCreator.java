package factory;

import population.EntityInterface;
import population.herbivore.Mouse;
import service.InitialApplication;

import java.io.IOException;

public class MouseCreator extends Creator{
    private static MouseCreator instance;

    private MouseCreator() {
    }

    public static MouseCreator getInstance(){
        if(instance == null){
            instance = new MouseCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Mouse animal = new Mouse();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
