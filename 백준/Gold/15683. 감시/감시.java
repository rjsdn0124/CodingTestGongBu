import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;
	private static int result;
	private static int zeroCount = 0;
	private static int[][] arr;
	private static int[] dx = new int[]{0 , 1, 0, -1};
	private static int[] dy = new int[]{-1 , 0, 1, 0};
	private static int[][] dInds = new int[][]{
		{},
		{0},
		{1, 3},
		{0, 1},
		{0,1,2},
		{0,1,2,3}
	};
	private static int[] rTime = new int[]{0, 4, 2, 4, 4, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		getInput(br);
		backTracking(0, 0, 7, 0);
		System.out.println(zeroCount - result);
	}

	private static void backTracking(int x, int y, int cctvInd, int count){
		for(int i = y; i < N; i++){
			for(int j = x; j < M; j++){
				if(arr[i][j] > 0 && arr[i][j] < 6){
					// 돌리기
					for(int k = 0; k < rTime[arr[i][j]]; k++){
						// 커버 범위 찾기
						int tc = updateArr(j, i, cctvInd, k);
						count += tc;
						// 다음 cctv 이동
						backTracking(j + 1, i, cctvInd + 1, count);
						// 원상 복구
						updateArr(j, i, cctvInd, k);
						count -= tc;
					}

					return;
				}
			}
			x = 0;
		}
		result = Math.max(result, count);
	}

	private static int updateArr(int x, int y, int cctvInd, int rotateW){
		int[] dInd = dInds[arr[y][x]];
		int count = 0;

		for(int i: dInd){
			i = (i + rotateW) % 4;
			int tx = x + dx[i];
			int ty = y + dy[i];

			while(tx >= 0 && tx < M && ty >= 0 && ty < N){
				if(arr[ty][tx] == 0){
					arr[ty][tx] = cctvInd;
					count++;
				}else if(arr[ty][tx] == cctvInd){
					arr[ty][tx] = 0;
				}
				else if(arr[ty][tx] == 6){
					break;
				}
				tx += dx[i];
				ty += dy[i];
			}
		}
		return count;
	}

	private static void getInput(BufferedReader br) throws IOException {
		// 입력.
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		arr = new int[N][M];

		for(int i = 0; i < N; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++){
				arr[i][j] = Integer.parseInt(input[j]);
				if(arr[i][j] == 0){
					zeroCount++;
				}
			}
		}
	}
}