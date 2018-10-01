package cs131.pa1.filter;

import java.util.Arrays;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		
		System.out.println(Arrays.toString("cat hi | grep keyword > output.txt".split("\\s\\|\\s|\\s>\\s",-1)));
		String str = "grep keyword > output.txt";
		System.out.println(str.substring(0, str.indexOf(" > ")));
		System.out.println(str.substring(str.indexOf(">")));
	}

}
