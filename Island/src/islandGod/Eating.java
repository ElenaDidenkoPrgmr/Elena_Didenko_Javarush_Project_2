package islandGod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Getter;
import population.abstracts.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Eating {
    public static HashMap<Class<? extends Entity>, ArrayList<PairEatenAndProbably>> eatingMap;

    static {
        JsonMapper jsonMapper = new JsonMapper();
        File fileFrom = new File("Elena_Didenko_Javarush_Project_2/Island/src/resources/eatingMapTable.json");

        try {
            //eatingMap = jsonMapper.readValue(fileFrom,HashMap.class);
            eatingMap = jsonMapper.readValue(fileFrom, new TypeReference<HashMap<Class<? extends Entity>, ArrayList<PairEatenAndProbably>>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

@Getter
class PairEatenAndProbably {
    private Class<? extends Entity> eaten;
    private Integer probably;
}
