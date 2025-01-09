import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static int M;
	private static int[][] unitLoc;
	private static Unit[] units;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);
		int result = solution(arr);
		System.out.println(result);
	}

	private static int solution(int[][] arr){
		int x, y, turn = 0;
		Unit u;

		while(true){
			turn++;
			if(turn > 1000) return -1;
			for(int i = 1; i <= M; i++){
				// 돌면서 말 번호대로 옮기기.
				u = units[i];
				if(u != null){
					// 이전 위치 비우기
					unitLoc[u.y][u.x] = 0;
					units[i] = null;
					// 말 옮기기
					u.move(arr, N);
					x = u.x;
					y = u.y;
					// 이후 위치 할당.
					if(unitLoc[y][x] != 0){
						// 옮기려는 칸에 있따면 합치고 결과 체크.
						if(units[unitLoc[y][x]].assemble(u)){
							return turn;
						}
					}else{
						// 옮기려는 칸에 없다면 그냥 배열 갱신.
						unitLoc[y][x] = u.num;
						units[u.num] = u;
					}
				}
			}
		}
	}

	private static int[][] getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		int[][] arr = new int[N][N];
		unitLoc = new int[N][N];

		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for(int j = 0; j < N; j++){
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}

		units = new Unit[M + 1];
		int x, y, dir;
		for(int i = 1; i <= M; i++){
			line = br.readLine().split(" ");
			y = Integer.parseInt(line[0]) - 1;
			x = Integer.parseInt(line[1]) - 1;
			dir = Integer.parseInt(line[2]) - 1;
			unitLoc[y][x] = i;
			units[i] = new Unit(x, y, dir, i);
		}

		return arr;
	}
}

class Unit{
	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, -1, 1};
	int x, y, headDir, tailDir, num, tailNum, size;

	public Unit(int x, int y, int headDir, int num){
		this.x = x;
		this.y = y;
		this.headDir = headDir;
		this.tailDir = headDir;
		this.num = num;
		this.tailNum = num;
		this.size = 1;
	}

	public void move(int[][] arr, int n){
		int nx = x + dx[headDir];
		int ny = y + dy[headDir];
		if(!(0 <= nx && nx < n && 0 <= ny && ny < n) || arr[ny][nx] == 2){
			// 파랗거나 배열 밖일 때 처리.
			// 방향 전환 후 다시 이동.
			negativeDir();
			nx = x + dx[headDir];
			ny = y + dy[headDir];
			if(!(0 <= nx && nx < n && 0 <= ny && ny < n) || arr[ny][nx] == 2){
				return;
			}
		}
		// 가장 아래 말의 방향과 번호 -> 가장 위 말의 방향과 번호 바꾸기
		if(arr[ny][nx] == 1){
			int temp = headDir;
			headDir = tailDir;
			tailDir = temp;
			temp = num;
			num = tailNum;
			tailNum = temp;
		}
		x = nx;
		y = ny;
	}

	public boolean assemble(Unit unit){
		// 위로 쌓기.
		this.tailDir = unit.tailDir;
		this.tailNum = unit.tailNum;
		this.size += unit.size;
		return this.size >= 4;
	}

	private void negativeDir(){
		// 방향 바꿔주기
		int start = 0;
		if(headDir >= 2){
			start = 2;
		}
		headDir = start + (headDir + 1) % 2;
		if(num == tailNum) tailDir = headDir;
	}
}