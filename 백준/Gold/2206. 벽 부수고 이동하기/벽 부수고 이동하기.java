import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] arr = getInput(br);
		int  result = solution(arr);
		System.out.println(result);
	}

	private static int solution(boolean[][] arr){
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		int[][] visited = new int[N][M];
		Queue<Location> q = new LinkedList<>();
		q.add(new Location(0, 0, 1, false));
		visited[0][0] = 2;

		while(!q.isEmpty()){
			Location loc = q.poll();

			if(loc.x == M - 1 && loc.y == N - 1){
				return loc.count;
			}

			for(int i = 0; i < 4; i++){
				int nx = loc.x + dx[i];
				int ny = loc.y + dy[i];

				if(0 <= nx && nx < M && 0 <= ny && ny < N){
					if(arr[ny][nx]){
						// 벽이 아닐 때
						if(visited[ny][nx] == 0){
							// 내가 처음 온거니까 무조건 넣어
							q.add(new Location(nx, ny, loc.count + 1, loc.isPassedWall));
							visited[ny][nx] = loc.isPassedWall ? 1 : 2;
						}else if(visited[ny][nx] == 1 && !loc.isPassedWall){
							// visited에 먼저 벽 지난 친구가 있다면 벽을 안 지난 친구면 들어가
							q.add(new Location(nx, ny, loc.count + 1, false));
							visited[ny][nx] = 2;
						}
						// 내가 벽 지나왔다면 x
						// 이미 벽을 하나도 안 거친 객체가 있으면 x
					}else{
						// 벽일 때
						if(!loc.isPassedWall && visited[ny][nx] == 0){
							// 벽을 안 지나왔으면 visited 이미 없으면 o하고 isPassedWall 업데이트
							q.add(new Location(nx, ny, loc.count + 1, true));
							visited[ny][nx] = 1;
						}
						// 이미 벽을 지나왔으면 x
					}
				}
			}
		}

		return -1;
	}

	private static boolean[][] getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		boolean[][] arr = new boolean[N][M];

		for(int i = 0 ; i < N; i++){
			char[] chars = br.readLine().toCharArray();
			for(int j = 0; j < M; j++){
				arr[i][j] = chars[j] == '0';
			}
		}
		return arr;
	}
}

class Location{
	int x, y, count;
	boolean isPassedWall;

	public Location(int x, int y, int count, boolean isPassedWall){
		this.x = x;
		this.y = y;
		this.count = count;
		this.isPassedWall = isPassedWall;
	}
}