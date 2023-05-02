package com.stuff.codingtest;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sunish Thankachan
 * 
 *         String calculator class that takes a string of numbers as input and
 *         returns their sum based on some predefined conditions to
 *         allow/restrict like delimiter type, negative values, greater than a
 *         particular amount etc.
 */
public class StringCalculator {

	private static final String DEFAULT_DELIMTER = ",";
	private static final String NEW_LINE_DELIMITER = "\n";
	private static final String CUSTOM_DELIMITER_STARTER = "//";
	private static final int MAXIMUM_NUMBER_COUNT = 10;
	private static final int MAXIMUM_VALUE_ALLOWED = 1000;

	public int add(String numbers) throws Exception {
		int sum = 0;

		// Checking if the input number string is empty
		if (numbers.isEmpty()) {
			return sum;
		}

		// Checking if the input number string contains invalid pattern like two
		// adjacent delimiters
		if (invalidPatternCheck(numbers)) {
			System.out.println("Two adjacent delimiters found");
			return 0;
		}

		// Code snippet to handle custom delimiters
		String delimiter = DEFAULT_DELIMTER;

		// Checks if custom delimiter is there
		if (numbers.startsWith(CUSTOM_DELIMITER_STARTER)) {
			String[] parts = numbers.split(NEW_LINE_DELIMITER, 2);
			if (parts[0].startsWith(CUSTOM_DELIMITER_STARTER)) {
				delimiter = parts[0].substring(2);
				numbers = parts[1];
			} else {
				numbers = parts[0];
			}
		}

		String[] numberArray = numbers.split("[" + delimiter + NEW_LINE_DELIMITER + "]+");
		if (numberArray != null && numberArray.length <= MAXIMUM_NUMBER_COUNT) {

			List<Integer> negatives = new ArrayList<>();
			for (String number : numberArray) {
				int num = Integer.parseInt(number);

				// To handle negatives
				if (num < 0) {
					negatives.add(num);
				} else if (num <= MAXIMUM_VALUE_ALLOWED) { // Ignore if GT 1000
					sum += num;
				}
			}

			// If the input string contains negative values, then throw an exception
			if (!negatives.isEmpty()) {
				throw new Exception("Negative numbers not allowed: " + negatives.toString());
			}
		} else {
			System.out.println("Amount of numbers exceeded the maximum limit!!!");
		}

		return sum;
	}

	/**
	 * Method to check there is any invalid pattern in the number string. Checks
	 * whether adjacent patterns are present
	 * 
	 * @param numbers
	 * @return boolean
	 */
	public boolean invalidPatternCheck(String numbers) {
		boolean invalidPatternPresent = false;

		for (char value : numbers.toCharArray()) {
			if (String.valueOf(value).contentEquals(NEW_LINE_DELIMITER)
					|| String.valueOf(value).contentEquals(DEFAULT_DELIMTER)) {

				if (invalidPatternPresent) {
					return true;
				}
				invalidPatternPresent = true;
			} else {
				invalidPatternPresent = false;
			}
		}

		return invalidPatternPresent;
	}

	public static void main(String[] args) {
		StringCalculator calculator = new StringCalculator();

		try {
			System.out.println(calculator.add("")); // 0
			System.out.println(calculator.add("1")); // 1
			System.out.println(calculator.add("1,2")); // 3
			System.out.println(calculator.add("1\n2,3")); // 6
			System.out.println(calculator.add("1,2,3,4,5,6,7,8,9,10,11")); // Exceeded count 0
			System.out.println(calculator.add("1,2,\n,3")); // Invalid - Adjacent patterns 0
			System.out.println(calculator.add("//;\n1;2")); // 3
			System.out.println(calculator.add("1,1001")); // 1
			System.out.println(calculator.add("//[***]\n1***2***3")); // 6
			System.out.println(calculator.add("//[*][%]\n1*2%3")); // 6
			System.out.println(calculator.add("//[***][%%%%]\n1***2%%%%3")); // 6

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
