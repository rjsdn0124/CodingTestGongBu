import java.io.*;
import java.util.*;

public class Main{
	private static int N, maxBit = 1024;
	private static long result = 0L;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(result % 1000000000);
	}

	private static void solution(){
		// [depth][0~9][bit]
		for(int i = 0; i < 10; i++){
			dp[0][i][1 << i] = 1;
		}

		for(int i = 1; i < N; i++){
			updatedp(i);
		}

		for(int i = 1; i < 10; i++){
			result += dp[N - 1][i][maxBit - 1];
		}
	}

	private static void updatedp(int depth){
		addNextStep(depth, 0, 1);
		for(int i = 1; i < 9; i++){
			addNextStep(depth, i, i + 1);
			addNextStep(depth, i, i - 1);
		}
		addNextStep(depth, 9, 8);
	}

	private static void addNextStep(int depth, int now, int next){
		for(int i = 1; i < maxBit; i++){
			dp[depth][now][i | 1 << now] += dp[depth - 1][next][i];
			dp[depth][now][i | 1 << now] %= 1000000000;
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		dp = new int[N][10][maxBit];
	}
}