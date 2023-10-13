import java.util.Scanner;

/*
 * Problem:
 * https://open.kattis.com/problems/3dprinter
 */
public class PrintedStatues {
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		if(keyboard.hasNextInt()) {
			Integer numStatues = keyboard.nextInt();
			
			if(numStatues <= 0 || numStatues > 10000) {
				keyboard.close();
				System.out.println("Invalid integer");
				return;
			}
			
			//Calculate number of days to print all statues
			else {
				Integer numPrinters = 1;
				Integer numComplete = 0;
				Integer numDays = 0;
				
				//loop through the days
				while(numComplete < numStatues) {
					//double printers until hit same num as half of needed statues
					if(numPrinters != numStatues/2) {
						//if doubling is too much, add some printers and print some statues in same day
						if(numPrinters*2 > numStatues/2) {
							Integer tempPrinters = (numStatues/2) - numPrinters; //difference need to reach half
							numPrinters += tempPrinters;
							numComplete += numPrinters - tempPrinters;
						}
						else numPrinters *= 2;
					}
					else numComplete += numPrinters;
					
					numDays++;
				}
				
				System.out.println(numDays);
				keyboard.close();
				return;
			}
		}
		
		System.out.println("Invalid input");
		keyboard.close();
		return;
	}
}
