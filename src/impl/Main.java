package impl;


/**
 * This class is for MyArrayMathImpl.
 *
 * @author Domagoj Trupeljak
 * @author Jacob Yousif
 * @version 1.0
 * @since 2020-10-13
 * <p>
 * Assignment 2
 */
public class Main {

    /**
     * It is for testing and analysis.
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        //Dummy example
        String [] keys = UTIL.getKeys();
        String [] values = UTIL.getValues();
        Logger logger = new Logger();
        MyHashTableImpl<String, String> nbaTeams = new MyHashTableImpl<>(0.5);
        for(int i = 0; i < keys.length; i++) {
            nbaTeams.insert(keys[i], values[i]);
            logger.printLine("Current load factor: " + nbaTeams.getLoadFactor());
        }
        logger.printLine("\n<-------------------------------------------------------------------->\n\n");

        int[] data = UTIL.getFilledArray(10000000);
        MyArrayMathImpl arrayMath = new MyArrayMathImpl();
        for (int number : arrayMath.getPercentileRange(data, 20, 95))
            logger.printLine("" + number, false);
        logger.printLine(UTIL.getInfo(arrayMath.getOuterIndex(), arrayMath.getInnerIndex(), data.length));
    }
}
