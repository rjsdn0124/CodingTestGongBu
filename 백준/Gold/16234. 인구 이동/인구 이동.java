import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static int min;
	private static int max;
	private static final int[] dx = new int[] { 1, 0, -1, 0 };
	private static final int[] dy = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);
		int result = solution(arr);
		System.out.println(result);
	}

	public static int solution(int[][] arr){
		int result = 0;
		while(true){
			boolean isUpdated = false;
			boolean[][] visited = new boolean[n][n];

			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(!visited[i][j]){
						isUpdated |= bfs(j, i, arr, visited);
					}
				}
			}
			if(isUpdated) {
				result++;
			}else{
				break;
			}
		}

		return result;
	}

	public static boolean bfs(int x, int y, int[][] arr, boolean[][] visited){
		Queue<int[]> q = new LinkedList<>();
		List<int[]> xys = new LinkedList<>();
		q.add(new int[] { x, y });
		visited[y][x] = true;
		int sum = 0;
		int count = 0;

		while(!q.isEmpty()){
			int[] xy = q.poll();
			xys.add(xy);
			sum += arr[xy[1]][xy[0]];
			count++;

			for(int i = 0; i < 4; i++){
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];
				if(0 <= nx && nx < n && 0 <= ny && ny < n && !visited[ny][nx]){
					int gap = Math.abs(arr[xy[1]][xy[0]] - arr[ny][nx]);
					if(min <= gap && gap <= max){
						q.add(new int[]{nx, ny});
						visited[ny][nx] = true;
					}
				}
			}
		}
		if(count > 1){
			int avg = sum / count;
			for(int[] xy: xys){
				arr[xy[1]][xy[0]] = avg;
			}
			return true;
		}
		return false;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		// 입력.
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		min = Integer.parseInt(input[1]);
		max = Integer.parseInt(input[2]);

		int[][] arr = new int[n][n];

		for(int i = 0; i < n; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < n; j++){
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}
		return arr;
	}
}