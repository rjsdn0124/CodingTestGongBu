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
		dfs(1, 0, new boolean[N + 1]);
	}

	private static void dfs(int ind, int depth, boolean[] visited){
		if(depth == M){
			for(int i = 1; i <= N; i++){
				if(visited[i]) {
					sb.append(i).append(" ");
				}
			}
			sb.append("\n");
			return;
		}

		for(int i = ind; i <= N - M + depth + 1; i++){
			visited[i] = true;
			dfs(i + 1, depth + 1, visited);
			visited[i] = false;
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] l = br.readLine().split(" ");
		N = Integer.parseInt(l[0]);
		M = Integer.parseInt(l[1]);
	}
}