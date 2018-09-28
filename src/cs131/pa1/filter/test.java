package cs131.pa1.filter;

import java.util.Arrays;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		
		String line = "cat hello.txt | grep keyword | > output.txt";
		System.out.println(line);
		System.out.println(Arrays.toString(line.split("\\s\\|\\s", -1)));
		for(String str : line.split("\\s\\|\\s", -1)) {
			System.out.println(Arrays.toString(str.split("\\s",-1)));
			
		}
		System.out.println(Arrays.toString(line.split("\\s\\|\\s", -1)));

	}

}
