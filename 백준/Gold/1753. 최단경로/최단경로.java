import java.io.*;
import java.util.*;

public class Main {
	static int startPoint;
	static int vSize;
	static int eSize;
	static Map<Integer, List<Edge>> map;
	static int[] result;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();

		getInputAndParse(br);

		result = new int[vSize + 1];
		for(int i = 1; i <= vSize; i++)
			result[i] = Integer.MAX_VALUE;
		isVisited = new boolean[vSize + 1];

		solution();
		printResult();
	}

	public static void solution(){
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(startPoint, 0));
		while(!pq.isEmpty()){
			search(pq);
		}
	}

	private static void search(PriorityQueue<Edge> pq){
		Edge edge = pq.poll();
		int v = edge.getVertex();
		int w = edge.getWeight();

		if(isVisited[v])
			return;

		List<Edge> edges = map.get(v);
		isVisited[v] = true;
		result[v] = w;

		for(Edge e: edges){
			int nextV = e.getVertex();
			int nextW = w + e.getWeight();

			if(result[nextV] > nextW) {
				pq.add(new Edge(nextV, nextW));
			}
		}
	}

	private static void getInputAndParse(BufferedReader br) throws IOException{
		String[] inputStr = br.readLine().split(" ");
		vSize = Integer.parseInt(inputStr[0]);
		eSize = Integer.parseInt(inputStr[1]);

		startPoint = Integer.parseInt(br.readLine());

		for(int i = 0; i < vSize; i++){
			map.put(i + 1, new LinkedList<>());
		}

		for(int i = 0; i < eSize; i++){
			inputStr = br.readLine().split(" ");
			int pointA = Integer.parseInt(inputStr[0]);
			int pointB = Integer.parseInt(inputStr[1]);
			int weight = Integer.parseInt(inputStr[2]);

			map.get(pointA).add(new Edge(pointB, weight));
		}
	}

	private static void printResult(){
		for(int i = 1; i <= vSize; i++){
			if(isVisited[i]){
				System.out.println(result[i]);
			}else{
				System.out.println("INF");
			}
		}
	}

	static class Edge implements Comparable<Edge>{
		int vertex;
		int weight;

		public Edge(int vertex, int weight){
			this.vertex = vertex;
			this.weight = weight;
		}

		public int getVertex(){
			return this.vertex;
		}

		public int getWeight(){
			return this.weight;
		}

		@Override
		public int compareTo(Edge e){
			return this.getWeight() - e.getWeight();
		}
	}
}
