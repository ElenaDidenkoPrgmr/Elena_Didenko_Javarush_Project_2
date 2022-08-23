package islandGod;

import factory.Creator;
import population.Animal;
import population.EntityInterface;
import population.abstracts.AbstractAnimal;
import population.abstracts.AbstractPlants;
import population.abstracts.Entity;
import population.abstracts.Herbivore;
import population.populationProcessor;
import service.InitialApplication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static islandGod.IslandUtility.getMaxInhabitant;

public class Island {
    private int widthIsland = InitialApplication.getIntAppProperties("island.width");
    private int heightIsland = InitialApplication.getIntAppProperties("island.height");

    private Map<Class<? extends Entity>, List<EntityInterface>>[][] mapsOnLocation = new HashMap[widthIsland + 2][heightIsland + 2];
    private final Set<Class> allClassesPopulation = populationProcessor.getAllClassesEntity();

    public Island() {
    }

    public void initialIsland(){
        for (int i = 1; i < widthIsland + 1; i++) {
            for (int j = 1; j < heightIsland + 1; j++) {
                mapsOnLocation[i][j] = new HashMap<>();

                for (Class<? extends Entity> classPopulation : allClassesPopulation) {
                    String maxPopulationPropertyName = (classPopulation.getSimpleName() + ".max.count.in.location").toLowerCase(Locale.ROOT);
                    int maxInhabitant = InitialApplication.getIntAppProperties(maxPopulationPropertyName);

                    int countEntitySomeClass = new Random().nextInt(maxInhabitant);
                    mapsOnLocation[i][j].put(classPopulation, createListEntity(classPopulation, countEntitySomeClass));
                }
            }
        }
    }

    public void lifeCycleIsland() {
        System.out.println(this);
        for (int i = 0; i < 10; i++) {
            updateDailySatiety();
            eatAnimalOnIsland();
            /*System.out.println(this);
            System.out.println("Island after eating: ");
            */
            moveAnimalOnIsland();
            growingPlantsOnIsland();
            /*System.out.println("Island after growing: ");
            System.out.println(this);
            */
            multiplyAnimalOnIsland();
            /*System.out.println("Island after multiply: ");
            System.out.println(this);*/
        }
        System.out.println(this);
    }

    private void multiplyAnimalOnIsland() {
        for (int i = 1; i < widthIsland + 1; i++) {
            for (int j = 1; j < heightIsland + 1; j++) {

                for (Class classPopulation : allClassesPopulation) {
                    List<Animal> childsAnimalList = new ArrayList<>();

                    for (EntityInterface entity : mapsOnLocation[i][j].get(classPopulation)) {
                        if (entity instanceof AbstractAnimal) {
                            AbstractAnimal animal = (AbstractAnimal) entity;
                            //animal starts to multiply

                            if (animal.getAlreadyMultiplied() == false) {
                                List<Animal> oneChild = getChildWithSomebodyPartner(animal, mapsOnLocation[i][j].get(classPopulation));
                                childsAnimalList.addAll(oneChild);
                            }
                        }
                    }
                    mapsOnLocation[i][j].get(classPopulation).addAll(childsAnimalList);
                }
            }
        }
    }


    private void growingPlantsOnIsland() {
        for (int i = 1; i < widthIsland + 1; i++) {
            for (int j = 1; j < heightIsland + 1; j++) {

                for (Class<? extends Entity> classPopulation : allClassesPopulation) {
                    if (AbstractPlants.class.isAssignableFrom(classPopulation)) {
                        String maxPopulationPropertyName = (classPopulation.getSimpleName() + ".max.count.in.location").toLowerCase(Locale.ROOT);
                        int maxInhabitant = InitialApplication.getIntAppProperties(maxPopulationPropertyName);

                        int countPlantsSomeClass = new Random().nextInt(maxInhabitant);
                        mapsOnLocation[i][j].get(classPopulation).addAll(createListEntity(classPopulation, countPlantsSomeClass));
                    }
                }
            }
        }
    }

