import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;
	private static int[] memories;
	private static int[] costs;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		int result = solution();
		System.out.println(result);
	}

	private static int solution(){
		int result = 100 * N + 1;
		int[][] dp = new int[N][result];
		dp[0][costs[0]] = memories[0];
		if(dp[0][costs[0]] >= M) result = costs[0];
		int tc;
		for(int i = 1; i < N; i++){
			tc = costs[i];
			for(int j = 0; j < tc; j++){
				dp[i][j] = dp[i - 1][j];
			}
			for(int j = tc; j < result; j++){
				dp[i][j] = Math.max(dp[i - 1][j - tc] + memories[i], dp[i - 1][j]);
				if(dp[i][j] >= M) {
					result = j;
					break;
				}
			}
		}
		return result;
	}

	private static void getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		String[] mLine = br.readLine().split(" ");
		String[] cLine = br.readLine().split(" ");

		memories = new int[N];
		costs = new int[N];
		for(int i = 0; i < N; i++){
			memories[i] = Integer.parseInt(mLine[i]);
			costs[i] = Integer.parseInt(cLine[i]);
		}
	}
}