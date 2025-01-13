import java.io.*;
import java.util.*;

public class Main{
	private static int v,e,t,s,g,h;
	private static List<int[]>[] edges;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(solution(br));
	}

	private static StringBuilder solution(BufferedReader br) throws IOException{
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		String[] line;

		while(n-- > 0){
			line = br.readLine().split(" ");
			v = Integer.parseInt(line[0]);
			e = Integer.parseInt(line[1]);
			t = Integer.parseInt(line[2]);
			line = br.readLine().split(" ");
			s = Integer.parseInt(line[0]);
			g = Integer.parseInt(line[1]);
			h = Integer.parseInt(line[2]);

			edges = new ArrayList[v + 1];
			for(int i = 1; i <= v; i++){
				edges[i] = new ArrayList<>();
			}

			int n1, n2, w;
			for(int i = 0; i < e; i++) {
				line = br.readLine().split(" ");
				n1 = Integer.parseInt(line[0]);
				n2 = Integer.parseInt(line[1]);
				w = Integer.parseInt(line[2]);
				edges[n1].add(new int[]{n2, w});
				edges[n2].add(new int[]{n1, w});
			}

			Set<Integer> targets = new HashSet<>();
			for(int i = 0; i < t; i++){
				targets.add(Integer.parseInt(br.readLine()));
			}

			List<Integer> result = dijk(targets);
			Collections.sort(result);
			for(int i = 0; i < result.size(); i++){
				sb.append(result.get(i)).append(" ");
			}
			sb.append("\n");
		}

		return sb;
	}

	private static List<Integer> dijk(Set<Integer> targets){
		Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		List<Integer> result = new ArrayList<>();
		int[] shortPath = new int[v + 1];
		boolean[] isPassed = new boolean[v + 1];
		int[] edge;
		int nowWeight;
		q.add(new int[]{s, 0, 0});
		Arrays.fill(shortPath, Integer.MAX_VALUE);

		shortPath[s] = 0;
		while(!q.isEmpty()){
			edge = q.poll();
			if(shortPath[edge[0]] < edge[1]) {
				continue;
			}

			if(targets.contains(edge[0]) && isPassed[edge[0]]){
				result.add(edge[0]);
				targets.remove(edge[0]);
				if(targets.isEmpty()){
					return result;
				}
			}

			for(int[] te: edges[edge[0]]){
				nowWeight = te[1] + edge[1];
				if(shortPath[te[0]] > nowWeight) {
					// 다음 목적지 갱신 가능하면 q에 추가.
					shortPath[te[0]] = nowWeight;
					q.add(new int[]{te[0], nowWeight});
					isPassed[te[0]] = isPassed[edge[0]] || checkIsPassed(edge, te[0]);
				}else if(shortPath[te[0]] == nowWeight && !isPassed[te[0]]){
					// 다음 목적지 결과가 같으면 gh 지난 친구만 넣기.
					if(isPassed[edge[0]]) isPassed[te[0]] = true;
					else isPassed[te[0]] = checkIsPassed(edge, te[0]);
				}
			}
		}
		return result;
	}

	private static boolean checkIsPassed(int[] edge, int tn){
		return (edge[0] == g || edge[0] == h) && (tn == g || tn == h);
	}
}