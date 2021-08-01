package impl;


import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * This class is for testing only.
 * It help in terms of creating test data.
 * It is not part of the initial solution.
 *
 * @author Domagoj Trupeljak
 * @author Jacob Yousif
 * @version 1.0
 * @since 2020-10-13
 */
public class UTIL {

    /**
     * A private constructor, to prevent making instances.
     */
    private UTIL() {

    }

    /**
     * It generates array filled with integers.
     *
     * @param size the size of the array.
     * @return array of unique values.
     */
    public static int[] getFilledArray(int size) {
        if (size > 10000000)
            throw new IllegalArgumentException("The number is out of the limit.");
        Set<Integer> set = new LinkedHashSet<>();
        int[] data = new int[size];
        int index = 0;
        while (index < size) {
            int temp = new Random().nextInt(Integer.MAX_VALUE - 50000000) + 50000000;
            if (!set.contains(temp)) {
                set.add(temp);
                data[index++] = temp;
            }
        }
        return data;
    }

    /**
     * It returns a string with info.
     *
     * @param outer the amount of the outer loops.
     * @param inner the amount of the inner loops.
     * @param size  the size of the array.
     * @return the string with info.
     */
    public static String getInfo(int outer, int inner, int size) {
        int logN = (int) (Math.log(size) / Math.log(2));
        return "\n\nThe array size is: " + size +
                ". The Quick-select have required external loops to collect the kth largest " +
                "and the kth " + "smallest. \nThe external loops are: " + outer +
                " times and these " + outer + " external loops have required inner loops " +
                "that are in total: " + inner + " times. \nNow to collect the range between the largest value " +
                "and the smallest value," + " that required linear time in the worst-case scenario\nwhich is " +
                size + " loops. \n\nThe total operation for collecting the " +
                "range of the percentiles required this amount of loops, which is " +
                (inner + size) + " times.\nThis is O(n) on the average case because " +
                "if the solution was O(n log n) then the amount of loops will be: " + size *
                logN + " where log2(" + size + ") is " + logN + ".";
    }

    /**
     * Dummy Text.
     *
     * @return String array
     */
    public static String[] getKeys() {
        return new String[]{"Atlanta", "Boston", "Chicago", "Cleveland", "Dallas", "Houston", "Indiana", "Los Angeles", "Los Angeles",
                "Miami", "Minnesota", "Toronto", "Washington", "San Antonio", "Sacramento", "Phoenix"};
    }

    /**
     * Dummy Text.
     *
     * @return String array
     */
    public static String[] getValues() {
        return new String[]{"Hawks", "Celtics", "Bulls", "Cavaliers", "Maverics", "Rockets", "Pacers", "Lakers", "Clippers",
                "Heat", "Timberwolves", "Raptors", "Wizards", "Spurs", "Kings", "Suns"};
    }
}
