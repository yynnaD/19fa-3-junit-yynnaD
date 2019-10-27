import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * runs a series of tests on the SlipperySlot.jar
 * 
 * BE CAREFUL THAT ITEMS IN values DO NOT OVERLAP, CAN BE PROBLEMATIC
 * Should be good, double check.
 */
class TestSlipperySlot {

	/*
	 * Tests for 5 of a kind.
	 */
	@Test
	void testAllFiveSame() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[]{3,3,3,3,3};
		
		assertEquals(s.payOff(values), 1000000, "failed testAllFiveSame()");
		
	}//testAllFiveSame()
	
	@Test
	void testAllFivePerfSquare() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {9,9,9,9,9};
		
		assertEquals(s.payOff(values), 1000007, "failed testAllFivePerfSquare()");
	}
	
	@Test
	void testAllFiveFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,42,42};
		
		assertEquals(s.payOff(values), 1000002, "failed testAllFiveFortyTwo()");
	}
	
	@Test
	void testAllFivePowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {2,2,2,2,2};
		
		assertEquals(s.payOff(values), 1000003, "failed testAllFIvePowTwo()");
	}
	
	/*
	 * Tests for 4 of a kind and other combinations of winnings.
	 */
	
	@Test
	void testFourSame(){
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,3,7};
		
		assertEquals(s.payOff(values), 10000, "failed testFourSame()");
		
	}//testAllFourSame()
	
	@Test
	void testFourAndPerfSquare() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,3,9};
		
		assertEquals(s.payOff(values), 10007, "failed testFourAndPerfSquare()");
	}
	
	@Test
	void testFourAndFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,3,42};
		
		assertEquals(s.payOff(values), 10002, "failed testFourAndFortyTwo()");
	}
	
	@Test
	void testFourAndPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,3,8};
		
		assertEquals(s.payOff(values), 10003, "failed testFourAndPowTwo()");
	}
	
	@Test
	void testFourPerfSquareAndFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,42,9};
		
		assertEquals(s.payOff(values), 10009, "failed testFourPerfSquareAndFortyTwo()");
	}
	
	@Test
	void testFourSquarePowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {9,9,9,9,2};
		
		assertEquals(s.payOff(values), 10010, "failed testFourSquarePowTwo()");
	}
	
	@Test
	void testFourFortyTwoPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,42,2};
		
		assertEquals(s.payOff(values), 10005, "failed testFourFortyTwoPowTwo()");
	}
	
	@Test
	void TestFourAll(){
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,42,4};
		
		assertEquals(s.payOff(values), 10012, "failed testFourAll()");
	}
	
	/*
	 * Tests for full house combinations.
	 */
	@Test
	void testFullHouse() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,7,7};
		
		assertEquals(s.payOff(values), 5000, "failed testFullHouse()");
	}
	
	@Test
	void testFullHouseSquare() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,9,9};
		
		assertEquals(s.payOff(values), 5007, "testFullHouseSquare()");
	}
	
	@Test
	void testFullHouseFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,42,42};
		
		assertEquals(s.payOff(values), 5002, "failed testFullHouseFortyTwo()");
	}
	
	@Test
	void testFullHousePowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,2,2};
		
		assertEquals(s.payOff(values), 5003, "failed testFullHousePowTwo()");
	}
	
	@Test
	void testFullHouseSquareFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,9,9};
		
		assertEquals(s.payOff(values), 5009, "failed testFullHouseSquareFortyTwo()");
	}
	
	@Test
	void testFullHouseSquarePowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {9,9,9,1,1};
		
		assertEquals(s.payOff(values), 5010, "failed testFullHouseSquarePowTwo()");
	}
	
	@Test
	void testFullHouseFortyTwoPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,2,2};
		
		assertEquals(s.payOff(values), 5005, "failed testFullHouseFortyTwoPowTwo");
	}
	
	@Test
	void testFullHouseAll() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,1,1};
		
		assertEquals(s.payOff(values), 5012, "failed testFullHouseAll()");
	}
	
	/*
	 * Tests for 3 of a kind combinations.
	 */
	@Test
	void testThreeSame() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,6,7};
		
		assertEquals(s.payOff(values), 100, "failed testThreeSame()");	
	}
	
	@Test
	void testThreeAndSquare() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,6,9};
		
		assertEquals(s.payOff(values), 107, "failed testThreeAndSquare");
	}
	
	@Test
	void testThreeAndFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,42,7};
		
		assertEquals(s.payOff(values), 102, "failed testThreeAndForty()");
	}
	
	@Test
	void testThreeAndPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,2,7};
		
		assertEquals(s.payOff(values), 103, "failed testThreeAndPowTwo()");
	}
	
	@Test
	void testThreeSquareFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,9,42};
		
		assertEquals(s.payOff(values), 109, "failed testThreeSquareFortyTwo()");
	}
	
	@Test
	void testThreeSquarePowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,9,2};
		
		assertEquals(s.payOff(values), 110, "failed testThreeSquarePowTwo()");
	}
	
	@Test
	void testThreeFortyTwoPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,3,42,2};
		
		assertEquals(s.payOff(values), 105, "failed testThreeFortyTwoPowTwo(x)");
	}
	
	@Test
	void testThreeAll() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {42,42,42,2,9};
		
		assertEquals(s.payOff(values), 112, "failed testThreeAll()");
	}
	
	/*
	 * Tests for combinations of two.
	 */
	
	@Test
	void testTwoSame() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,10,5,7};
		
		assertEquals(s.payOff(values), 10, "failed testTwoSame()");
	}
	
	@Test
	void testTwoSameAndSquare() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,5,9,7};
		
		assertEquals(s.payOff(values), 17, "failed testTwoSameAndSquare()");
	}
	
	@Test
	void testTwoSameAndFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,5,42,7};
		
		assertEquals(s.payOff(values), 12, "failed testTwoSameAndFortyTwo()");
	}
	
	@Test
	void testTwoSameAndPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,5,2,7};
		
		assertEquals(s.payOff(values), 13, "failed testTwoSameAndPowTwo()");
	}
	
	@Test
	void testTwoSquareFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,5,9,42};
		
		assertEquals(s.payOff(values), 19, "failed testTwoFortyTwo()");
	}
	
	@Test
	void testTwoSquarePowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,5,9,2};
		
		assertEquals(s.payOff(values), 20, "failed testTwoSquarePowTwo()");
	}
	
	@Test
	void testTwoFortyTwoPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,5,42,2};
		
		assertEquals(s.payOff(values), 15, "failed testTwoFortyTwoPowTwo()");
	}
	
	@Test 
	void testTwoAll() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,3,42,9,2};
		
		assertEquals(s.payOff(values), 22, "failed testTwoAll()");
	}
	
	/*
	 * No pair tests.
	 */
	
	@Test
	void testPerfectSquare() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,5,7,9,11};
		
		assertEquals(s.payOff(values), 7, "failed testPerfectSquare()");
	}
	
	@Test
	void testFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,7,5,42,10};
		
		assertEquals(s.payOff(values), 2, "failed testFortyTwo()");
	}
	
	@Test
	void testPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {2,5,7,11,19};
		
		assertEquals(s.payOff(values), 3, "failed testPowTwo()");
	}
	
	@Test
	void testSquareFortyTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,11,5,9,42};
		
		assertEquals(s.payOff(values), 9, "failed testSquareFortyTwo()");
	}
	
	@Test
	void testSquarePowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,11,5,2,9};
		
		assertEquals(s.payOff(values), 10, "failed testSquarePowTwo()");
	}
	
	@Test
	void testFortyTwoPowTwo() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,7,5,42,2};
		
		assertEquals(s.payOff(values), 5, "failed testFortyTwoPowTwo()");
	}
	
	@Test
	void testAll() {
		SlipperySlot s = new SlipperySlot();
		int[] values = new int[] {3,11,42,9,2};
		
		assertEquals(s.payOff(values), 12, "failed testAll()");
	}

}//class TestSlipperySlot
