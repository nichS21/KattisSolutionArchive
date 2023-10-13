import java.util.Scanner;

/*
 * Problem:
 * https://open.kattis.com/problems/conundrum
 */
public class CryptographersConundrum {
 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String cipher = in.next();
		int numDays = 0;
		
		int numPer = cipher.length() / 3; //get number of substrings of 3 in the cipher (input is always multiple of 3)
		int incr = 0; //increment variable
		for(int i = 0; i < numPer; i++) {
			// P case
			String letter = cipher.substring(incr,incr+1); //get letter in substring of i
			if(!letter.equals("P")) numDays++; //if does not equal character we want, another letter must erase (one more day)
			incr++; //move increment up one
			
			// E case
			letter = cipher.substring(incr,incr+1);
			if(!letter.equals("E")) numDays++;
			incr++;
			
			//R case
			letter = cipher.substring(incr,incr+1);
			if(!letter.equals("R")) numDays++;
			incr++;
		}
		
		System.out.println(numDays);
		in.close();	
	}
}
