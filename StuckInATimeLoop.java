import java.util.Scanner;

/*
 * Problem:
 * https://open.kattis.com/problems/timeloop
 */
public class StuckInATimeLoop {
	public static void main(String args[]) {
		Scanner keyboard = new Scanner(System.in);
		int numChants = keyboard.nextInt();
		
		for(int i = 0; i < numChants; i++) System.out.printf("%d Abracadabra\n", i+1);
		keyboard.close();
	}
}
