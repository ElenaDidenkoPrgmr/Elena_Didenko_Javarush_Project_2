package islandGod;

import population.abstracts.Entity;
import service.InitialApplication;

import java.util.Locale;

public class IslandUtility {

    public static int getMaxInhabitant(Class<? extends Entity> clazz){
        String maxPopulationPropertyName = (clazz.getSimpleName() + ".max.count.in.location").toLowerCase(Locale.ROOT);
        return InitialApplication.getIntAppProperties(maxPopulationPropertyName);
    }

}
