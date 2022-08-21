package service;

import java.util.Random;

public class RandomService {
    Random random;
    public RandomService instance;

    private RandomService() {
    }

    public RandomService getInstance(){
        if(instance == null){
            return new RandomService();
        } else return instance;
    }

    /*public int nextInt(int max){
        return new Random.nextInt(max);
    }*/
}
