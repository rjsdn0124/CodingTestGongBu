import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int result = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Edge>[] arr = getInput(br);
		solution(arr);
		System.out.println(result);
	}

	private static void solution(List<Edge>[] arr){
		boolean[] visited = new boolean[N + 1];
		// dfs 돌기
		dfs(arr, visited, 1);
	}

	private static int dfs(List<Edge>[] edges, boolean[] visited, int i){
		int fMax = 0;
		int sMax = 0;
		visited[i] = true;
		// 돌면서 리프 노드면 return 0
		for(Edge e: edges[i]){
			if(visited[e.target]) continue;
			int cWeight = dfs(edges, visited, e.target) + e.weight;
			// 내 자식들의 최대 2개를 찾아서 합 구하면 내가 중점인 지름
			if(fMax < cWeight){
				sMax = fMax;
				fMax = cWeight;
			}else if(sMax < cWeight){
				sMax = cWeight;
			}
		}
		// 최댓값 갱신.
		result = Math.max(result, fMax + sMax);

		// 내 자식들 중 가장 큰 친구 하나를 구해서 부모한테 전달.
		return fMax;
	}

	private static List<Edge>[] getInput(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		List<Edge>[] arr = new LinkedList[N + 1];

		String[] line;

		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			int p = Integer.parseInt(line[0]);
			arr[p] = new LinkedList<>();
			for(int j = 1; j < line.length - 1; j += 2){
				arr[p].add(new Edge(Integer.parseInt(line[j]), Integer.parseInt(line[j + 1])));
			}
		}
		return arr;
	}
}

class Edge{
	int target;
	int weight;

	public Edge(int target, int weight){
		this.target = target;
		this.weight = weight;
	}
}