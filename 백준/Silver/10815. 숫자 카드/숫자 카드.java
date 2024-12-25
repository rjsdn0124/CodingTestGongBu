import java.io.*;
import java.util.*;

public class Main{
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		solution(br);
	}

	private static int binarySearch(int[] arr, int target){
		int start = 0;
		int end = n - 1;
		while(start <= end) {
			int m = (start + end) / 2;
			if(arr[m] > target){
				end = m - 1;
			}else if(arr[m] < target){
				start = m + 1;
			}else{
				return 1;
			}
		}
		return 0;
	}

	private static void solution(BufferedReader br) throws IOException{
		// 입력.
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		String[] input = br.readLine().split(" ");
		for(int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(input[i]);
		}

		Arrays.sort(arr);

		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		input = br.readLine().split(" ");

		for(int i = 0; i < m; i++){
			int result = binarySearch(arr, Integer.parseInt(input[i]));
			sb.append(result).append(" ");
		}

		System.out.println(sb);
	}
}