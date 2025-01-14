import java.io.*;
import java.util.*;

public class Main{
	private static int N, K;
	private static Queue<int[]> jewelQue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
	private static Queue<Integer> bagQue = new PriorityQueue<>();
	private static Queue<Integer> valueQue = new PriorityQueue<>(Comparator.reverseOrder());

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		getInput(br);
		long result = solution();
		System.out.println(result);
	}

	private static long solution(){
		long result = 0;
		while(!bagQue.isEmpty()){
			int bag = bagQue.poll();
			while(!jewelQue.isEmpty() && jewelQue.peek()[0] <= bag){
				valueQue.add(jewelQue.poll()[1]);
			}
			if(!valueQue.isEmpty()){
				result += valueQue.poll();
			}
		}
		return result;
	}

	private static void getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);

		for(int i = 0 ; i < N; i++){
			line = br.readLine().split(" ");
			jewelQue.add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])});
		}

		for(int i = 0; i < K; i++){
			bagQue.add(Integer.parseInt(br.readLine()));
		}
	}
}