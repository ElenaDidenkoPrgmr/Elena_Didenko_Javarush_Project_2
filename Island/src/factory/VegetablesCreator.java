package factory;

import population.EntityInterface;
import population.plants.Vegetables;

import java.io.IOException;

public class VegetablesCreator extends Creator {
    private static VegetablesCreator instance;

    private VegetablesCreator() {
    }

    public static VegetablesCreator getInstance() {
        if (instance == null) {
            instance = new VegetablesCreator();
        }
        return instance;
    }


    @Override
    public EntityInterface createEntity() throws IOException {
        return new Vegetables();
    }

}
