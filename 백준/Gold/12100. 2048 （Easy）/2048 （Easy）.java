import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static int result = 0;
	private static final int MAX_DEPTH = 5;
	private static final boolean[][] MOVE_OPT = new boolean[][]{
		{true, true},
		{true, false},
		{false, true},
		{false, false}
	};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);
		dfs(arr, 0);
		System.out.println(result);
	}

	public static void dfs(int[][] arr, int depth){
		if(depth == MAX_DEPTH){
			return;
		}
		for(int i = 0; i < 4; i++){
			int[][] newArr = new int[n][n];
			int maxV;
			if(MOVE_OPT[i][0]){
				maxV = updateGaro(arr, newArr, MOVE_OPT[i][1]);
			}else{
				maxV = updateSero(arr, newArr, MOVE_OPT[i][1]);
			}
			result = Math.max(result, maxV);
			dfs(newArr, depth + 1);
		}
	}

	public static int updateGaro(int[][] arr, int[][] newArr, boolean isReversed){
		int maxValue = 0;

		for(int i = 0; i < n; i++){
			int sp = isReversed ? 0 : n - 1;

			for(int j = 0; j < n; j++){
				int x = isReversed ? j : n - 1 - j;

				if (arr[i][x] != 0) {
					int maxV = arr[i][x];
					if(newArr[i][sp] == 0){
						newArr[i][sp] = arr[i][x];
					}
					else{
						if (arr[i][x] == newArr[i][sp]) {
							newArr[i][sp] *= 2;
							maxV = newArr[i][sp];
							sp += isReversed ? 1 : -1;
						} else {
							sp += isReversed ? 1 : -1;
							newArr[i][sp] = arr[i][x];
						}
					}
					maxValue = Math.max(maxValue, maxV);
				}
			}
		}
		return maxValue;
	}

	public static int updateSero(int[][] arr, int[][] newArr, boolean isReversed){
		int maxValue = 0;

		for(int i = 0; i < n; i++){
			int sp = isReversed ? 0 : n - 1;

			for(int j = 0; j < n; j++){
				int y = isReversed ? j : n - 1 - j;

				if (arr[y][i] != 0) {
					int maxV = arr[y][i];
					if(newArr[sp][i] == 0){
						newArr[sp][i] = arr[y][i];
					}
					else{
						if (arr[y][i] == newArr[sp][i]) {
							newArr[sp][i] *= 2;
							maxV = newArr[sp][i];
							sp += isReversed ? 1 : -1;
						} else {
							sp += isReversed ? 1 : -1;
							newArr[sp][i] = arr[y][i];
						}
					}
					maxValue = Math.max(maxValue, maxV);
				}
			}
		}
		return maxValue;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		// 입력.
		n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n][n];

		for(int i = 0; i < n; i++){
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < n; j++){
				arr[i][j] = Integer.parseInt(input[j]);

			}
		}
		return arr;
	}
}