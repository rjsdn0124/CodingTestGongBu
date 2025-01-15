import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = getInput(br);
		System.out.println(solution(arr));
	}

	private static long solution(int[] arr){
		int result = arr[N - 1] - arr[0];
		Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());

		int prev = arr[0];

		for(int i = 1; i < N; i++){
			int temp = arr[i] - prev;
			if(temp > 0) {
				q.add(temp);
			}
			prev = arr[i];
		}

		int areaCount = 1;
		while(!q.isEmpty() && areaCount++ < M){
			int width = q.poll();
			result -= width;
		}

		return result;
	}

	private static int[] getInput(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		String[] line = br.readLine().split(" ");

		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(line[i]);
		}
		Arrays.sort(arr);

		return arr;
	}
}