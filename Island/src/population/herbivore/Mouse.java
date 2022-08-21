package population.herbivore;

import lombok.Data;
import population.Animal;
import population.abstracts.Herbivore;
import service.InitialApplication;

import java.util.Locale;

@Data
public class Mouse extends Herbivore implements Animal {

    @Override
    public int move() {
        String maxPopulationPropertyName = (this.getClass().getSimpleName() + ".speed").toLowerCase(Locale.ROOT);
        return  InitialApplication.getIntAppProperties(maxPopulationPropertyName);
    }
}
