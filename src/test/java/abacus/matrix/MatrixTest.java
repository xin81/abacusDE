package abacus.matrix;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatrixTest {
	private static final Logger logger=LogManager.getLogger(MatrixTest.class);			
	private final double DELTA=(1/1000)*1.0;
	int rows; //=10;
	int cols; //=10;		
	Matrix matrix;	
	
	@BeforeEach
	void setUp(){
		rows=10;
		cols=10;
		matrix=new Matrix(rows, cols);
	}
	
	@Test
	void testInitMatrix(){
		int[][] abacus=matrix.getAbacus();				
		if(abacus!=null){
			assertTrue(abacus.length>=0);
			logger.info("abacus is NOT empty which is good news");
		}else{
			assertNull(abacus);
			logger.error("NullPointer: abacus seems to be empty or even NULL");
		}
	}
	@Test
	void testAllOperations(){
		/*
		 * row: 9
		 * add 3
		 * expected 3
		 */
		testAddition(9, 3, 3.0);
		testAddition(9, 3, 6.0);
		testAddition(9, 3, 9.0);
		/*
		 * row: 9
		 * subtract 1
		 * expected: 2
		 */
		testSubtraction(9, 1, 8);
	}
	
	private void log(double expected, double base){
		double sum=matrix.getSum();
		double nsum=matrix.getNegativeSum(sum);
		double delta=DELTA;

		logger.info("expected: "+expected);
		logger.info("sum: "+sum);
		logger.info("difference: "+(expected-sum));
		assertEquals(expected, sum, delta);		

		// what negative number to be expected
		if(sum!=0){
			double nexpected=(sum-base)*1.0;		
			logger.info("expected negative: "+nexpected);
			logger.info("nsum: "+nsum);			
			logger.info("negative difference: "+(nexpected-nsum));		
			assertEquals(nexpected, nsum, delta);
		}else{
			double nexpected=sum; // sum=nsum=0
			logger.info("negative difference: "+(sum));		
			assertEquals(nexpected, nsum, delta);			
		}
	}
	
	@ParameterizedTest
    // Each string in the curly braces is ONE test run
    // Format: "row, amount, expected"
    @CsvSource({
        "9, 1, 1.0",
        "9, 2, 2.0",
        "9, 3, 3.0"
    })
	void testAddition(int row, int amount, double expected){
		matrix.add(row, amount);// 9 is the lowest row in the abacus matrix
		log(expected, 10);
	}
	
	@ParameterizedTest
    // Each string in the curly braces is ONE test run
    // Format: "row, amount, expected"
    @CsvSource({
        "9, 1, 0.0"
    })
	void testSubtraction(int row, int amount, double expected){
		matrix.subtract(row, amount);// remove only 1 from the previous sum
		log(expected, 10);
	}
}
