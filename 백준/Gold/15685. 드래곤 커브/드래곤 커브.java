import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static final int SIZE = 101;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] options = getInput(br);
		int result = solution(options);
		System.out.println(result);
	}

	private static int solution(int[][] options){
		boolean[][] arr = new boolean[SIZE][SIZE];
		List<Integer> list;
		for(int[] option: options){
			list = new ArrayList<>();
			list.add(option[0] * 1000 + option[1]);
			arr[option[1]][option[0]] = true;

			int nx = option[0] + dx[option[2]];
			int ny = option[1] + dy[option[2]];
			list.add(nx * 1000 + ny);
			arr[ny][nx] = true;
			dragonCurve(arr, list, nx, ny, option[3], 1);
		}
		return findSquare(arr);
	}

	private static int findSquare(boolean[][] arr){
		int result = 0;
		for(int i = 0; i < SIZE - 1; i++){
			for(int j = 0; j < SIZE - 1; j++){
				if(arr[i][j] && arr[i + 1][j] && arr[i][j + 1] && arr[i + 1][j + 1]){
					result++;
				}
			}
		}

		return result;
	}

	private static void dragonCurve(boolean[][] arr, List<Integer> list, int tx, int ty, int gen, int now){
		if(gen < now){
			return;
		}
		int ntx = 0;
		int nty = 0;
		int size = list.size();
		for(int i = 0; i < size; i++){
			int xy = list.get(i);
			int nxy = getNewXY(xy, tx, ty);
			if(xy == nxy) continue;
			list.add(nxy);
			int nx = nxy / 1000;
			int ny = nxy % 1000;
			arr[ny][nx] = true;
			if(i == 0){
				ntx = nx;
				nty = ny;
			}
		}

		dragonCurve(arr, list, ntx, nty, gen, ++now);
	}

	private static int getNewXY(int xy, int tx, int ty){
		int x = tx - (xy % 1000 - ty);
		int y = ty + (xy / 1000 - tx);

		return x * 1000 + y;
	}

	private static int[][] getInput(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][4];

		String[] line;

		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			arr[i] = new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])};
		}
		return arr;
	}

}
