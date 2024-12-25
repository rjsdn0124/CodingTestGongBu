import java.io.*;

public class Main{
	private static int n;
	private static int m;
	private static int[][] arr;
	private static int result = Integer.MAX_VALUE;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] rotate = getInput(br);

		backtracking(rotate, new boolean[rotate.length], 0);

		System.out.println(result);
	}

	private static void backtracking(int[][] rotate, boolean[] visited, int depth){
		if(depth == rotate.length){
			int tempResult = getMinSum(arr);
			result = Math.min(result, tempResult);
		}

		depth++;

		for(int i = 0; i < rotate.length; i++){
			if(!visited[i]){
				visited[i] = true;
				rotateArr(rotate[i], true);
				backtracking(rotate, visited, depth);
				rotateArr(rotate[i], false);
				visited[i] = false;
			}
		}
	}

	private static void rotateArr(int[] rotate, boolean isClockWay){
		int x = rotate[1] - 1;
		int y = rotate[0] - 1;
		int size = rotate[2];
		int[] tdx = isClockWay ? dx : dy;
		int[] tdy = isClockWay ? dy : dx;

		for(int i = 1; i <= size; i++){
			int nx = x - i;
			int ny = y - i;
			int next = arr[ny][nx];

			for(int j = 0; j < 8 * i; j++){
				int now = next;
				int direction = j / (2 * i);
				ny += tdy[direction];
				nx += tdx[direction];
				next = arr[ny][nx];
				arr[ny][nx] = now;
			}
		}
	}

	private static int getMinSum(int[][] arr){
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++){
			int temp = 0;
			for(int j = 0; j < m; j++){
				temp += arr[i][j];
			}
			result = Math.min(result, temp);
		}

		return result;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		int k = Integer.parseInt(input[2]);
		arr = new int[n][m];

		for(int i = 0; i < n; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < m; j++){
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		int[][] rotate = new int[k][3];

		for(int i = 0; i < k; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < 3; j++){
				rotate[i][j] = Integer.parseInt(input[j]);
			}
		}

		return rotate;
	}
}