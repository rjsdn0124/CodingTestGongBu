import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, result = -1;
	private static boolean isPassedHole = false;
	private static int[] blue, red, hole;
	private static boolean[][] arr;
	private static int[] dx = new int[]{1, -1, 0, 0};
	private static int[] dy = new int[]{0, 0, 1, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(result);
	}

	private static void solution(){
		// dfs 돌면서 위아래 좌우 기울이기.
		bfs();
	}

	private static void bfs(){
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {blue[0], blue[1], red[0], red[1], -1, 0});
		while(!q.isEmpty()){
			int[] temp = q.poll();
			if(temp[5] == 10){
				return;
			}
			temp[5]++;
			for(int i = 0; i < 4; i++){
				blue[0] = temp[0];
				blue[1] = temp[1];
				red[0] = temp[2];
				red[1] = temp[3];
				// 기울였는데 둘 다 위치 안 바뀌면 패스.
				if(i != temp[4] && move(i)){
					// 빨강이 빠지면 같은 타이밍에 파랑 빠지는지 체크.
					if(isPassedHole){
						result = temp[5];
						return;
					}
					q.add(new int[] {blue[0], blue[1], red[0], red[1], i, temp[5]});
				}
			}
		}
	}

	private static boolean move(int direction){
		boolean isMoved = false;
		// x축 y축 둘 중 하나가 같은 위치일 때 먼저 도착하는 애 있으면 그만하기
		if(direction < 2){
			boolean isRedBig = (blue[0] < red[0] && direction % 2 == 0) || (blue[0] > red[0] && direction % 2 == 1);
			blue[0] += dx[direction];
			isMoved = arr[blue[1]][blue[0]];
			while(arr[blue[1]][blue[0]]){
				if(blue[0] == hole[0] && blue[1] == hole[1]){
					return false;
				}
				// blue 위치 업데이트.
				blue[0] += dx[direction];
			}
			blue[0] -= dx[direction];

			red[0] += dx[direction];
			isMoved |= arr[red[1]][red[0]];
			while(arr[red[1]][red[0]]){
				if(red[0] == hole[0] && red[1] == hole[1]){
					isPassedHole = true;
					return true;
				}
				// red 위치 업데이트.
				red[0] += dx[direction];
			}
			red[0] -= dx[direction];
			if(blue[0] == red[0] && blue[1] == red[1]){
				if(isRedBig){
					blue[0] -= dx[direction];
				}else{
					red[0] -= dx[direction];
				}
			}
		}else{
			boolean isRedBig = (blue[1] < red[1] && direction % 2 == 0) || (blue[1] > red[1] && direction % 2 == 1);
			blue[1] += dy[direction];
			isMoved = arr[blue[1]][blue[0]];
			while(arr[blue[1]][blue[0]]){
				if(blue[0] == hole[0] && blue[1] == hole[1]){
					return false;
				}
				// blue 위치 업데이트.
				blue[1] += dy[direction];
			}
			blue[1] -= dy[direction];

			red[1] += dy[direction];
			isMoved |= arr[red[1]][red[0]];
			while(arr[red[1]][red[0]]){
				if(red[0] == hole[0] && red[1] == hole[1]){
					isPassedHole = true;
					return true;
				}
				// red 위치 업데이트.
				red[1] += dy[direction];
			}
			red[1] -= dy[direction];
			if(blue[0] == red[0] && blue[1] == red[1]){
				if(isRedBig){
					blue[1] -= dy[direction];
				}else{
					red[1] -= dy[direction];
				}
			}
		}

		return isMoved;
	}

	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		arr = new boolean[N][M];

		for(int i = 0; i < N; i++) {
			String l = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = l.charAt(j) != '#';
				if(l.charAt(j) == 'R'){
					red = new int[]{j,i};
				}else if(l.charAt(j) == 'B'){
					blue = new int[]{j,i};
				}else if(l.charAt(j) == 'O'){
					hole = new int[]{j,i};
				}
			}
		}
	}
}