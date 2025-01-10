import java.io.*;
import java.util.List;

public class Main{
	private static int N;
	private static int sum;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 3; i++){
			sum = 0;
			int[][] arr = getInput(br);

			System.out.println(solution(arr));
		}
	}

	private static int solution(int[][] arr){
		if(sum % 2 == 1) return 0;

		int result = sum / 2 + 1;
		int[] dp = new int[result];
		dp[0] = 1;
		int tc;
		int count;
		for(int i = 0; i < N; i++){
			tc = arr[i][0];
			count = arr[i][1];

			for(int j = result - tc - 1; j >= 0; j--){
				if(dp[j] > 0){
					for(int k = 1; k <= count && j + tc * k < result; k++){
						dp[j + tc * k] = 1;
					}
				}
			}
		}

		return dp[result - 1];
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][2];

		for(int i = 0; i < N; i++){
			String[] line = br.readLine().split(" ");
			arr[i] = new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])};
			sum += arr[i][0] * arr[i][1];
		}

		return arr;
	}
}