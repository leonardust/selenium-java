package services;

import exceptions.EnvironmentReaderServiceException;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.Properties;

@Log
public class EnvironmentReaderService {
    private EnvironmentReaderService() {
    }

    private static final Properties props = new Properties();

    static {
        String envFile = System.getProperty("env");
        if (envFile == null) {
            envFile = "config";
        }
        String filePath = envFile.concat(".properties");

        try {
            props.load(EnvironmentReaderService.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new EnvironmentReaderServiceException("Did not manage to read properties file");
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

}
