import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		String line;
		while(true) {
			line = user.nextLine();
			System.out.println(line.length());
			if(line.equals("stop")) {
				break;
			}
		}
		
		
	}
}
