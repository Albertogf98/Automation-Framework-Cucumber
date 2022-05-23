package readers;

import utils.Constants;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is the properties file reader.
 * */

public class PropertiesReader {

    private static Properties properties;
    public PropertiesReader() { }

    private static void loadProperties() {
        try (InputStream inputStream = new FileInputStream(Constants.PATH_CONFIG_PROPERTIES.toAbsolutePath().toString())) {
            if (properties == null)
                properties = new Properties();

            properties.load(inputStream);

        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (properties == null)
            loadProperties();

        return properties.getProperty(key);
    }
}
