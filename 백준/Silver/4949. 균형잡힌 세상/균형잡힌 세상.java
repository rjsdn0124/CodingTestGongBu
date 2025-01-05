import java.io.*;
import java.util.*;

public class Main{
	private static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		solution(br);
	}

	private static void solution(BufferedReader br) throws IOException {
		String line = br.readLine();
		while(!line.equals(".")){
			boolean result = checkBalance(line);

			System.out.println(result ? "yes" : "no");
			line = br.readLine();
		}
	}

	private static boolean checkBalance(String line){
		char[] arr = line.toCharArray();
		Stack<Character> stack = new Stack<>();

		for(char c: arr){
			if(c == '[' || c == '('){
				stack.push(c);
			}else if(c == ']') {
				if(!stack.isEmpty() && stack.peek() == '[') {
					stack.pop();
				}else {
					return false;
				}
			} else if(c == ')') {
				if(!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
				}else {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}
}