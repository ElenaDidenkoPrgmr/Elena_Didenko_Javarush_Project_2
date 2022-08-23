package factory;

import population.EntityInterface;
import population.plants.Plants;

import java.io.IOException;

public class PlantsCreator extends Creator {
    private static PlantsCreator instance;

    public static PlantsCreator getInstance() {
        if (instance == null) {
            instance = new PlantsCreator();
        }
        return instance;
    }

    private PlantsCreator() {
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        return new Plants();
    }

}
