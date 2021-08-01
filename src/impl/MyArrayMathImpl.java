package impl;


import interfaces.ArrayMath;

import java.util.Random;

/**
 * This class is for MyArrayMathImpl.
 *
 * @author Jacob Yousif
 * @author Domagoj Trupeljak
 * @version 1.0
 * @implNote References (Credit for the Quick-select algorithm):
 * 1-Introduction to algorithms. 2nd edition.
 * Authors: Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest
 * and Clifford Stein. The MIT Press. London, England. Year: 2002
 * <p>
 * 2-Algorithms. Author: Jeff Erickson. Creative Commons Attribution.
 * ISBN: 978-1-792-64483-2. Year: 2019
 * @since 2020-10-13
 */
public class MyArrayMathImpl implements ArrayMath {

    /**
     * A private field for the outer index, it is for testing only.
     * It is not part of the solution.
     */
    private int outerIndex = 0;
    private int innerIndex = 0;

    /**
     * Checks if two arrays contain identical elements.
     * Uses a map for comparison.
     * Executes in O(N).
     *
     * @param array1 first array
     * @param array2 second array
     * @return true if arrays are identical
     */
    @Override
    public boolean isSameCollection(int[] array1, int[] array2) {

        if (array1.length != array2.length) //If lengths are not the same, return false
            return false;

        //Two maps created from arrays
        MyHashTableImpl<Integer, Integer> table1 = createTable(array1); //O(N)
        MyHashTableImpl<Integer, Integer> table2 = createTable(array2); //O(N)

        return table1.compareTo(table2) == 0; //O(N)
    }

    /**
     * Creates a new a map from a given array.
     * Executes in O(N).
     *
     * @param array specified array
     * @return new map
     */
    private MyHashTableImpl<Integer, Integer> createTable(int[] array) {

        //Create and populate a new map
        MyHashTableImpl<Integer, Integer> table = new MyHashTableImpl<>(0.5);
        for (Integer i : array) {
            /*
             * (key, value) := key is the number in the array
             *                 value is the number of appearances in the array
             * */
            Integer value = table.contains(i);
            if (value != null)
                table.insert(i, table.contains(i) + 1); //Increment number of appearances if already exists
            else
                table.insert(i, 1); //Otherwise set the number of appearances to 1
        }

        return table;
    }

    /**
     * Calculates squared distance between vectors by pairing elements
     * with respect to its order in the associated array.
     * Executes in O(N log N)
     *
     * @param array1 first array
     * @param array2 second array
     * @return squared distance
     */
    @Override
    public int minDifferences(int[] array1, int[] array2) {

        if (array1.length != array2.length)
            return -1;

        //Use merge sort to sort both arrays
        MergeSort.sort(array1); //O(N logN)
        MergeSort.sort(array2); //O(N logN)

        //Sum elements at same positions and insert into calculation
        int sumDiff = 0;
        for (int i = 0; i < array1.length; i++)
            sumDiff += Math.pow(array1[i] - (double) array2[i], 2); //O(N)

        return sumDiff; //N logN > N => O(N + N logN + N logN) = O(N log N)
    }

