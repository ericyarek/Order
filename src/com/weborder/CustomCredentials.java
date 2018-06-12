package com.weborder;

import java.util.Random;

public class CustomCredentials {
	String userName = "Tester";
	String password = "test";
	String streetName = "123 Any st";
	String cityName = "Anytown";
	String stateName ="Virginia";
	
	//randomStringFullName
	String anthing ="sdfodsgonOKANFsokNAFlkmlslDMG";
	String firstName ="John";
	String lasName = "Smith";
	static String  fullName="";
	String midOne="";
	
	public String randomStringFullName() {
		for(int i=0; i<10; i++) {
			Random randomNumber = new Random();
			midOne =midOne+ anthing.charAt(randomNumber.nextInt(anthing.length()-1));
		}
		return firstName+" " +midOne +" "+lasName;
	}
	
	//My number generator
	public static String generateNumber(int howManyNumbers) {
		String returnNumber = "";
		Random randomNumber = new Random();
		for (int i = 0; i < howManyNumbers; i++) {
			returnNumber = returnNumber + randomNumber.nextInt(10);
		}
		return returnNumber;
	}
	
}
