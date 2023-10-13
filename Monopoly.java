import java.io.BufferedOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * Problem:
 * https://open.kattis.com/problems/monopol
 */
public class Monopoly {
	
	public static void main(String[] args) throws IOException {
		//Gather input
		int numHotels = getInt();
		if(numHotels < 1 || numHotels > 11) {
			System.out.println("Invalid number of hotels.");
			return;
		}
		ArrayList<Integer> distances = new ArrayList<>();
		for (int i = 0; i < numHotels; i++) {
			Integer temp = getInt();
			if(temp < 2 || temp > 12) {
				System.out.println("Invalid distance number.");
				return;
			}
			distances.add(temp);
		}
		
		//Calculate probabilities
		double prob = 0; //hold final probability		
		
		for(Integer dist : distances) {
			prob += getRollProb(dist);
		}
		sb.append(prob);
		
		in.close();
		System.out.println(sb.toString());
		out.flush();
	}
	
	//Helper method for main
	//Calculates probability of rolling given number with two, six-sided die
	//Have 6^2 number of possibilities for rolling 2, six-sided die
	private static double getRollProb(Integer num) {
		final double TOTAL_POSS = 36;
		int numPossibilities = 0; //hold number of possible sums to get num
		
		//go through numbers to be rolled on first die
		for(int i = 1; i < 7; i++) {
			if(i >= num) break; //exit outer loop if first die is >= num (higher roll than one for our prob)
			for(int j = 1; j < 7; j++) {
				if(i+j > num) break; //exit inner loop if sum of die is higher than num
				else if(i+j == num) numPossibilities++;
				else continue;
			}
		}
		
		return numPossibilities/TOTAL_POSS;
	}

	//Generic I/O handling for Kattis problems adapted from Kattio.java at https://open.kattis.com/help/java 
	static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
	static StringBuilder sb = new StringBuilder();
	static String inLine;
	static StringTokenizer inST;
	static String inToken;
	
	static boolean hasMoreTokens() {
		return peekToken() != null;
	}

	static int getInt() {
		return Integer.parseInt(nextToken());
	}

	static double getDouble() {
		return Double.parseDouble(nextToken());
	}

	static long getLong() {
		return Long.parseLong(nextToken());
	}

	static String getWord() {
		return nextToken();
	}

	static String peekToken() {
		if (inToken == null)
			try {
				while (inST == null || !inST.hasMoreTokens()) {
					inLine = in.readLine();
					if (inLine == null) return null;
					inST = new StringTokenizer(inLine);
				}
				inToken = inST.nextToken();
			} catch (IOException e) { }
		return inToken;
	}

	static String nextToken() {
		String ans = peekToken();
		inToken = null;
		return ans;
	}
}
