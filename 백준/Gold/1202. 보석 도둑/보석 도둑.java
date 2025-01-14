import java.io.*;
import java.util.*;

public class Main{
	private static int N, K;
	private static int[][] arr;
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
		int bag;
		int i = 0;
		while(!bagQue.isEmpty()){
			bag = bagQue.poll();
			while(i < N && arr[i][0] <= bag){
				valueQue.add(arr[i++][1]);
			}
			if(valueQue.isEmpty()){
				continue;
			}
			result += valueQue.poll();
		}
		return result;
	}

	private static void getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);

		arr = new int[N][2];
		for(int i = 0 ; i < N; i++){
			line = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(line[0]);
			arr[i][1] = Integer.parseInt(line[1]);
		}
		Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

		for(int i = 0; i < K; i++){
			bagQue.add(Integer.parseInt(br.readLine()));
		}
	}
}