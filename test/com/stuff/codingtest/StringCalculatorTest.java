package com.stuff.codingtest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

	 private StringCalculator calculator;

	    @BeforeEach
	    public void setup() {
	        calculator = new StringCalculator();
	    }

	    @Test
	    public void testEmptyString() throws Exception {
	        int result = calculator.add("");
	        Assertions.assertEquals(0, result);
	    }

	    @Test
	    public void testSingleNumber() throws Exception {
	        int result = calculator.add("1");
	        Assertions.assertEquals(1, result);
	    }

	    @Test
	    public void testMultipleNumbers() throws Exception {
	        int result = calculator.add("1,2,3,4,5");
	        Assertions.assertEquals(15, result);
	    }
	    
	    @Test
	    public void testMaximumCountOfNumbersExceeded() throws Exception {
	        int result = calculator.add("1,2,3,4,5,6,7,8,9,10,11,12,13");
	        Assertions.assertEquals(0, result);
	    }
	    
	    @Test
	    public void testNewLineAndComma() throws Exception {
	        int result = calculator.add("1\n2,3");
	        Assertions.assertEquals(6, result);
	    }
	    
	    @Test
	    public void testNewLineOnly() throws Exception {
	        int result = calculator.add("1\n2\n3");
	        Assertions.assertEquals(6, result);
	    }
	    
	    @Test
	    public void testInvalidPatternWithAdjacentDelimiters() throws Exception {
	        int result = calculator.add("1,2,\n\n,3");
	        Assertions.assertEquals(0, result);
	    }
	    
	    @Test
	    public void testCustomDelimter() throws Exception {
	        int result = calculator.add("//;\n1;2");
	        Assertions.assertEquals(3, result);
	    }
	    
	    @Test
	    public void testNegativeNumbers() {
	        Assertions.assertThrows(Exception.class, () -> {
	            calculator.add("-1,2,-3");
	        });
	    }
	    
	    @Test
	    public void testIgnoreNumbersGreaterThan1000() throws Exception {
	        int result = calculator.add("2,1001,2000,3");
	        Assertions.assertEquals(5, result);
	    }

}
