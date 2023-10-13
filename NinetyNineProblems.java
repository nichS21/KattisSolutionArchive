import java.util.Scanner;

/*
 * Problem:
 * https://open.kattis.com/problems/99problems
 */
public class NinetyNineProblems {
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		while (keyboard.hasNextInt()) {
			Integer N = keyboard.nextInt();
			keyboard.close();
			String temp = String.valueOf(N);
			String tempSub = (N > 9) ? temp.substring(temp.length()-2): temp; //last two characters to check for "99"
						
			//if last two numbers are not 99...
			if (!tempSub.contains("99")) {
				Integer lastTwo = Integer.parseInt(tempSub);
				int increase = 99 - lastTwo;
				int decrease = 100 - increase;
				
				if(increase == 50) { 
					//print the larger solution (tie case)
					System.out.println(N += increase);
					return;
				}
				else if (increase > 50 && N > 48 ) {
					//print the smaller solution
					System.out.println(N -= decrease);
					return;
				}
				else {
					//print the larger solution
					System.out.println(N += increase);
					return;
				}
			}
			
			else return;			
		}
		keyboard.close();
		System.out.println("Invalid input");
		return;
	}
}
