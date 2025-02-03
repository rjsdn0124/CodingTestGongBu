import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main{
	private static int N;
	private static final String SPACE = " ";
	private static StringBuilder sb = new StringBuilder();
	private static List<Integer>[] arr;
	private static int[] parentCount;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(sb);
	}

	private static void solution(){
		Queue<Integer> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++){
			if(parentCount[i] == 0) {
				pq.add(i);
			}
		}

		while(!pq.isEmpty()){
			int p = pq.poll();
			sb.append(p + 1).append(SPACE);

			for(int c : arr[p]){
				if(--parentCount[c] == 0){
					pq.add(c);
				}
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(SPACE);
		N = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		arr = new ArrayList[N];
		parentCount = new int[N];

		for(int i = 0; i < N; i++) {
			arr[i] = new ArrayList<>();
		}

		for(int i = 0; i < m; i++) {
			String[] l = br.readLine().split(SPACE);
			int parent = Integer.parseInt(l[0]) - 1;
			int child = Integer.parseInt(l[1]) - 1;
			arr[parent].add(child);
			parentCount[child]++;
		}
	}
}