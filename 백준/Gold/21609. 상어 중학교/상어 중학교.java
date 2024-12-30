import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static final int VACANT = -2;
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
			int[] bestResult = new int[2];
			int[] xy = new int[2];
			boolean[][] visited = new boolean[n][n];

			// 배열 돌면서 체크.
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(!visited[i][j] && arr[i][j] > 0 && canGoNext(arr, j, i)){
						int[] res = bfs(j, i, arr, visited);
						if(res[0] > bestResult[0] || (res[0] == bestResult[0] && res[1] >= bestResult[1])){
							bestResult = res;
							xy[0] = j;
							xy[1] = i;
						}
					}
				}
			}

			if(bestResult[0] < 2){
				// 결과가 안 나오면 끝내기.
				break;
			}

			// 결과 저장하기.
			result += bestResult[0] * bestResult[0];

			// 다음 집합을 찾기 위해 처리.
			removeArr(xy[0], xy[1], arr);
			gravity(arr);
			arr = rotate(arr);
			gravity(arr);
		}
		return result;
	}

	public static int[] bfs(int x, int y, int[][] arr, boolean[][] visited){
		// bfs 돌면서 집합 찾기.
		Queue<Integer> q = new LinkedList<>();
		q.add(createMyInt(x, y));
		visited[y][x] = true;
		// 0 지났는지 체크하는 visited
		boolean[][] zVisited = new boolean[n][n];

		int color = arr[y][x];
		int count = 0;
		int rainbowCount = 0;

		while(!q.isEmpty()){
			int myXY = q.poll();
			x = myXY / 100;
			y = myXY % 100;
			count++;

			for(int i = 0 ; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(0 <= nx && nx < n && 0 <= ny && ny < n){
					if(!zVisited[ny][nx] && arr[ny][nx] == 0){
						zVisited[ny][nx] = true;
						q.add(createMyInt(nx, ny));
						rainbowCount++;
					} else if(!visited[ny][nx] && arr[ny][nx] == color){
						visited[ny][nx] = true;
						q.add(createMyInt(nx, ny));
					}
				}
			}
		}
		return new int[]{count, rainbowCount};
	}

	public static int createMyInt(int x, int y){
		// 크기 2 배열 대신 int로 xy 저장.
		return x * 100 + y;
	}

	public static boolean canGoNext(int[][] arr, int x, int y){
		// 2개 이상의 집합 생성 가능 체크.
		int color = arr[y][x];

		for(int i = 0 ; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(0 <= nx && nx < n && 0 <= ny && ny < n && (arr[ny][nx] == 0 || arr[ny][nx] == color)){
				return true;
			}
		}
		return false;
	}

	public static void removeArr(int x, int y, int[][] arr){
		// bfs 다시 돌면서 만들어진 집합 삭제 처리.
		boolean[][] visited = new boolean[n][n];
		Queue<Integer> q = new LinkedList<>();
		q.add(createMyInt(x, y));
		visited[y][x] = true;

		int color = arr[y][x];

		while(!q.isEmpty()){
			int myXY = q.poll();
			x = myXY / 100;
			y = myXY % 100;
			arr[y][x] = VACANT;

			for(int i = 0 ; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(0 <= nx && nx < n && 0 <= ny && ny < n && !visited[ny][nx] && (arr[ny][nx] == 0 || arr[ny][nx] == color)){
					visited[ny][nx] = true;
					q.add(createMyInt(nx, ny));
				}
			}
		}
	}

	private static void gravity(int[][] arr){
		// 중력 처리.
		for(int i = 0; i < n; i++){
			int ty = n - 1;
			for(int j = ty; j >= 0; j--) {
				if(arr[j][i] >= 0){
					arr[ty--][i] = arr[j][i];
					if(ty >= j){
						arr[j][i] = VACANT;
					}
				}else if(arr[j][i] == -1){
					ty = j - 1;
				}
			}
		}
	}

	private static int[][] rotate(int[][] arr){
		// 회전 처리.
		int[][] nArr = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				nArr[n - j - 1][i] = arr[i][j];
			}
		}
		return nArr;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		// 입력.
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);

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