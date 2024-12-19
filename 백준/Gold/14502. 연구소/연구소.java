import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int m;
	static int[][] lab;
	static int wallCount = 3;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Coord> q = getInput(br);

		int result = createWallsCases(q);

		System.out.println(result);

		br.close();
	}

	private static int solution(int totalCount, List<Coord> q, int[][] lab){
		int[] dx = new int[] { 1, -1 };
		int[] dy = new int[] { 1, -1 };

		Queue<Coord> coords = new LinkedList<>();
		int viruses = 0;

		for(Coord coord: q){
			coords.add(coord);
		}

		while(!coords.isEmpty()){
			Coord coord = coords.poll();

			int cx = coord.getX();
			int cy = coord.getY();
			viruses++;

			for(int i = 0; i < 2; i++){
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if(0 <= ny && ny < n && lab[ny][cx] == 0){
					lab[ny][cx] = 2;
					coords.add(new Coord(cx, ny));
				}
				if(0 <= nx && nx < m && lab[cy][nx] == 0){
					lab[cy][nx] = 2;
					coords.add(new Coord(nx, cy));
				}
			}
		}

		return totalCount - wallCount - viruses;
	}

	private static int createWallsCases(List<Coord> q){
		int totalCount = n * m;
		int result = 0;
		int[][] tempLab = new int[n][m];

		for(int i = 0; i < totalCount; i++){
			for(int j = i + 1; j < totalCount && lab[i / m][i % m] == 0; j++){
				for(int k = j + 1; k < totalCount && lab[j / m][j % m] == 0; k++){
					if(lab[k / m][k % m] != 0){
						continue;
					}
					for(int l = 0; l < n; l++){
						tempLab[l] = Arrays.copyOf(lab[l], m);
					}
					tempLab[i / m][i % m] = 1;
					tempLab[j / m][j % m] = 1;
					tempLab[k / m][k % m] = 1;
					result = Math.max(result, solution(totalCount, q, tempLab));
				}
			}
		}
		return result;
	}

	private static List<Coord> getInput(BufferedReader br) throws IOException{
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);

		lab = new int[n][m];

		List<Coord> q = new LinkedList<>();
		for(int i = 0; i < n; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < m; j++){
				lab[i][j] = Integer.parseInt(input[j]);
				if(lab[i][j] == 2){
					q.add(new Coord(j, i));
				} else if(lab[i][j] == 1){
					wallCount++;
				}
			}
		}

		return q;
	}

	private static class Coord{
		private int x;
		private int y;

		public Coord(int x, int y){
			this.x = x;
			this.y = y;
		}

		public int getX(){
			return this.x;
		}

		public int getY(){
			return this.y;
		}
	}
}