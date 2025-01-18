import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;
	private static int[][] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		int result = solution();
		System.out.println(result);
	}

	private static int solution(){
		int result = 0;
		int start = 0;

		for(int i = 0; i < N; i++){
			start = Math.max(arr[i][0], start);
			if(start < arr[i][1]){
				int sToE = (arr[i][1] - start - 1) / M + 1;
				result += sToE;
				start = start + sToE * M;
			}
		}

		return result;
	}

	private static void getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		arr = new int[N][2];

		for(int i = 0; i < N; i++){
			line = br.readLine().split(" ");
			arr[i] = new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])};
		}

		Arrays.sort(arr, (a, b) -> a[0] - b[0]);
	}
}