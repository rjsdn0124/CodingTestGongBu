import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, areaCount;
	private static StringBuilder sb = new StringBuilder();
	private static int[][] arr;
	private static boolean[][] visited;
	private static Queue<int[]> q = new LinkedList<>();
	private static Queue<int[]> walls = new LinkedList<>();
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
				if(!visited[i][j] && arr[i][j] == 0){
					// 영역 갯수
					areaCount = 0;
					q.add(new int[]{j, i});
					visited[i][j] = true;
					bfs();
					// 1 자리에 결과 업데이트
					getAroundAreaCounts();
				}
			}
		}
		// 출력
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				sb.append(arr[i][j] % 10);
			}
			sb.append("\n");
		}
	}

	private static void bfs(){
		while(!q.isEmpty()){
			int[] xy = q.poll();
			areaCount++;

			for(int i = 0; i < 4; i++){
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];
				if(0 <= nx && nx < M && 0 <= ny && ny < N && !visited[ny][nx]){
					if(arr[ny][nx] == 0){
						q.add(new int[]{nx, ny});
						visited[ny][nx] = true;
					}else{
						walls.add(new int[]{nx, ny});
						visited[ny][nx] = true;
					}
				}
			}
		}

	}

	private static void getAroundAreaCounts(){
		while(!walls.isEmpty()) {
			int[] xy = walls.poll();
			arr[xy[1]][xy[0]] += areaCount;
			visited[xy[1]][xy[0]] = false;
		}
	}
	
	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		arr = new int[N][M];
		visited = new boolean[N][M];

		for(int i = 0; i < N; i++) {
			String l = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = l.charAt(j) - '0';
			}
		}
	}
}