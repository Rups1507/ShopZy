package com.masai.utility;

import java.time.LocalDate;

public class IDGeneration {
	

	public static int generateId() {

		return (int) (Math.random() * 1000000);
	}
}
