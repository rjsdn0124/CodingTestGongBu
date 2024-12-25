import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static int m;
	private static int[][] arr;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] rotate = getInput(br);

		backtracking(arr, rotate, new boolean[rotate.length], 0);

		System.out.println(result);
	}

	private static void backtracking(int[][] arr, int[][] rotate, boolean[] visited, int depth){
		if(depth == rotate.length){
			int tempResult = getMinSum(arr);
			result = Math.min(result, tempResult);
		}

		depth++;

		for(int i = 0; i < rotate.length; i++){
			if(!visited[i]){
				visited[i] = true;
				int[][] tempArr = rotateArr(rotate[i], arr);
				backtracking(tempArr, rotate, visited, depth);
				visited[i] = false;
			}
		}
	}

	private static int[][] rotateArr(int[] rotate, int[][] arr){
		int[][] newArr = new int[n][m];

		for(int i = 0; i < n; i++){
			newArr[i] = Arrays.copyOf(arr[i], m);
		}

		int x = rotate[1] - 1;
		int y = rotate[0] - 1;
		int size = rotate[2];

		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};

		for(int i = 1; i <= size; i++){
			int nx = x - i;
			int ny = y - i;
			int next = newArr[ny][nx];

			for(int j = 0; j < 8 * i; j++){
				int now = next;
				int direction = j / (2 * i);
				ny += dy[direction];
				nx += dx[direction];
				next = newArr[ny][nx];
				newArr[ny][nx] = now;
			}
		}
		return newArr;
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

