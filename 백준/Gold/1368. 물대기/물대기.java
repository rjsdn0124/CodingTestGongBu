import java.io.*;
import java.util.*;

public class Main{
	private static int n;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Edge> pq = getInput(br);

		int result = solution(pq);
		System.out.println(result);
	}

	private static int solution(PriorityQueue<Edge> pq){
		int result = 0;
		int[] groupNum = new int[n + 1];
		Map<Integer, List<Integer>> group = new HashMap<>();
		int headGroupNum = 1;

		while(!pq.isEmpty()){
			Edge e = pq.poll();
			int[] nodes = e.getNodes();
			if(groupNum[nodes[0]] > 0 && groupNum[nodes[1]] > 0){
				if(groupNum[nodes[0]] != groupNum[nodes[1]]){
					for(int n: group.get(groupNum[nodes[1]])){
						groupNum[n] = groupNum[nodes[0]];
						group.get(groupNum[n]).add(n);
					}
				}
				else continue;
			}
			else {
				int gn1 = groupNum[nodes[0]];
				int gn2 = groupNum[nodes[1]];
				if (gn1 > 0) {
					groupNum[nodes[1]] = gn1;
					group.get(gn1).add(nodes[1]);
				} else if (gn2 > 0) {
					groupNum[nodes[0]] = gn2;
					group.get(gn2).add(nodes[0]);
				} else {
					List<Integer> list = new LinkedList<>();
					for (int i = 0; i < 2; i++) {
						groupNum[nodes[i]] = headGroupNum;
						list.add(nodes[i]);
					}
					group.put(headGroupNum++, list);
				}
			}
			result += e.getWeight();
		}
		return result;
	}

	private static PriorityQueue<Edge> getInput(BufferedReader br) throws IOException{
		// 입력.
		n = Integer.parseInt(br.readLine());

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for(int i = 0; i < n; i++){
			pq.add(new Edge(Integer.parseInt(br.readLine()), i + 1, 0));
		}

		for(int i = 0; i < n; i++){
			String[] input = br.readLine().split(" ");
			for(int j = i + 1; j < n; j++){
				pq.add(new Edge(Integer.parseInt(input[j]), i + 1, j + 1));
			}
		}
		return pq;
	}
}

class Edge implements Comparable<Edge>{
	private int weight;
	private int[] nodes;

	public Edge(int weight, int node1, int node2){
		this.weight = weight;
		this.nodes = new int[]{node1, node2};
	}

	@Override
	public int compareTo(Edge e){
		return this.weight - e.weight;
	}

	public int getWeight(){
		return this.weight;
	}

	public int[] getNodes(){
		return this.nodes;
	}
}