import java.io.*;
import java.util.*;

public class Main{
	private static int Y = 12, X  = 6;
	private static int[][] arr;
	private static int[] arrMeta;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		int result = solution();
		System.out.println(result);
	}

	private static int solution(){
		int result = 0;
		// list에 갱신된 가장 아래 좌표 넣기. 옛날에도 안 터진 친구들은 영영 안 터질 친구들일거기 때문에 체크 안하기.
		boolean[][] visited;

		while(true){
			boolean isChained = false;
			visited = new boolean[Y][X];
			// 좌표들 dfs돌면서 같은 색 친구들 찾기.
			for(int j = 0; j < X; j++){
				for(int i = 0; i < arrMeta[j]; i++){
					if (!visited[i][j] && dfs(j, i, visited) >= 4) {
						// 찾는다면 해당 칸 비워주기.
						updateDfs(j, i, arr[i][j]);
						isChained = true;
					}
				}
			}
			if(isChained) result++;
			if(!gravity()){
				break;
			}
		}
		return result;
	}

	private static int dfs(int x, int y, boolean[][] visited){
		visited[y][x] = true;
		int count = 1;
		for(int i = 0; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(0 <= nx && nx < X && 0 <= ny && ny < Y && !visited[ny][nx] && arr[y][x] == arr[ny][nx]){
				count += dfs(nx, ny, visited);
			}
		}
		return count;
	}

	private static void updateDfs(int x, int y, int target){
		arr[y][x] = 0;
		for(int i = 0; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(0 <= nx && nx < X && 0 <= ny && ny < Y && target == arr[ny][nx]){
				updateDfs(nx, ny, target);
			}
		}
	}

	private static boolean gravity(){
		boolean isUpdated = false;

		for(int i = 0; i < X; i++){
			int newInd = 0;
			for(int j = 0; j < arrMeta[i]; j++){
				if(arr[j][i] > 0){
					isUpdated |= newInd != j;
					arr[newInd++][i] = arr[j][i];
				}
			}
			arrMeta[i] = newInd;
		}

		return isUpdated;
	}
	private static void getInput(BufferedReader br) throws IOException {
		arr = new int[Y][X];
		arrMeta = new int[X];
		Arrays.fill(arrMeta, Y);

		for(int i = Y - 1; i >= 0; i--) {
			String line = br.readLine();
			for (int j = 0; j < X; j++) {
				char prop = line.charAt(j);

				if (prop == 'R') {
					arr[i][j] = 1;
				}else if(prop == 'G'){
					arr[i][j] = 2;
				}else if(prop == 'B'){
					arr[i][j] = 3;
				}else if(prop == 'P'){
					arr[i][j] = 4;
				}else if(prop == 'Y'){
					arr[i][j] = 5;
				}else{
					arrMeta[j]--;
				}
			}
		}
	}
}