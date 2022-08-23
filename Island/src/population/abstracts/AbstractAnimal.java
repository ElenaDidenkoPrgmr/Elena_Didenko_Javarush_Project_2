package population.abstracts;

import lombok.ToString;
import population.Animal;

@ToString
public abstract class AbstractAnimal extends Entity implements Animal {

    private double satiety;
    private boolean alreadyMultiplied = false;

    public boolean getAlreadyMultiplied() {
        return alreadyMultiplied;
    }

    public void setAlreadyMultiplied(boolean alreadyMultiplied) {
        this.alreadyMultiplied = alreadyMultiplied;
    }


    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }
}
