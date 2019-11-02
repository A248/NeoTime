package space.arim.time;

import java.util.Scanner;

public class Main {
	static Scanner scanner;
	
	public static void main(String[] args) {
		System.out.println("hello world");
		scanner = new Scanner(System.in);
		String mystring;
		mystring= "iam Ashton";
		System.out.println(mystring);
		String u = scanner.next(); 
		int w = u.length();
		w += 5;
		w = w + 6;
		System.out.println(w);
		scanner.close();
		
	}

}
