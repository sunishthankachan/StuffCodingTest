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

		String[] numberArray = numbers.split(DEFAULT_DELIMTER);
		if (numberArray != null && numberArray.length < MAXIMUM_NUMBER_COUNT) {

			for (String number : numberArray) {
				int num = Integer.parseInt(number);
				sum += num;
			}
		}else {
			System.out.println("Amount of numbers exceeded the maximum limit!!!");
		}

		return sum;
	}

	public static void main(String[] args) {
		StringCalculator calculator = new StringCalculator();

		try {
			System.out.println(calculator.add("")); // 0
			System.out.println(calculator.add("1")); // 1
			System.out.println(calculator.add("1,2")); // 3

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
