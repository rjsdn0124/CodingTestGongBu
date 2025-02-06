import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;
	private static int[] arr, powTen, onlyOne;
	private static int[][] weights;
	private static Queue<Node> q;
	private static Map<Integer, Integer> visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		System.out.println(solution());
	}

	private static int solution(){
		q = new PriorityQueue<>();
		visited = new HashMap<>();
		Node tarr = new Node(arrToInt(), 0);
		q.add(tarr);

		while(!q.isEmpty()){
			tarr = q.poll();
			if(!visited.containsKey(tarr.arr)) {
				if(isIncreasing(tarr.arr)){
					return tarr.weightSum;
				}
				visited.put(tarr.arr, tarr.weightSum);
				for (int[] weight : weights) {
					int nArr = updateArr(tarr.arr, weight[0], weight[1]);
					if (!visited.containsKey(nArr)) {
						q.add(new Node(nArr, tarr.weightSum + weight[2]));
					}
				}
			}
		}

		return -1;
	}

	private static boolean isIncreasing(int arr){
		int prev = 0;
		int temp;

		for(int i = 0; i < N; i++){
			temp = arr / powTen[i];
			if(prev > temp){
				return false;
			}
			prev = temp;
			arr %= powTen[i];
		}
		return true;
	}

	private static int updateArr(int arr, int l, int r){
		int lv = arr / powTen[l] % 10;
		int rv = arr / powTen[r] % 10;
		arr += (rv - lv) * powTen[l];
		arr += (lv - rv) * powTen[r];
		return arr;
	}

	private static int arrToInt(){
		int result = arr[0];

		for(int i = 1; i < N; i++){
			result *= 10;
			result += arr[i];
		}

		return result;
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		powTen = new int[N];

		String[] line = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(line[i]) - 1;
			powTen[N - 1 - i] = (int) Math.pow(10, i);
		}

		M = Integer.parseInt(br.readLine());
		weights = new int[M][3];
		for(int i = 0; i < M; i++) {
			String[] l = br.readLine().split(" ");
			weights[i] = new int[3];
			weights[i][0] = Integer.parseInt(l[0]) - 1;
			weights[i][1] = Integer.parseInt(l[1]) - 1;
			weights[i][2] = Integer.parseInt(l[2]);
		}
	}
}

class Node implements Comparable<Node>{
	int arr;
	int weightSum;

	public Node(int arr, int weightSum){
		this.arr = arr;
		this.weightSum = weightSum;
	}

	public int compareTo(Node arrs){
		return this.weightSum - arrs.weightSum;
	}
}