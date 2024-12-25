import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static int m;
	private static int[] requirement;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = getInput(br);
		int[] result = solution(arr);

		printResult(result);
	}

	private static int[] solution(int[] arr){
		int[] result = new int[m];

		for(int i = 0; i < m; i++){
			result[i] = binarySearch(arr, 0, n, requirement[i]);
		}

		return result;
	}

	private static int binarySearch(int[] arr, int start, int end, int target){
		if(start >= end) {
			return 0;
		}
		int m = (start + end) / 2;
		if(arr[m] > target){
			return binarySearch(arr, start, m, target);
		}else if(arr[m] < target){
			return binarySearch(arr, m+1, end, target);
		}else{
			return 1;
		}
	}

	private static void printResult(int[] result){
		// result 출력.
		for(int i = 0; i < m; i++){
			System.out.print(result[i] + " ");
		}
	}

	private static int[] getInput(BufferedReader br) throws IOException{
		// 입력.
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		String[] input = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(input[i]);
		}

		Arrays.sort(arr);

		m = Integer.parseInt(br.readLine());
		requirement = new int[m];

		input = br.readLine().split(" ");
		for(int i = 0; i < m; i++){
			requirement[i] = Integer.parseInt(input[i]);
		}

		return arr;
	}
}

