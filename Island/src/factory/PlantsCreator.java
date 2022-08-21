package factory;

import population.EntityInterface;
import population.plants.Plants;

import java.io.IOException;

public class PlantsCreator extends Creator {
    private static PlantsCreator instance;

    private PlantsCreator() {
    }

    public static PlantsCreator getInstance() {
        if (instance == null) {
            instance = new PlantsCreator();
        }
        return instance;
    }

    @Override
    public EntityInterface createEntity() throws IOException {
        return new Plants();
    }

}
