package by.laziuk.controllers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Config config;
    private final static String inputType = "input_type";
    private final Properties properties;

    private Config(){
        properties = new Properties();
        try(InputStream in = new FileInputStream("/Users/illia/projects/cars/config.properties")){
            properties.load(in);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getInputType() {
        return properties.getProperty(inputType);
    }

    public static Config getInstance() {
        if (config == null)
            config = new Config();
        return config;
    }
}
