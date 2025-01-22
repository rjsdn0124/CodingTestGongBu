import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(sb);
	}

	private static void solution(){
		dfs(1, 0, new int[M]);
	}

	private static void dfs(int ind, int depth, int[] visited){
		if(depth == M){
			for(int i = 0; i < M; i++){
				sb.append(visited[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = ind; i <= N - M + depth + 1; i++){
			visited[depth] = i;
			dfs(i + 1, depth + 1, visited);
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] l = br.readLine().split(" ");
		N = Integer.parseInt(l[0]);
		M = Integer.parseInt(l[1]);
	}
}