    private void moveAnimalOnIsland() {
        for (int i = 1; i < widthIsland + 1; i++) {
            for (int j = 1; j < heightIsland + 1; j++) {

                Iterator<Class> classIterator = allClassesPopulation.iterator();
                while (classIterator.hasNext()) {
                    Class classPopulation = classIterator.next();
                    Iterator<EntityInterface> interfaceIterator = mapsOnLocation[i][j].get(classPopulation).iterator();

                    while (interfaceIterator.hasNext()) {
                        EntityInterface entity = interfaceIterator.next();
                        if (entity instanceof AbstractAnimal) {
                            AbstractAnimal animal = (AbstractAnimal) entity;
                            //animal starts to move
                            int newX = i;
                            int newY = j;

                            for (int l = 0; l < animal.getSpeed(); l++) {
                                int potentialNewX = newX;
                                int potentialNewY = newY;
                                int course = new Random().nextInt(4);
                                switch (course) {
                                    case 0: //on UP
                                        potentialNewY--;
                                        break;
                                    case 1: //on right
                                        potentialNewX++;
                                        break;
                                    case 2: //on down
                                        potentialNewY++;
                                        break;
                                    case 3: //on left
                                        potentialNewX--;
                                        break;
                                }
                                if (mapsOnLocation[potentialNewX][potentialNewY] != null &&
                                        mapsOnLocation[potentialNewX][potentialNewY].get(classPopulation).size() < getMaxInhabitant(classPopulation)) {
                                    newX = potentialNewX;
                                    newY = potentialNewY;
                                }
                            }
                            if (i != newX || j != newY) {
                                interfaceIterator.remove();
                                mapsOnLocation[newX][newY].get(classPopulation).add(animal);
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateDailySatiety() {
        for (int i = 1; i < widthIsland + 1; i++) {
            for (int j = 1; j < heightIsland + 1; j++) {

                Iterator<Class> classIterator = allClassesPopulation.iterator();
                while (classIterator.hasNext()) {
                    Class classPopulation = classIterator.next();

                    Iterator<EntityInterface> interfaceIterator = mapsOnLocation[i][j].get(classPopulation).iterator();
                    while (interfaceIterator.hasNext()) {
                        EntityInterface entity = interfaceIterator.next();
                        if (entity instanceof AbstractAnimal) {
                            AbstractAnimal animal = (AbstractAnimal) entity;
                            String fullSatietyPropertyName = (classPopulation.getSimpleName() + ".full.satiety").toLowerCase(Locale.ROOT);
                            double starve = InitialApplication.getDoubleAppProperties(fullSatietyPropertyName) / 4;
                            animal.setSatiety(animal.getSatiety() - starve);
                            if (animal.getSatiety() < 0) {
                                interfaceIterator.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    private void eatAnimalOnIsland() {
        for (int i = 1; i < widthIsland + 1; i++) {
            for (int j = 1; j < heightIsland + 1; j++) {

                for (Class classPopulation : allClassesPopulation) {
                    for (EntityInterface entity : mapsOnLocation[i][j].get(classPopulation)) {
                        if (entity instanceof AbstractAnimal) {
                            AbstractAnimal animal = (AbstractAnimal) entity;

                            //animal starts to eat
                            String fullSatietyPropertyName = (classPopulation.getSimpleName() + ".full.satiety").toLowerCase(Locale.ROOT);
                            double fullSatiety = InitialApplication.getDoubleAppProperties(fullSatietyPropertyName);

                            if (animal.getSatiety() < fullSatiety) {
                                Entity eatenEntity = getEatenEntity(animal, mapsOnLocation[i][j]);
                                if (eatenEntity != null) { //null = nothing to eat
                                    String weightPropertyName = (eatenEntity.getClass().getSimpleName() + ".weight").toLowerCase(Locale.ROOT);
                                    double addedSatiety = InitialApplication.getDoubleAppProperties(weightPropertyName);
                                    if (animal instanceof Herbivore) {
                                        addedSatiety = Math.max(addedSatiety, fullSatiety / 4);
                                    }

                                    double newSatiety = animal.getSatiety() + addedSatiety;
                                    animal.setSatiety((newSatiety>fullSatiety)?fullSatiety:newSatiety);

                                    mapsOnLocation[i][j].get(eatenEntity.getClass()).remove(eatenEntity);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Entity getEatenEntity(AbstractAnimal animalThatEats, Map<Class<? extends Entity>, List<EntityInterface>> entitiesOnLocation) {
        ArrayList<PairEatenAndProbably> tableDinner = Eating.eatingMap.get(animalThatEats.getClass());

        int dinnerPoint = new Random().nextInt(tableDinner.size());
        Class dinnerPointClass = tableDinner.get(dinnerPoint).getEaten();
        int dinnerPointProbably = tableDinner.get(dinnerPoint).getProbably();

        int eatingProbability = new Random().nextInt(101);
        if (eatingProbability <= dinnerPointProbably) {
            if (entitiesOnLocation.get(dinnerPointClass).size() > 0) {
                return (Entity) entitiesOnLocation.get(dinnerPointClass).get(0);
            }
        }
        return null;
    }

    private List<Animal> getChildWithSomebodyPartner(AbstractAnimal animal, List<EntityInterface> entityInterfaces) {
        for (int i = 0; i < entityInterfaces.size(); i++) {
            AbstractAnimal partner = (AbstractAnimal) entityInterfaces.get(i);
            if (animal != partner && partner.getAlreadyMultiplied() == false) {
                animal.setAlreadyMultiplied(true);
                partner.setAlreadyMultiplied(true);
                break;
            }
        }
        boolean random = new Random().nextBoolean();
        if (random) {
            return createListEntity(animal.getClass(), 1);
        } else return createListEntity(animal.getClass(), 0);//null;
    }

    private <T> List<T> createListEntity(Class<? extends Entity> classPopulation, int count) {
        List<T> result = new ArrayList<>();
        try {
            Class<? extends Creator> creatorEntity = (Class<? extends Creator>) Class.forName("factory." + classPopulation.getSimpleName() + "Creator");

            for (int k = 0; k < count; k++) {
                Method creator = creatorEntity.getMethod("getInstance", null);
                result.add((T) ((Creator) creator.invoke(creatorEntity)).createEntity());
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void printIslandMostBiggestClassInEachLocation() {
        for (int i = 1; i < widthIsland + 1; i++) {
            for (int j = 1; j < heightIsland + 1; j++) {
                Class mostBiggestClass = null;
                int countAnimalInMostBiggestClass = 0;
                for (Class classPopulation : allClassesPopulation) {
                    if (mapsOnLocation[i][j].get(classPopulation).size() > countAnimalInMostBiggestClass) {
                        countAnimalInMostBiggestClass = mapsOnLocation[i][j].get(classPopulation).size();
                        mostBiggestClass = classPopulation;
                    }
                }
                System.out.println(new StringBuilder().append("In location [").append(i).append("][").append(j).append("] biggest class is ").append(mostBiggestClass.getSimpleName()).append(". It have ").append(countAnimalInMostBiggestClass).append(" elements").toString());
            }
        }
    }

    public void getStatistics() {

    }

    @Override
    public String toString() {
        return "Island{" +
                printMap(mapsOnLocation) +
                '}';
    }

    public String printMap(Map<Class<? extends Entity>, List<EntityInterface>>[][] map) {
        StringBuilder result = new StringBuilder("\n");
        Map<Class, Integer> totalCounterClasses = new HashMap<>();
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                Set<Map.Entry<Class<? extends Entity>, List<EntityInterface>>> entrySet = map[i][j].entrySet();
                result.append("Animals in location [").append(i).append("-").append(j).append("]:").append("\n");
                for (Map.Entry<Class<? extends Entity>, List<EntityInterface>> pair : entrySet) {
                    String clazz = pair.getKey().getSimpleName();
                    result.append("          ").append(clazz).append(" count: ").append(pair.getValue().size()).append("\n"); //+ animals + "\n";
                    if (totalCounterClasses.containsKey(pair.getKey())) {
                        int newCount = totalCounterClasses.get(pair.getKey()) + pair.getValue().size();
                        totalCounterClasses.put(pair.getKey(), newCount);
                    } else totalCounterClasses.put(pair.getKey(), pair.getValue().size());
                }
            }
        }
        //System.out.println("Total count by classes:"+"\n");
        //System.out.println(totalCounterClasses);
        result.append(totalCounterClasses);
        //return result.toString();
        return totalCounterClasses.toString();
    }
}
