import java.io.*;
import java.util.*;

public class Main{
	private static int SIZE;
	private static final int GARO = 0;
	private static final int SERO = 1;
	private static final int SLASH = 2;
	private static final int[][] doType = {{1,0},{0,1},{1,1}};
	private static int result = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);
		dfs(0, 0, GARO, arr);
		System.out.println(result);
	}

	private static void dfs(int x, int y, int type, int[][] arr){
		// 시작
		x += doType[type][0];
		y += doType[type][1];
		if(isUnvalid(x, y, type, arr)){
			return;
		}
		if(x == SIZE - 1 && y == SIZE - 1){
			result++;
			return;
		}
		// 현재 파이프가 배열 안에 있으면
		dfs(x, y, SLASH, arr);

		if(type == SLASH){
			dfs(x, y, GARO, arr);
			dfs(x, y, SERO, arr);
		}else{
			dfs(x, y, type, arr);
		}
	}

	private static boolean isUnvalid(int x, int y, int type, int[][] arr){
		boolean result = false;
		if(x >= SIZE || y >= SIZE){
			return true;
		}
		result |= arr[y][x] == 1;
		if(type == SLASH){
			result |= arr[y - 1][x] == 1;
			result |= arr[y][x - 1] == 1;
		}
		return result;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		SIZE = Integer.parseInt(br.readLine());
		int[][] arr = new int[SIZE][SIZE];

		for(int i = 0; i < SIZE; i++){
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < SIZE; j++){
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		return arr;
	}
}
