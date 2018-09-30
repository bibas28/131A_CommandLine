package cs131.pa1.filter;

import java.util.Arrays;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		
		String line = "cd beraa/hi/nine";
		System.out.println(line);
		System.out.println(Arrays.toString(line.split("\\s\\|\\s", -1)));
		for(String str : line.split("\\s\\|\\s", -1)) {
			String[] array = str.split("\\s",-1);
			System.out.println(array[1]);
			System.out.println(Arrays.toString(str.split("\\s",-1)));
			
		}
		System.out.println(Arrays.toString(line.split("\\s\\|\\s", -1)));
		System.out.println();
		
	}

}
