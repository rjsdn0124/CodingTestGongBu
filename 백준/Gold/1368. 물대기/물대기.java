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
		List<Integer>[] group = new LinkedList[n + 1];
		int headGroupNum = 1;

		while(!pq.isEmpty()){
			Edge e = pq.poll();
			int[] nodes = e.getNodes();
			if(groupNum[nodes[0]] > 0 && groupNum[nodes[1]] > 0){
				if(groupNum[nodes[0]] != groupNum[nodes[1]]){
					for(int n: group[groupNum[nodes[1]]]){
						groupNum[n] = groupNum[nodes[0]];
						group[groupNum[n]].add(n);
					}
				}
				else continue;
			}
			else{
				if(groupNum[nodes[0]] > 0){
					groupNum[nodes[1]] = groupNum[nodes[0]];
					group[groupNum[nodes[1]]].add(nodes[1]);
				} else if(groupNum[nodes[1]] > 0){
					groupNum[nodes[0]] = groupNum[nodes[1]];
					group[groupNum[nodes[0]]].add(nodes[0]);
				} else {
					group[headGroupNum] = new LinkedList<>();
					for(int i = 0; i < 2; i++){
						groupNum[nodes[i]] = headGroupNum;
						group[headGroupNum].add(nodes[i]);
					}
					headGroupNum++;
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