    /**
     * References (Credit for the Quick-select algorithm):
     * 1-Introduction to algorithms. 2nd edition.
     * Authors: Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest
     * and Clifford Stein. The MIT Press. London, England. Year: 2002
     * <p>
     * 2-Algorithms. Author: Jeff Erickson. Creative Commons Attribution.
     * ISBN: 978-1-792-64483-2. Year: 2019
     *
     * <p>
     * The data is unsorted. To use a sorting algorithm to sort the data
     * and then collect the range that would exclude O(n) on the average case, as
     * the best sorting algorithms require O(n log n) on the average case.
     * <p>
     * The best approach to achieve O(n) is to find the value of the
     * upper bound and the value of the lower bound and collect the range between them.
     * <p>
     * In this approach, the Quick-select algorithm will be used to collect
     * the kth elements in the array. The Quick-select is derived from the Quicksort algorithm
     * where it uses the same technique of partitioning and using pivots.
     * This algorithm does not sort entirely; instead, it sorts until it finds the value and returns it.
     * This algorithm is the best choice since it can search in O(n) on the average case.
     * <p>
     * Selection is the best strategy to get the outcome in O(n) on the average case, and that is by
     * using the provided data to pinpoint the kth element and using the Quick-select to pinpoint the
     * value of the kth element. Ex:
     * Assume the length of the array is 10, the upper bound is 80, and the lower bound is 10.
     * upper percentage * n / 100 => 80 * 10/100 = 8. That means eight elements behind the upper bound.
     * lower percentage * n / 100 => 10 * 10/100 = 1. That means one element behind the lower bound.
     * Thus, the next step is to find the 3rd biggest element in the array since the eight elements start
     * exactly from that value and downward. Kth largest = array size - (percentage * n / 100) + 1
     * When it comes to the other kth value, the method should look for the 9th smallest value
     * since after this value comes that element behind the lower bound. The range is not interested
     * in the values that come after the 9th value; rather, it is interested in
     * this value since it is inclusive. Kth smallest = array size - (percentage * n / 100).
     * The number of elements of the range will be 8 - 1 = 7 elements.
     * <p>
     * The running time of Quick-select on average is O(n), but it can be
     * quadratic if the pivot was chosen poorly. Case in point:
     * If the array was sorted and the pivot is either the smallest or the
     * largest value in the array, it will result in quadratic time.
     * To avoid the quadratic time, the best approach is to choose a random pivot.
     * Random pivots will give linear time most of the time, which is O(n).
     * <p>
     * Now, the Quick-select is O(n) on the average case, and the method for collecting
     * the upper and lower bound values is O(n) in the worst-case scenario; it is a linear method.
     * <p>
     * Thus, the Quick-select -in this method- is selecting two values
     * (the upper bound and the lower bound) and that is: 2 * O(n) = 2 O(n)
     * on the average case. Collecting the range is O(n) in the worst-case scenario.
     * Total: 2 O(n) + O(n) = 3 O(n).
     * Hence, the time complexity of this approach is O(n) on the average time.
     *
     * @param arr   the array of the values.
     * @param lower the lower percentage.
     * @param upper the upper percentage.
     * @return the range between the upper and the lower percentages.
     * @implNote This approach includes the largest value in the array,
     * only when the upper explicitly is 100, otherwise it is 90.
     * This only applies to the largest value.
     * If the upper is 85 then it will be rounded to 90.
     * If the upper is 99 then it will be rounded to 90.
     * <p>
     * This approach is only for unique values; i.e. it does not work
     * with arrays that contain duplications. Ex:
     * Assume that A = {1,2,6,3,5,4,9,9,9,9}, Upper = 80 and Lower = 10.
     * In this case the range is = {9,9,5,4,6,3,2}
     * Consequently, the order and the positions of the duplicated nines in ordered array are
     * unknown since this array is not sorted and that makes it impossible to know which
     * ones to include in the range and which ones to exclude, not unless-
     * sorting the array to figure out their positions in order and collect the ones that their
     * positions actually lie within the range.
     * Sorting will affect the time complexity, where O(n) on
     * the average case will be out of the question and not possible.
     */
    @Override
    public int[] getPercentileRange(int[] arr, int lower, int upper) {
        if (arr.length == 0)
            throw new IllegalArgumentException("The array is EMPTY!");
        else if (lower > upper || lower == upper)
            return new int[0];
        else if (lower == 0 && upper == 100)
            return arr;
        int theNumberOfElementsInTheRange = (arr.length * upper / 100) - (arr.length * lower / 100);
        int maximum = (arr.length - (arr.length * upper / 100) + 1);
        int minimum = (arr.length - (arr.length * lower / 100));
        maximum = quickSelect(arr, maximum);
        minimum = quickSelect(arr, minimum);
        return getPercentileRange(arr, maximum, minimum, theNumberOfElementsInTheRange);
    }

