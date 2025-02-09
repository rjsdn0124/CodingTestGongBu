import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, K, result = 0;
	private static int[] arr;
	private static int[] dp;
	private static List<int[]> groups;
	private static List<Integer>[] friends;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		solution();
		System.out.println(dp[K - 1]);
	}

	private static void solution(){
		union();
	}

	private static void updateDP(int[] group){
		for(int i = K - 1; i >= group[0]; i--){
			dp[i] = Math.max(dp[i], dp[i - group[0]] + group[1]);
		}
	}

	private static void union(){
		boolean[] visited = new boolean[N];
		groups = new ArrayList<>();
		for(int i = 0; i < N; i++){
			if(!visited[i]){
				int[] group = new int[2];
				dfs(i, group, visited);
				updateDP(group);
			}
		}
	}

	private static void dfs(int ind, int[] group, boolean[] visited){
		visited[ind] = true;
		group[0]++;
		group[1] += arr[ind];

		for(int f: friends[ind]){
			if(!visited[f]) {
				dfs(f, group, visited);
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		arr = new int[N];
		friends = new ArrayList[N];
		dp = new int[K];

		line = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(line[i]);
			friends[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++){
			String[] l = br.readLine().split(" ");
			int n1 = Integer.parseInt(l[0]) - 1;
			int n2 = Integer.parseInt(l[1]) - 1;

			friends[n1].add(n2);
			friends[n2].add(n1);
		}
	}
}