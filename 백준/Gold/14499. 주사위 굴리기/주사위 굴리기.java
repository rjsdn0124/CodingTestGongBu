import java.io.*;

public class Main{
	private static int N, M, x, y, top = 0, east = 2, south = 4;
	private static int[][] map;
	private static int[] commands;
	private static int[] dice = {0, 0, 0, 0, 0, 0};
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		System.out.print(solution());
	}

	private static StringBuilder solution(){
		StringBuilder sb = new StringBuilder();

		// 명령을 따라 이동.
		for(int command: commands){
			// 이동하며 위 동쪽 남쪽 인덱스 갱신
			int nx = x + dx[command];
			int ny = y + dy[command];
			if(0 <= nx && nx < M && 0 <= ny && ny < N){
				// xy 갱신
				x = nx;
				y = ny;
				// 아래 인덱스의 값 및 지도의 값 갱신
				updateDice(command);
				// 윗 면 값 sb에 추가.
				sb.append(dice[top]).append("\n");
			}
		}
		return sb;
	}

	private static void updateDice(int command){
		if(command == 0){
			int temp = east;
			east = top;
			top = 5 - temp;
		}else if(command == 1){
			int temp = east;
			east = 5 - top;
			top = temp;
		}else if(command == 2){
			int temp = south;
			south = 5 - top;
			top = temp;
		}else{
			int temp = south;
			south = top;
			top = 5 - temp;
		}

		if(map[y][x] == 0){
			map[y][x] = dice[5 - top];
		}else{
			dice[5 - top] = map[y][x];
			map[y][x] = 0;
		}
	}

	private static void getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		y = Integer.parseInt(line[2]);
		x = Integer.parseInt(line[3]);
		int k = Integer.parseInt(line[4]);

		map = new int[N][M];
		commands = new int[k];

		for(int i = 0; i < N; i++) {
			String[] l = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(l[j]);
			}
		}

		line = br.readLine().split(" ");
		for(int i = 0; i < k; i++){
			commands[i] = Integer.parseInt(line[i]) - 1;
		}
	}
}