import java.io.*;
import java.util.*;

public class Main{
	private static int N, hitInfo = 4, result = 0;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(result);
	}

	private static void solution(){
		// [depth][0~9][bit]
		dp[0][0][1] = 1;
		dp[0][9][2] = 1;
		for(int i = 1; i < 9; i++){
			dp[0][i][0] = 1;
		}

		for(int i = 1; i < N; i++){
			updatedp(i);
		}

		for(int i = 1; i < 10; i++){
			result += dp[N - 1][i][hitInfo - 1];
			result %= 1000000000;
		}
	}

	private static void updatedp(int depth){
		addNextStep(depth, 0, 1, 1);
		for(int i = 1; i < 9; i++){
			addNextStep(depth, i, i + 1, 0);
			addNextStep(depth, i, i - 1, 0);
		}
		addNextStep(depth, 9, 8, 2);
	}

	private static void addNextStep(int depth, int now, int next, int hitPlace){
		for(int i = 0; i < hitInfo; i++){
			dp[depth][now][i | hitPlace] += dp[depth - 1][next][i];
			dp[depth][now][i | hitPlace] %= 1000000000;
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		dp = new int[N][10][hitInfo];
	}
}