package utils;


import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class contains variables that will be used more than once.
 */

public class Constants {

    /** PATHS */
    public final static Path PATH_JSON_POST         = Paths.get("src", "main", "resources", "body.json");

    /** REGEXS */
    public final static String REGEX_CORCHETTES     = "[\\[\\]]";
    public final static String REGEX_INTEGER_NUMBER = "\\d*";


    /** VARIABLES FOR PETS */
    public final static String PET_NAME             = "Manchita";
    public final static String CATEGORY_NAME        = "Rabbit";
    public final static String TAG_NAME             = "#Happy";

}
