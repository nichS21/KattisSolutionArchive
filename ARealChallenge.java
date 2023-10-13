import java.util.Scanner;

/*
 * Problem:
 * https://open.kattis.com/problems/areal
 */
public class ARealChallenge {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		if(keyboard.hasNextDouble()) {
			double area = keyboard.nextDouble();
			
			if(area > 0) {
				
				double sideLength = Math.sqrt(area);
				
				double perimeter = sideLength*4;
				
				System.out.println(perimeter);
				keyboard.close();
				return;
			}
			
			else {
				System.out.println("Error. Negative area");
				keyboard.close();
				return;
			}
		}
		System.out.println("Error. Invalid input");
		keyboard.close();
		return;
	}

}
