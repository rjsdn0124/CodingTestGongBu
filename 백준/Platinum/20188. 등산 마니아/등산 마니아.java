import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static long result = 0;
	private static List<Integer>[] arr;
	private static int[] subTree;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.print(result / 2);
	}

	private static void solution(){
		dfs(0);

		for (int i = 1; i < N; i++) {
			result += (2L * N - subTree[i] - 1) * subTree[i];
		}
	}

	private static void dfs(int ind){
		visited[ind] = true;
		for(int n : arr[ind]) {
			if(!visited[n]) {
				dfs(n);
				subTree[ind] += subTree[n];
			}
		}
	}

	private static void getInput(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new ArrayList[N];
		visited = new boolean[N];
		subTree = new int[N];
		Arrays.fill(subTree, 1);
		for(int i = 0; i < N; i++){
			arr[i] = new ArrayList<>();
		}

		for(int i = 0; i < N - 1; i++) {
			String[] l = br.readLine().split(" ");
			int n1 = Integer.parseInt(l[0]) - 1;
			int n2 = Integer.parseInt(l[1]) - 1;
			arr[n1].add(n2);
			arr[n2].add(n1);
		}
	}
}