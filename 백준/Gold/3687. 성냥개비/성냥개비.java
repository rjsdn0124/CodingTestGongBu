import java.io.*;
import java.util.*;

public class Main{
	private static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = getInput(br);
		StringBuilder sb = new StringBuilder();
		solution(arr, sb);
		System.out.println(sb);
	}

	private static void solution(int[] arr, StringBuilder sb){
		long[] dp = dp();
		for(int i: arr){
			sb.append(findMin(i, dp));
			sb.append(" ");
			findMax(i, sb);
			sb.append("\n");
		}
	}

	private static long[] dp() {
		long[] dp = new long[101];
		dp[1] = 9;
		dp[2] = 1;
		dp[3] = 7;
		dp[4] = 4;
		dp[5] = 2;
		dp[6] = 6;
		dp[7] = 8;
		int[] map = {1, 7, 4, 2, 0, 8};

		for(int i = 8; i < dp.length; i++){
			dp[i] = Long.MAX_VALUE;
			for(int j = 0; j < 6; j++){
				dp[i] = Math.min(dp[i], dp[i - (j + 2)] * 10 + map[j]);
			}
		}

		return dp;
	}

	private static long findMin(int torchCount, long[] dp){
		return dp[torchCount];
	}


	private static void findMax(int torchCount, StringBuilder sb){
		if(torchCount % 2 == 1){
			sb.append(7);
			torchCount -= 3;
		}
		torchCount /= 2;
		while(torchCount-- > 0){
			sb.append(1);
		}
	}

	private static int[] getInput(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}
		return arr;
	}
}