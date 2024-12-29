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
		while(true){
			boolean isUpdated = false;
			boolean[][] visited = new boolean[n][n];

			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(!visited[i][j]){
						q.add(createMyXY(j, i));
						visited[i][j] = true;
						isUpdated |= bfs(q, arr[i][j].getValue(), arr, visited);
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

	public static boolean bfs(Queue<Integer> q, int prevVal, Section[][] arr, boolean[][] visited){
		Section s = new Section(prevVal);
		int sum = 0;
		int count = 0;

		while(!q.isEmpty()){
			int myXY = q.poll();
			int x = myXY / 100;
			int y = myXY % 100;
			int prev = arr[y][x].getValue();
			sum += prev;
			arr[y][x] = s;
			count++;

			for(int i = 0; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(0 <= nx && nx < n && 0 <= ny && ny < n && !visited[ny][nx]){
					int gap = Math.abs(prev - arr[ny][nx].getValue());
					if(min <= gap && gap <= max){
						q.add(createMyXY(nx, ny));
						visited[ny][nx] = true;
					}
				}
			}
		}

		if(count > 1){
			int avg = sum / count;
			s.setValue(avg);
			return true;
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