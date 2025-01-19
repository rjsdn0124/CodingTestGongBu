import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, K, result = 0;
	private static boolean[][] arr;
	private static boolean[][][] stickers;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.println(result);
	}

	private static void solution(){
		// 완전탐색.
		// 노트북 우선 위부터 아래로 완탐. 모든 스티커를 붙일 때마다 해주어야할듯.
		for(boolean[][] sticker : stickers) {
			// 못 붙이면 90도 돌리기.
			for(int turn = 0; turn < 4; turn++) {
				if(turn > 0){
					sticker = turnSticker(sticker);
				}
				if(searchArr(sticker)){
					// 붙이고 해당 스티커의 칸 개수 더해주기.
					break;
				}
			}
		}
	}

	private static boolean searchArr(boolean[][] sticker){
		for (int i = 0; i <= N - sticker.length; i++) {
			for (int j = 0; j <= M - sticker[0].length; j++) {
				// 완탐하면서 노트북과 스티커 배열 비교해서 모든 true 원소의 값이 다르다면 붙일 수 있는거.
				if (trySticker(sticker, j, i)) {
					updateArr(sticker, j, i);
					return true;
				}
			}
		}
		return false;
	}

	private static boolean trySticker(boolean[][] sticker, int x, int y){
		for(int i = 0; i < sticker.length; i++){
			for(int j = 0; j < sticker[0].length; j++){
				if(sticker[i][j] && arr[y + i][x + j]){
					return false;
				}
			}
		}
		return true;
	}

	private static boolean[][] turnSticker(boolean[][] sticker){
		int xl = sticker[0].length;
		int yl = sticker.length;
		boolean[][] newSticker = new boolean[xl][yl];

		for(int i = 0; i < xl; i++){
			for(int j = 0; j < yl; j++){
				newSticker[i][j] = sticker[yl - j - 1][i];
			}
		}

		return newSticker;
	}

	private static void updateArr(boolean[][] sticker, int x, int y){
		for(int i = 0; i < sticker.length; i++){
			for(int j = 0; j < sticker[i].length; j++){
				if(sticker[i][j]){
					arr[y + i][x + j] = true;
					result++;
				}
			}
		}
	}

	private static void getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);

		arr = new boolean[N][M];
		stickers = new boolean[K][][];

		for(int i = 0; i < K; i++){
			line = br.readLine().split(" ");
			int y = Integer.parseInt(line[0]);
			int x = Integer.parseInt(line[1]);
			stickers[i] = new boolean[y][x];
			for(int j = 0; j < y; j++){
				line = br.readLine().split(" ");
				for(int k = 0; k < x; k++){
					stickers[i][j][k] = line[k].equals("1");
				}
			}
		}
	}
}