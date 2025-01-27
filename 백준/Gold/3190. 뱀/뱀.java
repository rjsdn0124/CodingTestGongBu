import java.io.*;

public class Main{
	private static int N, x = 0, y = 0, tailX = 0, tailY = 0, turn = 0, direction = 0;
	private static boolean[][] arr;
	private static int[][] snake;
	private static int[][] reqs;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(turn);
	}

	private static void solution(){
		for(int[] req: reqs) {
			while(turn < req[0]) {
				// 이동
				moveHead();
				if (0 > x || x >= N || 0 > y || y >= N || snake[y][x] > 0) {
					return;
				}
				// 꼬리 갱신.
				//  이동한 자리 사과 있으면 꼬리 유지
				//  없으면 꼬리 갱신.
				updateTail();
			}
			// 이동하면서 다음 방향 snake에 저장.
			updateDirection(req);
		}
	}

	private static void moveHead(){
		snake[y][x] = direction + 1;
		x += dx[direction];
		y += dy[direction];
		turn++;
	}

	private static void updateDirection(int[] req){
		direction += req[1];
		direction %= 4;
	}

	private static void updateTail(){
		if(!arr[y][x]){
			int dir = snake[tailY][tailX] - 1;
			snake[tailY][tailX] = 0;
			tailX += dx[dir];
			tailY += dy[dir];
		}else{
			arr[y][x] = false;
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		arr = new boolean[N][N];
		snake = new int[N][N];

		for(int i = 0; i < k; i++) {
			String[] l = br.readLine().split(" ");
			arr[Integer.parseInt(l[0]) - 1][Integer.parseInt(l[1]) - 1] = true;
		}

		int m = Integer.parseInt(br.readLine());
		reqs = new int[m + 1][2];
		for(int i = 0; i < m; i++){
			String[] l = br.readLine().split(" ");
			reqs[i] = new int[]{Integer.parseInt(l[0]), l[1].equals("D") ? 1 : 3};
		}
		reqs[m] = new int[]{20000, 0};
	}
}