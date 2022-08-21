package islandGod;

import service.InitialApplication;

import java.util.Locale;

public class IslandUtility {

    public static int getMaxInhabitant(Class clazz){
        String maxPopulationPropertyName = (clazz.getSimpleName() + ".max.count.in.location").toLowerCase(Locale.ROOT);
        return InitialApplication.getIntAppProperties(maxPopulationPropertyName);
    }

}
