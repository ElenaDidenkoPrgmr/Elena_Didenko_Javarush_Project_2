package service;

import factory.BullCreator;
import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;


public class InitialApplication {
    private Properties appProperties;
    private static InitialApplication instance;
    private InitialApplication() {
        try (FileReader appPropertiesFile = new FileReader("Elena_Didenko_Javarush_Project_2/Island/src/resources/app.properties");) {

            Properties propertiesFromFile = new Properties();
            propertiesFromFile.load(appPropertiesFile);
            this.appProperties = propertiesFromFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static InitialApplication getInstance(){
        if(instance == null){
            instance = new InitialApplication();
        }
        return instance;
    }

    public static int getIntAppProperties(String searchProperty) {
        return Integer.parseInt(getInstance().getAppProperties().getProperty(searchProperty.toLowerCase(Locale.ROOT)));
    }

    public static double getDoubleAppProperties(String searchProperty) {
        return Double.parseDouble(getInstance().getAppProperties().getProperty(searchProperty.toLowerCase(Locale.ROOT)));
    }

    public Properties getAppProperties() {
        return appProperties;
    }
}
