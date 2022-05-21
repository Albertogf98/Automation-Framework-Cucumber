package readers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * This class is the properties file reader.
 * */

public class PropertiesReader {

    private final static Path PATH_CONFIG_PROPERTIES = Paths.get(
            "src",
            "test",
            "resources",
            "config.properties"
    );

    private static Properties properties;

    private static void loadProperties() {
        try (InputStream inputStream = new FileInputStream(PATH_CONFIG_PROPERTIES.toAbsolutePath().toString())) {
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
