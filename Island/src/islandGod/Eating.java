package islandGod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import population.abstracts.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Eating {
    public static HashMap<Class<? extends Entity>, ArrayList<PairEatenAndProbably>> eatingMap;

    static {
        JsonMapper jsonMapper = new JsonMapper();
        File fileFrom = new File("Island/src/resources/eatingMapTable.json");

        try {
            //eatingMap = jsonMapper.readValue(fileFrom,HashMap.class);
            eatingMap = jsonMapper.readValue(fileFrom,new TypeReference<HashMap<Class<?extends Entity>, ArrayList<PairEatenAndProbably>>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Eating{" +
                "eatingMap=" + eatingMap +
                '}';
    }
}

class PairEatenAndProbably {
    private Class<?extends Entity> eaten;
    private Integer probably;

    public Class<? extends Entity> getEaten() {
        return eaten;
    }

    public Integer getProbably() {
        return probably;
    }

}
