import java.io.*;
import java.util.*;

public class Main{
	private static int N, dest, oil;
	private static Node[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		int result = solution();
		System.out.println(result);
	}

	private static int solution(){
		int result = 0;
		int ind = 0;
		Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> -a));
		while(oil < dest){
			while(ind < N && arr[ind].loc <= oil) {
				pq.add(arr[ind++].oil);
			}
			if(pq.isEmpty()){
				return -1;
			}
			oil += pq.poll();
			result++;
		}

		return result;
	}

	private static void getInput(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		String[] line;
		arr = new Node[N];

		for(int i = 0; i < N; i++){
			line = br.readLine().split(" ");
			arr[i] = new Node(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}

		Arrays.sort(arr, Comparator.comparingInt(a -> a.loc));

		line = br.readLine().split(" ");
		dest = Integer.parseInt(line[0]);
		oil = Integer.parseInt(line[1]);
	}
}

class Node{
	int loc, oil;

	public Node(int loc, int oil){
		this.loc = loc;
		this.oil = oil;
	}
}