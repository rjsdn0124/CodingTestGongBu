import java.io.*;
import java.util.*;

public class Main{
	private static final int SIZE = 10;
	private static final int PAPER_SIZE = 5;
	private static int result = Integer.MAX_VALUE;
	private static boolean canConcile = false;


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] arr = getInput(br);
		int result = solution(arr);
		System.out.println(canConcile ? result : -1);
	}

	private static int solution(boolean[][] arr){
		// 색종이 사용 현황 저장 배열
		int[] papers = new int[PAPER_SIZE + 1];
		canPaperIn(arr, papers);

		return result;
	}

	private static void canPaperIn(boolean[][] arr, int[] papers){
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				if(arr[i][j]){
					for(int k = PAPER_SIZE; k > 0; k--){
						if(search(k, j, i, arr) && papers[k] < 5){
							updateArr(k, j, i, arr, false);
							papers[k]++;
							canPaperIn(arr, papers);
							updateArr(k, j, i, arr, true);
							papers[k]--;

						}
					}
					return;
				}
			}
		}
		int res = 0;
		for(int i = PAPER_SIZE; i > 0; i--){
			if(papers[i] > 5) {
				return;
			}
			res += papers[i];
		}
		canConcile = true;
		result = Math.min(result, res);
	}

	private static boolean search(int paperSize, int x, int y, boolean[][] arr){
		for(int i = 0; i < paperSize; i++){
			for(int j = 0; j < paperSize; j++){
				if(y + i >= SIZE || x + j >= SIZE || !arr[y + i][x + j]) return false;
			}
		}
		return true;
	}

	private static void updateArr(int paperSize, int x, int y, boolean[][] arr, boolean isOne){
		for(int i = 0; i < paperSize; i++){
			for(int j = 0; j < paperSize; j++){
				arr[y + i][x + j] = isOne;
			}
		}
	}

	private static boolean[][] getInput(BufferedReader br) throws IOException{
		// 입력.

		boolean[][] arr = new boolean[SIZE][SIZE];

		for(int i = 0; i < SIZE; i++){
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < SIZE; j++){
				arr[i][j] = input[j].equals("1");
			}
		}

		return arr;
	}
}