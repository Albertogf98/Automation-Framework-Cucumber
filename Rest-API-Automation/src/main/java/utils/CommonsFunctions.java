package utils;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * This class contains functions that can be used more than once.
 * */

public class CommonsFunctions {

    public CommonsFunctions() { }

    public static BiFunction<Integer, Integer, Integer> generateRandomNumber = (minValue, maxValue) -> new Random()
            .nextInt(minValue + maxValue);
}
