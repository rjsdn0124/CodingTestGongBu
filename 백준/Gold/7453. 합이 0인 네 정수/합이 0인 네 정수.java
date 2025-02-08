import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static long result = 0L;
	private static int[][] arr;
	private static int[][] sumArr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		solution();
		System.out.println(result);
	}

	private static void solution(){
		int ind = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				sumArr[0][ind] = arr[0][i] + arr[1][j];
				sumArr[1][ind++] = arr[2][i] + arr[3][j];
			}
		}

		Arrays.sort(sumArr[0]);
		Arrays.sort(sumArr[1]);

		int lastInd = sumArr[1].length - 1;
		long prev = Long.MIN_VALUE;
		int sameCount = 0;
		for(int i = 0; i < sumArr[0].length; i++){
			if(prev == sumArr[0][i]){
				result += sameCount;
			}else {
				prev = sumArr[0][i];
				sameCount = 0;
				while (lastInd >= 0) {
					if (sumArr[1][lastInd] + sumArr[0][i] < 0) {
						break;
					} else if (sumArr[1][lastInd] + sumArr[0][i] == 0) {
						sameCount++;
						result++;
					}
					lastInd--;
				}
			}

		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[4][N];
		sumArr = new int[2][N * N];

		for(int i = 0; i < N; i++){
			String[] line = br.readLine().split(" ");
			for(int j = 0; j < 4; j++) {
				arr[j][i] = Integer.parseInt(line[j]);
			}
		}
	}
}