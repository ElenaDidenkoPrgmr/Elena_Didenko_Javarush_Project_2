package factory;

import population.EntityInterface;
import population.herbivore.Caterpillar;
import service.InitialApplication;

import java.io.IOException;

public class CaterpillarCreator extends Creator{
    private static CaterpillarCreator instance;

    private CaterpillarCreator() {
    }

    public static CaterpillarCreator getInstance(){
        if(instance == null){
            instance = new CaterpillarCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        Caterpillar animal = new Caterpillar();
        String searchSatietyProperties = animal.getClass().getSimpleName() + ".full.satiety";
        animal.setSatiety(InitialApplication.getDoubleAppProperties(searchSatietyProperties));
        return animal;
    }
}
