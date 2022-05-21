package utils;

import java.util.Random;
import java.util.function.Function;

/**
 * This class contains functions that can be used more than once.
 * */

public class Functions {

    public Functions() { }

    /**
     * 97 -> is the first letter of the alphabet (A).
     * 122 -> is the last letter of the alphabet (Z).
     *
     * @param wordLength -> is the length of the word to be generated.
     * @return a random String word of the indicate length.
     * */
    public static Function<Integer, String> generateRandomWord = wordLength -> new Random()
            .ints(97, 122)
            .limit(wordLength)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();

    /**
     * @param numberLength -> is the length of the number to be generated.
     * @return a random Float of the indicate length.
     * */
    public static Function<Integer, Float> generateRandomNumbers = numberLength -> Float
            .valueOf(new Random()
            .ints(0, 100)
            .limit(numberLength)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString());
}
