import java.io.*;
import java.util.*;

public class Main{
	private static int v,e,t,s,g,h;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(solution(br));
	}

	private static StringBuilder solution(BufferedReader br) throws IOException{
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		String[] line;
		Queue<Integer> result = new PriorityQueue<>();
		List<Integer>[] edges = new ArrayList[2001];
		Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

		while(n-- > 0){
			line = br.readLine().split(" ");
			v = Integer.parseInt(line[0]);
			e = Integer.parseInt(line[1]);
			t = Integer.parseInt(line[2]);
			line = br.readLine().split(" ");
			s = Integer.parseInt(line[0]);
			g = Integer.parseInt(line[1]);
			h = Integer.parseInt(line[2]);

			for(int i = 1; i <= v; i++){
				edges[i] = new ArrayList<>();
			}

			int n1, n2, w;
			for(int i = 0; i < e; i++) {
				line = br.readLine().split(" ");
				n1 = Integer.parseInt(line[0]);
				n2 = Integer.parseInt(line[1]);
				w = Integer.parseInt(line[2]);
				edges[n1].add(createMyEW(n2, w));
				edges[n2].add(createMyEW(n1, w));
			}

			boolean[] isPassed = new boolean[v + 1];
			dijk(q, isPassed, edges);
			for(int i = 0; i < t; i++){
				int res = Integer.parseInt(br.readLine());
				if(isPassed[res]) {
					result.add(res);
				}
			}
			while(!result.isEmpty()){
				sb.append(result.poll()).append(" ");
			}
			sb.append("\n");
		}

		return sb;
	}

	private static void dijk(Queue<int[]> q, boolean[] isPassed, List<Integer>[] edges){
		q.add(new int[]{s, 0});
		int[] shortPath = new int[v + 1];
		Arrays.fill(shortPath, Integer.MAX_VALUE);

		shortPath[s] = 0;
		while(!q.isEmpty()){
			int[] edge = q.poll();
			if(shortPath[edge[0]] < edge[1]) {
				continue;
			}

			for(int tew: edges[edge[0]]){
				int te = tew / 1001;
				int nowWeight = tew % 1001 + edge[1];
				if(shortPath[te] > nowWeight) {
					// 다음 목적지 갱신 가능하면 q에 추가.
					shortPath[te] = nowWeight;
					q.add(new int[]{te, nowWeight});
					isPassed[te] = isPassed[edge[0]] || (checkIsPassed(edge[0]) && checkIsPassed(te));
				}else if(shortPath[te] == nowWeight && !isPassed[te]){
					// 다음 목적지 결과가 같으면 gh 지났는지 체크
					isPassed[te] = isPassed[edge[0]] || (checkIsPassed(edge[0]) && checkIsPassed(te));
				}
			}
		}
	}

	private static int createMyEW(int e, int w){
		return e * 1001 + w;
	}

	private static boolean checkIsPassed(int node){
		return (node == g || node == h);
	}
}