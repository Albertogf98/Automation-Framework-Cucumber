package utils;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * This class contains functions that can be used more than once.
 * */

public class Functions {

    public Functions() { }

    public static BiFunction<Integer, Integer, Integer> generateRandomNumber = (minValue, maxValue) -> new Random()
            .nextInt(minValue + maxValue) + minValue;
}
