package population;

import service.InitialApplication;

import java.util.Locale;

public interface Move {

    public default int getSpeed(){
        String maxPopulationPropertyName = (this.getClass().getSimpleName() + ".speed").toLowerCase(Locale.ROOT);
        return  InitialApplication.getIntAppProperties(maxPopulationPropertyName);
    }
}
