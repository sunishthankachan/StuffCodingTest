package com.stuff.codingtest;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	private static final String DEFAULT_DELIMTER = ",";
	private static final int MAXIMUM_NUMBER_COUNT = 10;

	public int add(String numbers) throws Exception {
		int sum = 0;
		if (numbers.isEmpty()) {
			return sum;
		}

		if (invalidPatternCheck(numbers)) {
			System.out.println("Two adjacent delimiters found");
			return 0;
		}

		String delimiter = DEFAULT_DELIMTER;
		if (numbers.startsWith("//")) {
			String[] parts = numbers.split("\n", 2);
			if (parts[0].startsWith("//")) {
				delimiter = parts[0].substring(2);
				numbers = parts[1];
			} else {
				numbers = parts[0];
			}
		}

		String[] numberArray = numbers.split("[" + delimiter + "\n]+");
		if (numberArray != null && numberArray.length < MAXIMUM_NUMBER_COUNT) {

//			for (String number : numberArray) {
//				int num = Integer.parseInt(number);
//				sum += num;
//			}
			List<Integer> negatives = new ArrayList<>();
			for (String number : numberArray) {
				int num = Integer.parseInt(number);
				if (num < 0) {
					negatives.add(num);
				} else if (num <= 1000) {
					sum += num;
				}
			}

			if (!negatives.isEmpty()) {
				throw new Exception("Negative numbers not allowed: " + negatives.toString());
			}
		} else {
			System.out.println("Amount of numbers exceeded the maximum limit!!!");
		}

		return sum;
	}

	public boolean invalidPatternCheck(String numbers) {
		boolean flag = false;

		for (char value : numbers.toCharArray()) {
			if (String.valueOf(value).contentEquals("\n") || String.valueOf(value).contentEquals(DEFAULT_DELIMTER)) {

				if (flag) {
					return true;
				}
				flag = true;
			} else {
				flag = false;
			}
		}

		return flag;
	}

	public static void main(String[] args) {
		StringCalculator calculator = new StringCalculator();

		try {
			System.out.println(calculator.add("")); // 0
			System.out.println(calculator.add("1")); // 1
			System.out.println(calculator.add("1,2")); // 3
			System.out.println(calculator.add("1\n2,3")); // 6
			System.out.println(calculator.add("1,2,3,4,5,6,7,8,9,10,11")); // Invalid input
			System.out.println(calculator.add("1,2,\n,3")); // Invalid pattern
			System.out.println(calculator.add("//;\n1;2")); // 3
			System.out.println(calculator.add("1,1001")); // 1

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
