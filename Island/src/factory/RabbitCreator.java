package factory;

import population.EntityInterface;
import population.herbivore.Rabbit;
import service.InitialApplication;
import java.io.IOException;

public class RabbitCreator extends Creator{
    private static RabbitCreator instance;

    private RabbitCreator() {
    }

    public static RabbitCreator getInstance(){
        if(instance == null){
            instance = new RabbitCreator();
        }
        return instance;
    }


    @Override
    public EntityInterface createEntity() throws IOException {
        Rabbit animal = new Rabbit();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
