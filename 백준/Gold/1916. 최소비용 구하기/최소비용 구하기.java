import java.io.*;
import java.util.*;

public class Main{
	private static int cityCount;
	private static int startPoint;
	private static int endPoint;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<Node>[] arr = getInput(br);

		solution(arr);
	}

	private static void solution(List<Node>[] arr){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[cityCount + 1];

		pq.add(new Node(startPoint, 0));

		while(!visited[endPoint]){
			search(arr, pq, visited);
		}
	}

	private static void search(List<Node>[] arr, PriorityQueue<Node> pq, boolean[] visited){
		Node node = pq.poll();
		visited[node.getValue()] = true;

		if(node.getValue() == endPoint){
			System.out.println(node.getWeight());
			return;
		}

		for(Node n: arr[node.getValue()]){
			if(!visited[n.getValue()]){
				pq.add(new Node(n.getValue(), node.getWeight() + n.getWeight()));
			}
		}
	}

	private static List<Node>[] getInput(BufferedReader br) throws IOException{
		cityCount = Integer.parseInt(br.readLine());
		int busCount = Integer.parseInt(br.readLine());

		List<Node>[] arr = new ArrayList[cityCount + 1];
		for(int i = 1; i <= cityCount; i++){
			arr[i] = new ArrayList<>();
		}

		for(int i = 0; i < busCount; i++){
			String[] input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			arr[start].add(new Node(end, weight));
		}

		String[] input = br.readLine().split(" ");
		startPoint = Integer.parseInt(input[0]);
		endPoint = Integer.parseInt(input[1]);

		return arr;
	}

	private static class Node implements Comparable<Node>{
		private final int value;
		private final int weight;

		public Node(int value, int weight){
			this.value = value;
			this.weight = weight;
		}

		public int getValue(){
			return this.value;
		}

		public int getWeight(){
			return this.weight;
		}

		@Override
		public int compareTo(Node node){
			return this.weight - node.getWeight();
		}
	}
}