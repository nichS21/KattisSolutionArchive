import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 * Problem:
 * https://open.kattis.com/problems/aaah
 */
public class Aaah { 
	
	public static void main(String[] args) throws IOException {
		
		try {
			//Collect ah's for Jon and doctor that day
			String jonVal = getWord();
			String docVal = getWord();
			
			
			//if Jon's ah equal or larger than doctor's 
			if(jonVal.contains(docVal)) {
				sb.append("go");
			}
			
			//if Jon's ah lesser than doctor's or invalid string input
			else {
				sb.append("no");
			}
		}
		
		//catch input that is not a string
		catch(IllegalArgumentException e){
			System.out.println("Invalid input.");
			return;
		}
		
		in.close();
		System.out.println(sb.toString()); 
		out.flush();
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
