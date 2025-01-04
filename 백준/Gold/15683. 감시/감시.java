import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;
	private static int result;
	private static int zeroCount = 0;
	private static int[][] arr;
	private static int[] dx = {0 , 1, 0, -1};
	private static int[] dy = {-1 , 0, 1, 0};
	private static int[][] dInds = {
		{},
		{0},
		{1, 3},
		{0, 1},
		{0,1,2},
		{0,1,2,3}
	};
	private static int[] rTime = {0, 4, 2, 4, 4, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> cctvs = getInput(br);
		backTracking(cctvs, 0, 0);
		System.out.println(zeroCount - result);
	}

	private static void backTracking(List<Integer> cctvs, int i, int count){
		if(cctvs.size() <= i) {
			result = Math.max(result, count);
			return;
		}
		int xy = cctvs.get(i);
		int x = xy / 10;
		int y = xy % 10;

		// 돌리기
		for(int k = 0; k < rTime[arr[y][x]]; k++){
			// 커버 범위 찾기
			int tc = updateArr(x, y, i, k);
			count += tc;
			// 다음 cctv 이동
			backTracking(cctvs, i + 1, count);
			// 원상 복구
			updateArr(x, y, i, k);
			count -= tc;
		}
	}

	private static int updateArr(int x, int y, int cctvInd, int rotateW){
		int[] dInd = dInds[arr[y][x]];
		int count = 0;
		cctvInd += 7;

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

	private static List<Integer> getInput(BufferedReader br) throws IOException {
		// 입력.
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		arr = new int[N][M];
		List<Integer> cctvs = new ArrayList<>();
		for(int i = 0; i < N; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++){
				arr[i][j] = Integer.parseInt(input[j]);
				if(arr[i][j] == 0){
					zeroCount++;
				}else if(arr[i][j] < 6){
					cctvs.add(j * 10 + i);
				}
			}
		}

		return cctvs;
	}
}