package factory;

import population.EntityInterface;
import population.plants.Vegetables;

import java.io.IOException;

public class VegetablesCreator extends Creator {
    private static VegetablesCreator instance;

    public static VegetablesCreator getInstance() {
        if (instance == null) {
            instance = new VegetablesCreator();
        }
        return instance;
    }

    private VegetablesCreator() {
    }


    @Override
    public EntityInterface createEntity() throws IOException {
        return new Vegetables();
    }

}
