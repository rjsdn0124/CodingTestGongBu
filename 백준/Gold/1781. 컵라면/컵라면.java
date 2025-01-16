import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main{
	private static int N, M = 0, result = 0;
	private static Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> -a[1]));
	private static int[] dlSet;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.println(result);
	}

	private static void solution(){
		while(!pq.isEmpty()){
			int[] homework = pq.poll();
			int dl = find(homework[0]);
			if(dl > 0){
				result += homework[1];
				if(--M == 0){
					return;
				}
			}
		}
	}

	private static int find(int dl){
		if(dlSet[dl] == dl){
			if(dl == 0) return 0;
			dlSet[dl]--;
			return dl;
		}
		dlSet[dl] = find(dlSet[dl]);
		return dlSet[dl];
	}

	private static void getInput(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		String[] line;
		for(int i = 1; i <= N; i++){
			line = br.readLine().split(" ");
			int dl = Integer.parseInt(line[0]);
			pq.add(new int[]{dl, Integer.parseInt(line[1])});
			M = Math.max(dl, M);
		}

		dlSet = new int[M + 1];

		for(int i = 1; i <= M; i++){
			dlSet[i] = i;
		}
	}
}