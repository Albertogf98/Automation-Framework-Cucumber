package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class contains variables that will be used more than once.
 */

public class Constants {

    public Constants() { }

    /** VARIABLES FOR EXPECTED VALUES */
    public final static String EXPECTED_ALERT_TITLE     = "Product added";

    /** VARIABLES FOR PRODUCT NAMES */
    public final static String PRODUCT_DEL_I7_8GB       = "Dell i7 8gb";

    /** VARIABLES FOR FORMS */
    public final static String NAME                      = "Alberto";
    public final static String COUNTRY                   = "Spain";
    public final static String CITY                      = "Granada";
    public final static String MONTH                     = "05";
    public final static String YEAR                      = "2030";

    /**VARIABLES FOR WAITS */
    public final static long TIME_TO_WAIT                = 60;
    public final static long TIME_TO_SLEEP               = 500;

    /**VARIABLES FOR PATHS*/
    public final static Path PATH_CONFIG_PROPERTIES = Paths.get(
            "src",
            "test",
            "resources",
            "config.properties"
    );
}
