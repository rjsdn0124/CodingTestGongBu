import java.io.*;
import java.util.*;

public class Main{
	private static int[] dirX = new int[]{0,-1,-1,-1,0,1,1,1};
	private static int[] dirY = new int[]{-1,-1,0,1,1,1,0,-1};
	private static final int SIZE = 4;
	private final static int[][] sea = new int[SIZE][SIZE];
	private final static Fish[] fishes = new Fish[SIZE * SIZE + 1];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		getInput(br);

		Shark shark = new Shark(0, 0, fishes[sea[0][0]].direction);
		int result = dfs(shark, sea, fishes);

		System.out.println(result);
		br.close();
	}

	private static int dfs(Shark shark, int[][] sea, Fish[] fishes){
		// 상어 움직이고 물고기 움직이고.
		// 상어가 움직이는 경우마다 물고기들을 다 움직여줘야한다...?
		// 물고기 이전 위치를 어떻게..?

		// 먹고
		int result = shark.eat(sea, fishes);
		// 물고기 위치 변경
		doFish(sea, fishes);
		// 상어 이동 가능 dfs. 배열들 copy
		int temp = 0;
		for(Shark s: sharkCanEat(shark, sea, fishes)){
			int[][] newSea = new int[SIZE][SIZE];
			for(int i = 0; i < SIZE; i++){
				newSea[i] = Arrays.copyOf(sea[i], 4);
			}
			Fish[] newFishes = new Fish[SIZE * SIZE + 1];
			for(int i = 1; i <= SIZE * SIZE; i++){
				Fish fish = fishes[i];
				newFishes[i] = fish == null ? null : new Fish(fish.x, fish.y, fish.direction);
			}
			temp = Math.max(temp, dfs(s, newSea, newFishes));
		}
		return result + temp;
	}

	private static List<Shark> sharkCanEat(Shark shark, int[][] sea, Fish[] fishes){
		List<Shark> ways = new LinkedList<>();
		int dir = shark.getDirection();
		int x = shark.getX();
		int y = shark.getY();
		sea[y][x] = 0;
		while(0 <= x && x < SIZE && 0 <= y && y < SIZE){
			if(sea[y][x] > 0) {
				Fish fish = fishes[sea[y][x]];
				ways.add(new Shark(fish.x, fish.y, fish.direction));
			}
			x += dirX[dir - 1];
			y += dirY[dir - 1];
		}

		return ways;
	}

	private static void doFish(int[][] sea, Fish[] fishes){
		for(Fish fish: fishes){
			if(fish != null){
				fish.move(sea, fishes);
			}
		}
	}

	private static void getInput(BufferedReader br) throws IOException{
		for(int i = 0; i < SIZE; i++){
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < SIZE; j++){
				int fishNum = Integer.parseInt(input[2 * j]);
				fishes[fishNum] = new Fish(j, i, Integer.parseInt(input[2 * j + 1]));
				sea[i][j] = fishNum;
			}
		}
	}

	private static class Fish{
		private int x;
		private int y;
		private int direction;

		// 물고기 하는 일. 움직이기.
		public Fish(int x, int y, int direction){
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		public void move(int[][] sea, Fish[] fishes){
			int prevX = x;
			int prevY = y;
			while(true){
				int nx = x + dirX[direction - 1];
				int ny = y + dirY[direction - 1];
				if(0 <= nx && nx < SIZE && 0 <= ny && ny < SIZE && sea[ny][nx] > -1) {
					this.x = nx;
					this.y = ny;
					break;
				}
				turn();
			}

			int me = sea[prevY][prevX];
			sea[prevY][prevX] = sea[y][x];
			if(sea[y][x] > 0){
				fishes[sea[prevY][prevX]].setXY(prevX, prevY);
			}
			sea[y][x] = me;
		}

		public int getX(){
			return this.x;
		}

		public int getY(){
			return this.y;
		}

		public int getDirection(){
			return this.direction;
		}

		public void setXY(int x, int y){
			this.x = x;
			this.y = y;
		}

		private void turn(){
			direction = direction % 8 + 1;
		}
	}

	private static class Shark extends Fish{
		// 상어 하는 일. 움직이고 먹고 변신하기.
		public Shark(int x, int y, int direction){
			super(x, y, direction);
		}

		public int eat(int[][] sea, Fish[] fishes){
			int value = sea[super.y][super.x];
			sea[super.y][super.x] = -1;
			fishes[value] = null;
			return value;
		}
	}
}
