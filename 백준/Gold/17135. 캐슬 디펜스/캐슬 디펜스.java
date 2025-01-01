import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;
	private static int D;
	private static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);
		locateArchers(arr, new int[3], 0, 0);
		System.out.println(result);
	}

	private static void locateArchers(int[][] arr, int[] archers, int ind, int depth){
		if(depth == 3){
			// 배치 효율성 테스트하기.
			int tempResult = defense(arr, archers);
			result = Math.max(result, tempResult);
			return;
		}
		for(int i = ind; i < M; i++){
			archers[depth] = i;
			locateArchers(arr, archers, ind + 1, depth + 1);
		}
	}

	private static int defense(int[][] arr, int[] archers){
		int[][] isKilled = new int[N][M];
		int result = 0;


		for(int i = N - 1; i >= 0; i--){
			for(int archer: archers){
				result += shoot(arr, isKilled, archer, i);
			}
		}

		return result;
	}

	private static int shoot(int[][] arr, int[][] isKilled, int archerLoc, int turn){
		for(int i = 0; i < D; i++){
			int x = archerLoc - i;
			int y = turn;
			for(int j = 0; j < i; j++){
				if(canKill(arr, isKilled, x, y, turn)){
					if(isKilled[y][x] == turn + 1){
						return 0;
					}
					isKilled[y][x] = turn + 1;
					return 1;
				}
				x++;
				y--;
			}
			for(int j = 0; j <= i; j++){
				if(canKill(arr, isKilled, x, y, turn)){
					if(isKilled[y][x] == turn + 1){
						return 0;
					}
					isKilled[y][x] = turn + 1;
					return 1;
				}
				x++;
				y++;
			}
		}
		return 0;
	}

	private static boolean canKill(int[][] arr, int[][] isKilled, int x, int y, int turn){
		return x >= 0 && x < M && y >= 0 && y < N && arr[y][x] > 0 && isKilled[y][x] <= turn + 1;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		// 입력.
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		D = Integer.parseInt(input[2]);

		int[][] arr = new int[N][M];

		for(int i = 0; i < N; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++){
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}
		return arr;
	}
}

