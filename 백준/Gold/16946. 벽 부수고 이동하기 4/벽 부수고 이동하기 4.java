import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;
	private static StringBuilder sb = new StringBuilder();
	private static boolean[][] arr;
	private static boolean[][] visited;
	private static int[][][] areaCounts;
	private static int[] dx = new int[]{1, 0, -1, 0};
	private static int[] dy = new int[]{0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(sb);
	}

	private static void solution(){
		// 0을 찾아서 0 영역 갯수 세고 각 좌표별 갯수 저장.
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(!visited[i][j] && arr[i][j]){
					dfs(j, i, new int[]{0});
				}
			}
		}
		// 1을 찾아서 상하좌우 갯수 더하기.
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(arr[i][j]){
					sb.append(0);
				}else{
					sb.append(getAroundAreaCounts(j, i));
				}
			}
			sb.append("\n");
		}
	}

	private static void dfs(int x, int y, int[] areaCount){
		areaCount[0]++;
		visited[y][x] = true;
		areaCounts[y][x] = areaCount;

		for(int i = 0; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isInBound(nx, ny) && !visited[ny][nx] && arr[ny][nx]){
				dfs(nx, ny, areaCount);
			}
		}
	}

	private static int getAroundAreaCounts(int x, int y){
		int result = 1;
		List<int[]> list = new LinkedList<>();
		for(int i = 0; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isInBound(nx, ny) && arr[ny][nx]){
				result += areaCounts[ny][nx][0];

				for(int[] e: list){
					if(e == areaCounts[ny][nx]){
						result -= areaCounts[ny][nx][0];
						break;
					}
				}
				list.add(areaCounts[ny][nx]);
			}
		}
		return result % 10;
	}

	private static boolean isInBound(int x, int y){
		return 0 <= x && x < M && 0 <= y && y < N;
	}

	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		arr = new boolean[N][M];
		visited = new boolean[N][M];
		areaCounts = new int[N][M][];

		for(int i = 0; i < N; i++) {
			String l = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = l.charAt(j) == '0';
			}
		}
	}
}