import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * Problem:
 *https://open.kattis.com/problems/4thought?editsubmit=10211666
 */
public class fourThought {
	//Generate all equations as hashTable since numbers are constant and have three choices of four operations;
	//thus have 4^3 (64) equations. Then can use user input as key to find equation that equals input.

	//Generates all 64 possible equations, eliminating redundancy. To be called in main method.
	public static Map<Integer, String> generateAll() {
		HashMap<Integer, String> table = new HashMap<Integer, String>(64); //create Hashtable with 64 (key,value) pairs
		ArrayList<String> ops = new ArrayList<String>(); //create list with operations to cycle through for creating equations
		ops.add("+");
		ops.add("-");
		ops.add("*");
		ops.add("/");

		String equation; //holds equation to be built


		//Generate all 64 equations possible for storage into HashMap
		for(int i = 0; i < ops.size(); i++) {
			equation = "4 " + ops.get(i) + " 4 ";

			for(int j = 0; j < ops.size(); j++) {
				equation += ops.get(j) + " 4 ";

				for(int k = 0; k < ops.size(); k++) {
					equation += ops.get(k) + " 4";
					Integer key = shuntingYard(equation); //compute equation using Shunting Yard algorithm

					table.put(key, equation);
					equation = equation.substring(0,10); //truncate equation to ready for next
				}

				equation = equation.substring(0,6); //truncate equation to ready for next
			}
			equation = ""; //clear temp var for next equation
		}

		return table;
	}

	//Helper method to compute string input to numerical output (using Shunting Yard algorithm)
	//takes string as input (an equation)
	//outputs value of original string equation
	private static Integer shuntingYard(String input) {
		Integer result = 0; //to hold final result of equation
		//Array to hold individual characters of input string
		input = input.replaceAll("\\s", "");
		char[] chars = input.toCharArray();

		//Data structures to hold pieces of input
		Stack<String> stack = new Stack<String>(); //to temporarily hold operators
		Queue<String> queue = new LinkedList<String>(); //to hold string that can be converted to arithmetic through computer language

		//iterate through char array
		for(int i = 0; i < chars.length; i++) {
			//check if next character is an operator
			//if so, put into stack 
			if(chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {
				//check if PEMDAS violated or if need to pop accordingly
				//if so, pop off head of stack and enqueue into queue, then put new operator in stack
				//if not, simply add operator to stack
				if(stack.isEmpty()) stack.push(Character.toString(chars[i])); //if stack empty, simply add it
				else {
					switch(chars[i]) {
					case '+':
						queue.add(stack.pop());
						if(stack.isEmpty()) stack.push(Character.toString(chars[i]));
						else {
							while(stack.peek().equals("+") || stack.peek().equals("-")) { //case in which pop off higher precedence, but still have same precedence next in stack before pushing (bad)
								queue.add(stack.pop());
								if(stack.isEmpty()) break;
							}
							stack.push(Character.toString(chars[i]));
						}
						break;


					case '-':
						queue.add(stack.pop());
						if(stack.isEmpty()) stack.push(Character.toString(chars[i]));
						else {
							while(stack.peek().equals("+") || stack.peek().equals("-")) { //case in which pop off higher precedence, but still have same precedence next in stack before pushing (bad)
								queue.add(stack.pop());
								if(stack.isEmpty()) break;
							}
							stack.push(Character.toString(chars[i]));
						}
						break;

					default: // * & / cases, since are same 

						switch(stack.peek()) {
						case "+": //lower precedence in PEMDAS, add to stack
							stack.push(Character.toString(chars[i]));
							break;
						case "-": //same as + case
							stack.push(Character.toString(chars[i]));
							break;
						default: //* and / have same precedence, pop off stack first then add new
							queue.add(stack.pop());
							if(stack.isEmpty()) {
								stack.push(Character.toString(chars[i]));
							}
							else {
								while(stack.peek().equals("*") || stack.peek().equals("/")) { //case in which pop off same precedence, but is still more of same precedence in stack
									queue.add(stack.pop());
									if(stack.isEmpty()) break;
								}
								stack.push(Character.toString(chars[i]));
							}
							break;
						}
					}
				}
			}
			//else character must be a number; simply add to queue
			else queue.add(Character.toString(chars[i]));
		}

		//Empty stack and enqueue remaining operators
		while(!stack.isEmpty()) {
			queue.add(stack.pop());
		}

		
		//Now take queue and compute the operation.... 
		while(!queue.isEmpty()) {
			//check if element from queue is an operator
			//if so, perform operation with two most recent numbers in stack
			if(queue.peek().equals("+") || queue.peek().equals("-") || queue.peek().equals("*") || queue.peek().equals("/")) {
				int second = Integer.valueOf(stack.pop());
				int first = Integer.valueOf(stack.pop());
				Integer equals = 0;
				String eq;
				String operator = queue.remove();

				switch(operator) {
				case "+":
					equals = first + second;
					eq = equals.toString();
					stack.push(eq);
					break;
				case "-":
					equals = first - second;
					eq = equals.toString();
					stack.push(eq);					
					break;
				case "*":
					equals = first * second;
					eq = equals.toString();
					stack.push(eq);						
					break;
				case "/":
					equals = first / second;
					eq = equals.toString();
					stack.push(eq);
					break;
				}
			}
			//otherwise, element must be a number, so add to stack
			else {
				stack.push(queue.remove());
			}

		}
		result = Integer.valueOf(stack.pop());
		return result;
	}


	public static void main(String[] args) throws IOException {
		try {
			Map<Integer, String> table = generateAll();

			//Give amount of numbers to check
			int tempNumCases = getInt();
			int numCases = (tempNumCases >= 1 && tempNumCases <= 1000) ? tempNumCases: 0; 
			int[] caseArr = new int[numCases];

			//Give 'numCases' amount of numbers
			if(numCases != 0) {
				for(int i = 0; i < numCases; i++) {
					int temp = getInt();
					if(temp >= -1000000 && temp <= 1000000)	caseArr[i] = temp;
					else {
						System.out.println("Invalid int value");
						return;
					}
				}

				//For each number, check if there is a solution or not
				for(int i = 0; i < numCases; i++) {

					if(table.containsKey(caseArr[i])) {

						sb.append(table.get(caseArr[i]) + " = " + caseArr[i]);
						System.out.println(sb.toString()); 
					}
					else {

						sb.append("No solution");
						System.out.println(sb.toString());
					}

					sb.setLength(0);
				}
			}
			else {
				System.out.println("Invalid case number");
				return;
			}
		}

		catch(IllegalArgumentException e) {
			System.out.println("Invalid input");
			return;
		}

		in.close();
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
