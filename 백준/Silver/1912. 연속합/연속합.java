import java.io.*;
import java.util.*;

public class Main{
	private static int N, result = Integer.MIN_VALUE;
	private static final String SPACE = " ";
	private static int[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(result);
	}

	private static void solution(){
		int prevSum = 0;
		for(int i = 0; i < N; i++){
			if(prevSum < 0){
				prevSum = arr[i];
			}else{
				prevSum += arr[i];
			}
			result = Math.max(prevSum, result);
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		String[] l = br.readLine().split(SPACE);
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(l[i]);
		}
	}
}