import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main{
	private static int N;
	private static int M;
	private static int cheeseCount = 0;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);
		int result = solution(arr);
		System.out.println(result);
	}

	private static int solution(int[][] arr){
		int result = 0;
		int airCount;
		int nx;
		int ny;
		Queue<Integer> q = new LinkedList<>();
		while(cheeseCount > 0){
			boolean[][] visited = new boolean[N][M];
			findAirZone(arr, visited, 0, 0);
			for(int i = 0; i < N; i++){
				for(int j = 0; j < M; j++){
					if(arr[i][j] == 1){
						airCount = 0;
						for(int k = 0; k < 4; k++){
							nx = j + dx[k];
							ny = i + dy[k];
							if(arr[ny][nx] == 2){
								airCount++;
							}
						}
						if(airCount >= 2){
							cheeseCount--;
							arr[i][j] = 0;
						}
					}
				}
			}
			result++;
		}
		return result;
	}

	private static void findAirZone(int[][] arr, boolean[][] visited, int x, int y){
		int nx;
		int ny;
		arr[y][x] = 2;
		visited[y][x] = true;
		for(int i = 0; i < 4; i++){
			nx = x + dx[i];
			ny = y + dy[i];
			if(0 <= nx && nx < M && 0 <= ny && ny < N && !visited[ny][nx] && arr[ny][nx] != 1){
				findAirZone(arr, visited, nx, ny);
			}
		}
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		int[][] arr = new int[N][M];

		for(int i = 0; i < N; i++){
			line = br.readLine().split(" ");
			for(int j = 0; j < M; j++){
				arr[i][j] =Integer.parseInt(line[j]);
				if(arr[i][j] == 1){
					cheeseCount++;
				}
			}
		}

		return arr;
	}
}
