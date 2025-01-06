import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;
	private static int n1;
	private static int n2;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Edge>[] edges = getInput(br);
		int result = solution(edges);
		System.out.println(result);
	}

	public static int solution(List<Edge>[] edges){
		// 1에서 n1, n2 거쳐서 N까지의 최단 거리 다 구하기.
		int destToDest = dyke(edges, n1, n2);
		if(destToDest < 0){
			return -1;
		}

		boolean canVia = false;
		int result = Integer.MAX_VALUE;


		int via = via(edges, n1, n2, destToDest);
		if(via > 0){
			canVia = true;
			result = via;
		}

		via = via(edges, n2, n1, destToDest);
		if(via > 0){
			canVia = true;
			result = Math.min(result, via);
		}

		return canVia ? result : -1;
	}

	private static int via(List<Edge>[] edges, int firstDest, int secondDest, int dToD){

		int toFirst = firstDest == 1 ? 0 : dyke(edges, 1, firstDest);
		int toFinal = secondDest == N ? 0 :dyke(edges, secondDest, N);

		if(toFirst < 0 || toFinal < 0){
			return -1;
		} else{
			return toFirst + dToD + toFinal;
		}
	}

	private static int dyke(List<Edge>[] edges, int start, int end){
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited[start] = true;
		pq.addAll(edges[start]);

		while(!pq.isEmpty()){
			Edge edge = pq.poll();
			if(edge.target == end){
				return edge.weight;
			}

			if(!visited[edge.target]){
				visited[edge.target] = true;
				for(Edge e: edges[edge.target]){
					if(!visited[e.target]) {
						pq.add(new Edge(e.target, e.weight + edge.weight));
					}
				}
			}
		}
		return -1;
	}

	private static List<Edge>[] getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		List<Edge>[] edges = new LinkedList[N + 1];

		for(int i = 1; i <= N; i++){
			edges[i] = new LinkedList<>();
		}

		for(int i = 0; i < M; i++){
			line = br.readLine().split(" ");
			int n1 = Integer.parseInt(line[0]);
			int n2 = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			edges[n1].add(new Edge(n2, w));
			edges[n2].add(new Edge(n1, w));
		}
		line = br.readLine().split(" ");
		n1 = Integer.parseInt(line[0]);
		n2 = Integer.parseInt(line[1]);
		return edges;
	}

}

class Edge implements Comparable<Edge> {
	int target;
	int weight;

	public Edge(int target, int weight){
		this.target = target;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}
}