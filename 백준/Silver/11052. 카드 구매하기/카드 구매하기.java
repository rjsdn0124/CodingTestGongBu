import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static final String SPACE = " ";
	private static int[] arr;
	private static int[] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(dp[N]);
	}

	private static void solution(){
		for(int i = 0; i <= N; i++){
			for(int j = 1; j <= N - i; j++){
				dp[i + j] = Math.max(dp[i + j], dp[i] + arr[j]);
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new int[N+1];
		dp = new int[N+1];

		String[] l = br.readLine().split(SPACE);
		for(int i = 0; i < N; i++){
			arr[i + 1] = Integer.parseInt(l[i]);
		}
	}
}