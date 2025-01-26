import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, r, c, d, result = 0;
	private static boolean[][] arr;
	private static boolean[][] visited;
	private static int[] dc = {0, 1, 0, -1};
	private static int[] dr = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(result);
	}

	private static void solution(){
		while(true) {
			// 현재 칸이 0 이면 result +1
			if(!visited[r][c] && arr[r][c]){
				result++;
				visited[r][c] = true;
			}
			// 청소 후 돌아보기.
			// 	있으면 이동
			// 	없으면 후진
			if(!rotateAndCheck() && !canGoBack()){
				break;
			}
			// 반복
		}
	}

	private static boolean rotateAndCheck(){
		for(int i = 0; i < 4; i++) {
			d = (d + 3) % 4;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (0 <= nc && nc < M && 0 <= nr && nr < N && !visited[nr][nc] && arr[nr][nc]) {
				r = nr;
				c = nc;
				return true;
			}
		}
		return false;
	}

	private static boolean canGoBack(){
		int td = (d + 2) % 4;
		int nr = r + dr[td];
		int nc = c + dc[td];
		if(0 <= nc && nc < M && 0 <= nr && nr < N && arr[nr][nc]){
			r = nr;
			c = nc;
			return true;
		}
		return false;
	}

	private static void init(BufferedReader br) throws IOException {
		String[] l = br.readLine().split(" ");
		N = Integer.parseInt(l[0]);
		M = Integer.parseInt(l[1]);

		l = br.readLine().split(" ");
		r = Integer.parseInt(l[0]);
		c = Integer.parseInt(l[1]);
		d = Integer.parseInt(l[2]);

		arr = new boolean[N][M];
		visited = new boolean[N][M];

		for(int i = 0; i < N; i++){
			l = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = l[j].equals("0");
			}
		}
	}
}