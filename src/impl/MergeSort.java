package impl;

/**
 * @author Domagoj Trupeljak
 * @author Jacob Yousif
 * @version 1.0
 *
 * Class used to separate the logic for merge sorting method.
 */
public class MergeSort {

    /**
     * Merge sort.
     *
     * @param array array to sort
     * */
    public static void sort(int [] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * Sort with specified boundaries.
     *
     * @param array array to sort
     * @param left left border
     * @param right right border
     * */
    private static void sort(int [] array, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2; //Get mid value

            //Sort halves
            sort(array, left, mid);
            sort(array, mid + 1, right);

            //Merge sorted halves
            merge(array, left, mid, right);
        }
    }

    /**
     * Merges two arrays into one.
     *
     * @param array resulting array
     * @param left left border index
     * @param mid mid index
     * @param right right border index
     * */
    private static void merge(int[] array, int left, int mid, int right) {

        int sizeFirst = mid - left + 1;
        int[] leftArray = new int[sizeFirst];
        int sizeSecond = right - mid;
        int[] rightArray = new int[sizeSecond];

        if (sizeFirst >= 0)
            System.arraycopy(array, left, leftArray, 0, sizeFirst);
        if (sizeSecond >= 0)
            System.arraycopy(array, mid + 1, rightArray, 0, sizeSecond);

        int i = 0;
        int j = 0;
        int k = left;
        while (i < sizeFirst && j < sizeSecond) {
            if (leftArray[i] <= rightArray[j]) array[k++] = leftArray[i++];
            else array[k++] = rightArray[j++];
        }

        while (i < sizeFirst) array[k++] = leftArray[i++];
        while (j < sizeSecond) array[k++] = rightArray[j++];
    }
}
