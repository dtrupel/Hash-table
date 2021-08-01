package impl;


import interfaces.ArrayWithPublishedSize;
import interfaces.MyMap;

/**
 * @author Domagoj Trupeljak
 * @author Jacob Yousif
 * @version 1.0
 *
 * Implements a map using a hash table with quadratic probing collision technique.
 */
public class MyHashTableImpl<K, V> implements MyMap<K, V>, ArrayWithPublishedSize,Comparable<MyMap<K, V>> {

    /**
     * Default length for underlying array.
     * Must remain a prime.
     *
     * */
    private static final int DEFAULT_ARRAY_LENGTH = 13;

    private MapEntryImpl<K, V> [] array;
    private final double maxLoadFactor;
    private int currentSize = 0;

    /**
     * A public constructor.
     *
     * @param maxLoadFactor sets the maximum capacity of the
     *                      underlying array before rehash is required.
     * */
    public MyHashTableImpl(double maxLoadFactor) {
	    this.maxLoadFactor = maxLoadFactor;
	    allocateArray(DEFAULT_ARRAY_LENGTH);
    }

    public double getLoadFactor() {
        return (double)currentSize / array.length;
    }

    /**
     * Initializes underlying array.
     *
     * @param newLength sets the length of the array.
     * */
    @SuppressWarnings("unchecked")
    private void allocateArray(int newLength) {
        array = new MapEntryImpl[newLength];
    }

    /**
     * Inserts the element into the table by adding a
     * new (key, value) entry into an array.
     *
     * @param key sets key
     * @param value pairs a value with a given key
     * */
    @Override
    public void insert(K key, V value) {

        int position = findPos(key); //Quadratic probing to find position
        MapEntryImpl<K, V> entry = array[position];

        if(entry != null) {
            array[position].setValue(value); //Override the value and return if the key already exists
            return;
        }

        array[position] = new MapEntryImpl<>(key, value); //If the key does not exist, create a new entry at found position
        currentSize++;

        double currentLoadFactor = getLoadFactor(); //Rehash in case when load factor is reached
        if(currentLoadFactor >= maxLoadFactor)
            rehash();

    }

    /**
     * Finds the position for the new element in the array
     * by using quadratic probing when collision occurs.
     *
     * @param key is given to the hash function to find
     *            the new position for the element.
     * @return new position for the element.
     * */
    private int findPos(K key) {

        int offset = 1; //Probing offset
        int position = hash(key); //Find hashcode of a given key

        //Executes until it finds a new position or a key duplicate
        while(array[position] != null && !array[position].getKey().equals(key)) {

            //Quadratic probing of positions
            position += offset;
            offset += 2;

            //If probing sets the value out of bounds, subtract by length of array
            if(position >= array.length)
                position -= array.length;
        }

        //If the key is duplicate, array[position] is != null ( used with insert "if(entry != null) )
        return position;
    }

    /**
     * Finds the position for the given key by performing a hash function.
     *
     * @param key used for hashCode() value.
     * @return new position for the element.
     * */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % array.length;
    }

    /**
     * Reallocates memory for the array and uses a new hash function
     * to populate it. The new array is at least 2n + 1 in size.
     * Executes in O(N).
     *
     * */
    private void rehash() {

        MapEntryImpl<K, V> [] oldArray = array; //Save old values

        //New array (size is defined by multiplying old length with 2 and finding the next prime value from that point)
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        //Use old values to populate newly allocated array
        for (MapEntryImpl<K, V> entry : oldArray)
            if (entry != null)
                insert(entry.getKey(), entry.getValue());

    }

    /**
     * Finds next prime number from a given value.
     * Executes in O( sqrt(N) ).
     *
     * @param num value from which the search begins.
     * @return next prime number.
     * */
    private int nextPrime(int num) {

        if(num % 2 == 0)
            num++;
        while(!isPrime(num))
            num += 2;

        return num;
    }

    /**
     * Check if the number is prime.
     * Executes in O( sqrt(N) ).
     *
     * @param num checked value.
     * @return whether number is a prime.
     * */
    private boolean isPrime(int num) {

        for(int i = 2; i < Math.sqrt(num); i++) {
            if(num % i == 0)
                return false;
        }
        return true;

    }

    /**
     * Performs a usual deletion of a specified key.
     * Executes in O(1) for load factor <= 0.5.
     *
     * @param key the key chosen for removal.
     * */
    @Override
    public void delete(K key) {

        int position = findPos(key); //Find the position of a key
        if(array[position] == null)
            return; //Return if the key does not exist

        //Otherwise, set the value to null and decrement size
        array[position] = null;
        currentSize--;
    }

    /**
     * Returns the value associated with a given key.
     * Executes in O(1) for load factor <= 0.5.
     *
     * @param key specified key
     * @return value associated with the key
     * */
    @Override
    public V contains(K key) {

        int position = findPos(key); //Find the position of the key
        if(array[position] != null) {
            if(array[position].getKey().equals(key))
                return array[position].getValue(); //Get the element if it exists and values match
        }
        return null; //If element does not exist, return null
    }

    /**
     * Returns the length of the array.
     *
     * @return array length.
     * */
    @Override
    public int getLengthOfArray() {
        return array.length;
    }

    /**
     * Compare to another map.
     * Executes in O(N).
     *
     * @param o defines the element to compare with.
     * @return 1 if true
     *         0 if false
     * */
    @Override
    public int compareTo(MyMap<K, V> o) {

        for(int i = 0; i < array.length; i++) {
            MapEntryImpl<K, V> first = array[i];
            MapEntryImpl<K, V> second = ((MyHashTableImpl<K, V>)o).array[i];
            if(first != null && second != null) {
                if(!first.getKey().equals(second.getKey()) ||
                        !first.getValue().equals(second.getValue()))
                    return 1;
            }
        }
        return 0;
    }

    /**
     * Use wisely to test and debug.
     *
     * */
    public void printHashTable() {

        int i = 0;
        int limit = 10;

        for (MapEntryImpl<K, V> entry : array) {
            if (i >= limit) {
                System.out.println();
                limit *= 2;
            }
            i++;

            if(entry != null)
                System.out.print(entry.getValue() + " ");
        }
    }
}
