import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;
	private static StringBuilder sb = new StringBuilder();
	private static Problem[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(sb);
	}

	private static void solution(){
		// 부모 갯수, 자식 노드 저장
		Queue<Integer> pq = new PriorityQueue<>();
		// 위상정렬
		// 처음 한 번 돌면서 부모가 0인 친구들 pq에 넣기.
		for(int i = 1; i <= N; i++){
			if(arr[i].parentCount == 0){
				pq.add(i);
			}
		}
		// pq 순회하면서 갯수 적고 난이도 낮은 친구 처리.
		while(!pq.isEmpty()){
			int parent = pq.poll();

			sb.append(parent).append(" ");

			for(int cp: arr[parent].childProblems){
				if(--arr[cp].parentCount == 0){
					pq.add(cp);
				}
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		arr = new Problem[N + 1];

		for(int i = 1; i <= N; i++){
			arr[i] = new Problem();
		}

		for(int i = 0; i < M; i++) {
			String[] l = br.readLine().split(" ");
			int parent = Integer.parseInt(l[0]);
			int child = Integer.parseInt(l[1]);
			arr[parent].childProblems.add(child);
			arr[child].parentCount++;
		}
	}
}

class Problem{
	int parentCount;
	List<Integer> childProblems;

	public Problem(){
		parentCount = 0;
		childProblems = new ArrayList<>();
	}
}