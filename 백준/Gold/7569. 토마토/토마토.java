import java.io.*;
import java.util.*;

public class Main{
	static int x;
	static int y;
	static int z;
	static int[] dx = new int[]{1 , -1};
	static int[] dy = new int[]{1 , -1};
	static int[] dz = new int[]{1 , -1};
	static int[][][] tomatoes;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Coord> q = getInput(br);

		int result = solution(q);

		System.out.println(result);
	}

	private static int solution(Queue<Coord> q){
		int days = -1;

		while(!q.isEmpty()) {
			days = updateTomatoes(tomatoes, q);
		}

		for(int i = 0; i < z; i++){
			for(int j = 0; j < y; j++){
				for(int k = 0; k < x; k++){
					if(tomatoes[i][j][k] == 0){
						return -1;
					}
				}
			}
		}

		return days;
	}

	private static int updateTomatoes(int[][][] tomatoes, Queue<Coord> q){
		Coord co = q.poll();
		int cx = co.getX();
		int cy = co.getY();
		int cz = co.getZ();

		for(int i = 0; i < 2; i++){
			int nz = cz + dz[i];
			int ny = cy + dy[i];
			int nx = cx + dx[i];

			if(0 <= nz && nz < z && tomatoes[nz][cy][cx] == 0){
				tomatoes[nz][cy][cx] = 1;
				q.add(new Coord(cx, cy, nz, co.getDays() + 1));
			}

			if(0 <= ny && ny < y && tomatoes[cz][ny][cx] == 0){
				tomatoes[cz][ny][cx] = 1;
				q.add(new Coord(cx, ny, cz, co.getDays() + 1));
			}

			if(0 <= nx && nx < x && tomatoes[cz][cy][nx] == 0){
				tomatoes[cz][cy][nx] = 1;
				q.add(new Coord(nx, cy, cz, co.getDays() + 1));
			}
		}
		return co.getDays();
	}

	private static Queue<Coord> getInput(BufferedReader br) throws IOException{
		String[] input = br.readLine().split(" ");
		x = Integer.parseInt(input[0]);
		y = Integer.parseInt(input[1]);
		z = Integer.parseInt(input[2]);
		tomatoes = new int[z][y][x];

		Queue<Coord> que = new LinkedList<>();

		for(int i = 0; i < z; i++){
			for(int j = 0; j < y; j++){
				input = br.readLine().split(" ");
				for(int k = 0; k < x; k++){
					tomatoes[i][j][k] = Integer.parseInt(input[k]);
					if(tomatoes[i][j][k] == 1){
						que.add(new Coord(k, j, i, 0));
					}
				}
			}
		}

		return que;
	}

	private static class Coord{
		int x;
		int y;
		int z;
		int days;

		public Coord(int x, int y, int z, int days) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.days = days;
		}

		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public int getZ(){
			return z;
		}
		public int getDays(){
			return days;
		}
	}
}