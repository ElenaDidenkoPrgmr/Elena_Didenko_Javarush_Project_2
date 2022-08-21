package population;

import service.AccessingAllClassesInPackage;

import java.util.Set;

public class populationProcessor {

    public static Set<Class> getAllClassesEntity(){
        AccessingAllClassesInPackage instance = new AccessingAllClassesInPackage();
        Set<Class> allClassEntity = instance.findAllClassesUsingClassLoader("population.herbivore");
        allClassEntity.addAll(instance.findAllClassesUsingClassLoader("population.carnivore"));
        allClassEntity.addAll(instance.findAllClassesUsingClassLoader("population.plants"));
        return allClassEntity;
    }

    /*public static Set<Class<?>> getAllClassesEntity2(){
        AccessingAllClassesInPackage instance = new AccessingAllClassesInPackage();
        Set<Class<?>> allClassEntity = instance.findAllClassesUsingClassLoader("population.herbivore");
        allClassEntity.addAll(instance.findAllClassesUsingClassLoader("population.carnivore"));
        allClassEntity.addAll(instance.findAllClassesUsingClassLoader("population.plants"));
        return allClassEntity;
    }*/

}
