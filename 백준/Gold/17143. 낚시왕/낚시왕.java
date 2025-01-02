import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;
	private static int SHARK_COUNT;
	private static Shark[] sharks;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);

		int result = solution(arr);
		System.out.println(result);
	}

	private static int solution(int[][] arr){
		int result = 0;
		// 0부터 x까지 낚시 반복.
		// 낚시꾼 이동하기
		for(int i = 0; i < N; i++){
			// 낚고
			result += fishing(i, arr);
			// 상어 이동하고
			arr = moving();
		}

		return result;
	}

	private static int fishing(int x, int[][] arr){
		for(int i = 0; i < M; i++){
			if(arr[x][i] > 0){
				Shark shark = sharks[arr[x][i]];
				shark.isDead = true;
				return shark.size;
			}
		}

		return 0;
	}

	private static int[][] moving() {
		int[][] nArr = new int[N][M];

		for(int i = 1; i <= SHARK_COUNT; i++){
			Shark shark = sharks[i];
			if(!shark.isDead){
				shark.move();
				int x = shark.x;
				int y = shark.y;

				if(nArr[y][x] == 0){
					nArr[y][x] = i;
				} else if(sharks[nArr[y][x]].size < shark.size){
					sharks[nArr[y][x]].isDead = true;
					nArr[y][x] = i;
				} else{
					shark.isDead = true;
				}
			}
		}

		return nArr;
	}

	private static int[][] getInput(BufferedReader br) throws IOException {
		// 입력.
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		SHARK_COUNT = Integer.parseInt(input[2]);

		int[][] arr = new int[N][M];
		sharks = new Shark[SHARK_COUNT + 1];

		for(int i = 1; i <= SHARK_COUNT; i++){
			input = br.readLine().split(" ");

			sharks[i] = new Shark(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1,Integer.parseInt(input[2]),Integer.parseInt(input[3]),Integer.parseInt(input[4]));

			arr[sharks[i].y][sharks[i].x] = i;
		}
		return arr;
	}

	private static class Shark{
		int x;
		int y;
		int size;
		int speed;
		boolean isOnY;
		boolean isDead;

		public Shark(int x, int y, int speed, int direction, int size){
			this.x = x;
			this.y = y;
			this.size = size;
			if(direction > 2){
				isOnY = true;
				speed %= (2 * (N - 1));
			}else{
				isOnY = false;
				speed %= (2 * (M - 1));
			}
			if(direction == 1 || direction == 4){
				speed = -speed;
			}
			this.speed = speed;
		}

		public void move(){
			int target = isOnY ? y : x;
			int max = isOnY ? N : M;
			target += speed;

			while(true){
				if(target >= 0 && target < max){
					break;
				}else if(target < 0){
					target = -target;
				}else{
					target = 2 * max - 2 - target;
				}
				speed = -speed;
			}
			if(isOnY){
				y = target;
			}else{
				x = target;
			}
		}
	}
}
