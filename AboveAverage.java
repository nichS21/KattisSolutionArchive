import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/*
 * Problem:
 * https://open.kattis.com/problems/aboveaverage
 */
public class AboveAverage { 
	
	public static void main(String[] args) throws IOException {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(3);
		
		try {
			int tempNumCases = getInt();
			int numCases = (tempNumCases >= 1 && tempNumCases <= 50) ? tempNumCases: 0; 
			
			if(numCases != 0) {
				for(int i = 0; i < numCases; i++) {
					int numStudents = getInt();
					
					if(numStudents <= 0 || numStudents > 1000) {
						System.out.println("Invalid student number.");
						return;
					}
					
					double grades = 0; 
					double[] gradeArr = new double[numStudents]; 
					
					//add grades
					for(int j = 0; j < numStudents; j++) {
						double currGrade = getDouble();
						
						if(currGrade >= 0 && currGrade <= 100) {
							gradeArr[j] = currGrade;
							grades += currGrade;	
						}
						else {
							System.out.println("Invalid final grade.");
							return;//break;
						}
					}
					
					double average = grades/numStudents; 
					double numAbove = 0;
					for(int x = 0; x < numStudents; x++) {
						if(gradeArr[x] > average) numAbove++;
					}
					
					double aboveAvg = (numAbove/numStudents)*100;
					
					sb.append(df.format(aboveAvg) + "%");
					
					System.out.println(sb.toString());
					
					sb.setLength(0);//clear string builder for next line	
				}		
			} 
			else {
				System.out.println("Invalid case number.");
				return;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input.");
			return;
		}
	
		in.close();
		out.flush();
		return;		
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