    /**
     * References (Credit for the Quick-select algorithm):
     * 1-Introduction to algorithms. 2nd edition.
     * Authors: Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest
     * and Clifford Stein. The MIT Press. London, England. Year: 2002
     * <p>
     * 2-Algorithms. Author: Jeff Erickson. Creative Commons Attribution.
     * ISBN: 978-1-792-64483-2. Year: 2019
     * <p>
     * This the quick-select method where it finds the target value.
     * It partitions the array into two parts around the pivot,
     * and then it will arrange one part less than the pivot and the other for
     * for the values that larger than the pivot.
     * It will look in the part where the kth element lies.
     *
     * @param arr    the array of the values.
     * @param target the kth largest/smallest element in the array.
     * @return the value.
     */
    private int quickSelect(int[] arr, int target) {
        if (target > arr.length || target < 0)
            throw new IndexOutOfBoundsException("The target kth element is not within the array!");
        int leftPointer = 0;
        int rightPointer = arr.length - 1;
        while (leftPointer <= rightPointer) {
            ++outerIndex;
            int index = partition(arr, leftPointer, rightPointer);
            if (index == arr.length - target) return arr[index];
            else if (index > arr.length - target) rightPointer = index - 1;
            else leftPointer = index + 1;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * It divides the array into two parts around the pivot and
     * swaps the values accordingly.
     *
     * @param data         the array of the values.
     * @param leftPointer  the left pointer for the most left side of the array.
     * @param rightPointer the right pointer for the most right side of the array.
     * @return the value of the index.
     */
    private int partition(int[] data, int leftPointer, int rightPointer) {
        int pivot = getRandomPivot(data, leftPointer, rightPointer);
        int val = data[pivot];
        int leftSide = leftPointer;
        swap(data, pivot, rightPointer);
        int index = leftPointer;
        while (index < rightPointer) {
            innerIndex++;
            if (data[index] < val) swap(data, index, leftSide++);
            index++;
        }
        swap(data, rightPointer, leftSide);
        return leftSide;
    }

    /**
     * It returns a random index for the pivot.
     *
     * @param data         the array of the values.
     * @param leftPointer  the left pointer for the most left side of the array.
     * @param rightPointer the right pointer of the most right side of the array.
     * @return the index for the pivot.
     */
    private int getRandomPivot(int[] data, int leftPointer, int rightPointer) {
        return data.length == 2 ? new Random().nextInt(2) :
                new Random().nextInt(rightPointer - leftPointer + 1) + leftPointer;
    }

    /**
     * It swaps values in the array.
     *
     * @param data   the data of the values.
     * @param first  the first index of the values to be swapped.
     * @param second the second index of the values to be swapped.
     */
    private void swap(int[] data, int first, int second) {
        int temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    /**
     * This method run linearly to collect the values between
     * the upper bound and the lower bound, where the upper bound
     * is inclusive and the lower bound is inclusive.
     * The time complexity of this method is
     * O(n) in the worst-case scenario.
     *
     * @param data the array of the values.
     * @param max  the maximum bound; exclusive.
     * @param min  the minimum bound; inclusive.
     * @param size the size of the elements of the range.
     * @return the array of the elements within the range.
     */
    private int[] getPercentileRange(int[] data, int max, int min, int size) {
        int[] range = new int[size];
        int index = 0;
        for (int number : data)
            if (number >= min && number <= max)
                range[index++] = number;
        return range;
    }

    /**
     * It returns the amounts of time, the inner loop have looped.
     * It is for testing purposes only.
     *
     * @return the amount of loops.
     */
    public int getInnerIndex() {
        return innerIndex;
    }

    /**
     * It returns the amounts of time, the outer loop have looped.
     * It is for testing purposes only.
     *
     * @return the amount of loops.
     */
    public int getOuterIndex() {
        return outerIndex;
    }
}
