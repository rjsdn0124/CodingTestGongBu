import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static long M;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<int[]>[] map = getInput(br);
		System.out.println(solution(map));
	}

	private static long solution(List<int[]>[] map){
		Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(a->calSpentTime(a[1], a[2])));
		pq.add(new int[]{ 1, 0, 0 });
		long[] updatedArr = new long[N + 1];
		Arrays.fill(updatedArr, Long.MAX_VALUE);
		updatedArr[1] = 0;

		while(!pq.isEmpty()){
			int[] info = pq.poll();
			long spentTime = calSpentTime(info[1], info[2]);

			if(updatedArr[info[0]] >= spentTime){
				if(info[0] == N) {
					return spentTime;
				}

				for(int[] cw : map[info[0]]){
					int next = cw[1] + 1;
					long tst = calSpentTime(next, info[2]);
					if(updatedArr[cw[0]] > tst && cw[1] >= info[1]){
						pq.add(new int[]{cw[0], next, info[2]});
						updatedArr[cw[0]] = tst;
					}else if(updatedArr[cw[0]] > tst + M){
						pq.add(new int[]{cw[0], next, info[2] + 1});
						updatedArr[cw[0]] = tst + M;
					}
				}
			}
		}

		return 0;
	}

	private static long calSpentTime(int time, int cycle){
		return cycle * M + time;
	}

	private static List<int[]>[] getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		List<int[]>[] map = new LinkedList[N + 1];

		for(int i = 1; i <= N; i++){
			map[i] = new LinkedList<>();
		}

		for(int i = 0 ; i < M; i++){
			line = br.readLine().split(" ");
			int n1 = Integer.parseInt(line[0]);
			int n2 = Integer.parseInt(line[1]);
			map[n1].add(new int[]{n2, i});
			map[n2].add(new int[]{n1, i});
		}

		return map;
	}
}