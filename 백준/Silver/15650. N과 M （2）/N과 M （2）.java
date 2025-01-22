import java.io.*;

public class Main{
	private static int N, M;
	private static int[] arr;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(sb);
	}

	private static void solution(){
		dfs(1, 0);
	}

	private static void dfs(int ind, int depth){
		if(depth == M){
			for(int i = 0; i < M; i++){
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = ind; i <= N - M + depth + 1; i++){
			arr[depth] = i;
			dfs(i + 1, depth + 1);
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] l = br.readLine().split(" ");
		N = Integer.parseInt(l[0]);
		M = Integer.parseInt(l[1]);
		arr = new int[M];
	}
}