package population.herbivore;

import lombok.Data;
import population.Animal;
import population.abstracts.AbstractAnimal;
import population.abstracts.Herbivore;
import service.InitialApplication;

import java.util.Locale;

@Data
public class Rabbit extends Herbivore implements Animal {
}
