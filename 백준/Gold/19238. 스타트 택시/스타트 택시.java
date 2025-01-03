import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int m;
	private static int gas;
	private static int[] taxi;
	private static Client[] clients;
	private static int[] dx = new int[]{0 , -1, 1, 0};
	private static int[] dy = new int[]{-1 , 0, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);

		System.out.println(solution(arr) ? gas : -1);
	}

	private static boolean solution(int[][] arr){
		while(m > 0){
			// bfs로 택시 승객 찾기
			// bfs로 승객 태우고 이동시키기.
			if(findClient(arr)){
				// 이동시킨 결과 처리.
				int clientNum = arr[taxi[0]][taxi[1]];
				arr[taxi[0]][taxi[1]] = 0;
				if(!move(arr, clients[clientNum])){
					return false;
				}
				m--;
			}else{
				return false;
			}
		}
		return true;
	}

	private static boolean findClient(int[][] arr){
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { taxi[0], taxi[1], 0});
		visited[taxi[0]][taxi[1]] = true;

		while(!q.isEmpty()){
			int[] yxd = q.poll();
			int y = yxd[0];
			int x = yxd[1];
			int d = yxd[2];

			if(arr[y][x] > 0){
				while(!q.isEmpty()){
					yxd = q.poll();
					if(yxd[2] > d){
						break;
					}
					int ty = yxd[0];
					int tx = yxd[1];
					if(arr[ty][tx] > 0 && (ty < y || (ty == y && tx < x))){
						x = tx;
						y = ty;
					}
				}
				taxi[0] = y;
				taxi[1] = x;
				gas -= d;
				return true;
			}else if(d > gas){
				return false;
			}
			d++;
			for(int i = 0; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(0 <= nx && nx < N && 0 <= ny && ny < N && !visited[ny][nx] && arr[ny][nx] >= 0){
					q.add(new int[]{ny, nx, d});
					visited[ny][nx] = true;
				}
			}
		}
		return false;
	}


	private static boolean move(int[][] arr, Client client){
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { taxi[0], taxi[1], 0});
		visited[taxi[0]][taxi[1]] = true;
		int[] endPoint = client.endPoint;

		while(!q.isEmpty()){
			int[] yxd = q.poll();
			int y = yxd[0];
			int x = yxd[1];
			int d = yxd[2];

			if(endPoint[0] == y && endPoint[1] == x){
				taxi[0] = y;
				taxi[1] = x;
				gas += d;
				return true;
			}else if(d > gas){
				return false;
			}
			d++;
			for(int i = 0; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(0 <= nx && nx < N && 0 <= ny && ny < N && !visited[ny][nx] && arr[ny][nx] >= 0){
					q.add(new int[]{ny, nx, d});
					visited[ny][nx] = true;
				}
			}
		}
		return false;
	}

	private static int[][] getInput(BufferedReader br) throws IOException {
		// 입력.
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		gas = Integer.parseInt(input[2]);

		int[][] arr = new int[N][N];

		for(int i = 0; i < N; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++){
				arr[i][j] = -Integer.parseInt(input[j]);
			}
		}

		taxi = new int[2];
		input = br.readLine().split(" ");
		for(int i = 0; i < 2; i++){
			taxi[i] = Integer.parseInt(input[i]) - 1;
		}

		clients = new Client[m + 1];
		for(int i = 1; i <= m; i++){
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[1]) - 1;
			int y = Integer.parseInt(input[0]) - 1;
			clients[i] = new Client(Integer.parseInt(input[2]) - 1, Integer.parseInt(input[3]) - 1);
			arr[y][x] = i;
		}

		return arr;
	}

	private static class Client{
		int[] endPoint;

		public Client(int ey, int ex){
			this.endPoint = new int[]{ey, ex};
		}
	}
}