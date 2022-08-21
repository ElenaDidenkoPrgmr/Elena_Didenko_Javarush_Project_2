package population.carnivore;

import lombok.Data;
import population.Animal;
import population.abstracts.Carnivore;
import service.InitialApplication;

import java.util.Locale;

@Data
public class Eagle extends Carnivore implements Animal {

    @Override
    public int move() {
        String maxPopulationPropertyName = (this.getClass().getSimpleName() + ".speed").toLowerCase(Locale.ROOT);
        return  InitialApplication.getIntAppProperties(maxPopulationPropertyName);

    }

    }
