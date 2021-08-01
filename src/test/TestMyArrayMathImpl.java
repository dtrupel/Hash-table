package test;

import interfaces.ArrayMath;
import impl.MyArrayMathImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestMyArrayMathImpl {

    @Test
    void TestIsSameCollection() {

	int[] array1 = { 78, 10, 1, 7, 29, 16, 22, 17, 48, 55, 10, 31, 707, 49, 101, 212};
	int[] array2 = { 1, 10, 7, 10, 22, 212, 101, 16, 17, 48, 29, 31, 55, 78, 49, 707};

	ArrayMath mymath = new MyArrayMathImpl();
	Assertions.assertTrue(mymath.isSameCollection(array1, array2));

	int[] array3 = { 10, 1, 7, 9 };
	Assertions.assertFalse(mymath.isSameCollection(array2, array3));

    }

    @Test
    void TestMinDifferences() {
	int[] array1 = { 2, 5, 3, 9 };
	int[] array2 = { 15, 12, 1, 3 };

	ArrayMath myMath = new MyArrayMathImpl();
	Assertions.assertEquals(myMath.minDifferences(array1, array2), 86);

	ArrayMath mymath = new MyArrayMathImpl();
	Assertions.assertEquals(mymath.minDifferences(array1, array2), 86);
    }

    @Test
    void TestGetPercentileRange() {
	int[] array = { 20000, 160, -2, 4, 100, 6, 120, 8, 140, 1800 };
	int[] solution = { 4, 6, 8, 100 };

	ArrayMath myMath = new MyArrayMathImpl();
	Assertions.assertTrue(myMath.isSameCollection(myMath.getPercentileRange(array, 10, 50), solution));

	ArrayMath mymath = new MyArrayMathImpl();
	Assertions.assertTrue(mymath.isSameCollection(mymath.getPercentileRange(array, 10, 50), solution));
    }

}
