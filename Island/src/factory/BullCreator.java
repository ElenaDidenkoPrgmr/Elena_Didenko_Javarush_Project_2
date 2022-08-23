package factory;

import population.EntityInterface;
import population.herbivore.Bull;
import service.InitialApplication;
import java.io.IOException;


public class BullCreator extends Creator{

    private static BullCreator instance;

    public static BullCreator getInstance(){
        if(instance == null){
            instance = new BullCreator();
        }
        return instance;
    }

    private BullCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Bull animal = new Bull();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }

}
