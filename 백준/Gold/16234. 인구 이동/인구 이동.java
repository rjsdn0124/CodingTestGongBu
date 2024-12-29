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
		Section[][] arr = getInput(br);
		int result = solution(arr);
		System.out.println(result);
	}

	public static int solution(Section[][] arr){
		int result = 0;
		Queue<Integer> q = new LinkedList<>();
		boolean[][] visited;
		while(true){
			boolean isUpdated = false;
			visited = new boolean[n][n];

			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(!visited[i][j]){
						visited[i][j] = true;
						boolean canUpdate = false;
						for(int k = 0; k < 2; k++){
							canUpdate |= canGoNext(j + dx[k], i + dy[k], arr[i][j].getValue(), arr, visited);
						}
						if(canUpdate){
							q.add(createMyXY(j, i));
							bfs(q, arr, visited);
							isUpdated = true;
						}
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

	public static void bfs(Queue<Integer> q, Section[][] arr, boolean[][] visited){
		Section s = new Section(0);
		int sum = 0;
		int count = 0;

		while(!q.isEmpty()){
			int myXY = q.poll();
			int x = myXY / 100;
			int y = myXY % 100;
			int prev = arr[y][x].getValue();
			arr[y][x] = s;
			sum += prev;
			count++;

			for(int i = 0; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(canGoNext(nx, ny, prev, arr, visited)){
					q.add(createMyXY(nx, ny));
					visited[ny][nx] = true;
				}
			}
		}

		int avg = sum / count;
		s.setValue(avg);
	}

	private static boolean canGoNext(int nx, int ny, int prev, Section[][] arr, boolean[][] visited){
		if(0 <= nx && nx < n && 0 <= ny && ny < n && !visited[ny][nx]){
			int gap = Math.abs(prev - arr[ny][nx].getValue());
			return min <= gap && gap <= max;
		}
		return false;
	}

	private static int createMyXY(int x, int y){
		return x * 100 + y;
	}

	private static Section[][] getInput(BufferedReader br) throws IOException{
		// 입력.
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		min = Integer.parseInt(input[1]);
		max = Integer.parseInt(input[2]);

		Section[][] arr = new Section[n][n];

		for(int i = 0; i < n; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < n; j++){
				arr[i][j] = new Section(Integer.parseInt(input[j]));
			}
		}
		return arr;
	}
}

class Section{
	int value;
	public Section(int value){
		this.value = value;
	}

	public int getValue(){
		return this.value;
	}

	public void setValue(int value){
		this.value = value;
	}
}