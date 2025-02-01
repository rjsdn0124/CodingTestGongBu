import java.io.*;
import java.util.*;

public class Main{
	private static int N, minL, minR;
	private static int[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(minL + " " + minR);
	}

	private static void solution(){
		// 투포인터
		int l = 0;
		int r = N - 1;
		int result = Math.abs(arr[r] + arr[l]);

		while(l < r){
			int temp = arr[r] + arr[l];
			if(result >= Math.abs(temp)){
				minL = arr[l];
				minR = arr[r];
				result = Math.abs(temp);
			}

			if(temp > 0){
				r--;
			}else if(temp < 0){
				l++;
			}else{
				return;
			}
		}

	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		String[] l = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(l[i]);
		}
		Arrays.sort(arr);
	}
}