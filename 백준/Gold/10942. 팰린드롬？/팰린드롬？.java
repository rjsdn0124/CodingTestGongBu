import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;
	private static int[] arr;
	private static int[] s;
	private static int[] e;
	private static boolean[][] dp;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(sb);
	}

	private static void solution(){
		// Dp 배열 만들기. (홀수 짝수 케이스 고려하기)
		findDp();
		// dp 에서 찾기.
		doAnswer();
	}

	private static void findDp(){
		int start;
		int end;
		boolean prevResult;
		for(int i = 0; i < N; i++){
			start = i;
			end = i;
			prevResult = true;
			while(start >= 0 && end < N){
				if(arr[start] == arr[end]){
					dp[start][end] = prevResult;
				}else {
					prevResult = false;
				}
				end++;
				start--;
			}
		}

		for(int i = 0; i < N - 1; i++){
			start = i;
			end = i + 1;
			prevResult = true;
			while(start >= 0 && end < N){
				if(arr[start] == arr[end]){
					dp[start][end] = prevResult;
				}else {
					prevResult = false;
				}
				start--;
				end++;
			}
		}
	}

	private static void doAnswer(){
		for(int i = 0; i < M; i++){
			sb.append(dp[s[i]][e[i]] ? 1 : 0).append("\n");
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new boolean[N][N];
		String[] l = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(l[i]);
		}
		M = Integer.parseInt(br.readLine());
		s = new int[M];
		e = new int[M];
		for(int i = 0; i < M; i++){
			l = br.readLine().split(" ");
			s[i] = Integer.parseInt(l[0]) - 1;
			e[i] = Integer.parseInt(l[1]) - 1;
		}
	}
}