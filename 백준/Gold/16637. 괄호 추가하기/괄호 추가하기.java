import java.io.*;

public class Main{
	private static int n;
	private static int result = Integer.MIN_VALUE;
	private static final char ZERO = '0';

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = getInput(br);
		dfs(chars, chars[0] - ZERO,  2);
		System.out.println(result);
	}

	private static void dfs(char[] chars, int sum, int startPoint){
		// 괄호 시작, 끝 계산, 끝부터 다시 괄호 묶기. 이전 값을 전달해서 dfs에서 이전 값과 현재 괄호 계산.
		if(startPoint > n){
			result = Math.max(result, sum);
			return;
		}
		if(startPoint < n){
			int gualho = chars[startPoint] - ZERO;
			dfs(chars, calculate(sum, gualho, chars[startPoint - 1]), startPoint + 2);

			if(startPoint + 2 < n){
				gualho = calculate(gualho, chars[startPoint + 2] - ZERO, chars[startPoint + 1]);
				dfs(chars, calculate(sum, gualho, chars[startPoint - 1]), startPoint + 4);
			}
		}
	}

	private static int calculate(int a, int b, char op){
		if(op == '+'){
			return a + b;
		} else if(op == '*'){
			return a * b;
		} else{
			return a - b;
		}
	}

	private static char[] getInput(BufferedReader br) throws IOException{
		// 입력.
		n = Integer.parseInt(br.readLine());

		return br.readLine().toCharArray();
	}
}