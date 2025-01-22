import java.io.*;
import java.util.*;

public class Main{
	private static int N = 5;
	private static boolean[][][] arr = new boolean[N][N][N];
	private static boolean[][][] updatedArr = new boolean[N][N][N];
	private static int result = Integer.MAX_VALUE;
	private static boolean canMake = false;
	private static boolean[] plateVisited = new boolean[N];
	private static int[][] dxyz = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
	private static int[][] startPoints = {{0,0}, {4,0}, {0,4}, {4,4}};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution(0);
		System.out.print(canMake ? result : -1);
	}

	private static void solution(int zInd){
		if(zInd == 5){
			turnPlateCases(0);
			return;
		}
		for(int i = 0; i < N; i++){
			if(!plateVisited[i]){
				updatedArr[zInd] = arr[i];
				plateVisited[i] = true;
				solution(zInd + 1);
				plateVisited[i] = false;
			}
		}
	}

	private static void turnPlateCases(int zind){
		if(zind == 5){
			for(int i = 0; i < 4; i++){
				if(updatedArr[0][startPoints[i][1]][startPoints[i][0]]){
					bfs(startPoints[i][0], startPoints[i][1]);
				}
			}
			return;
		}

		for(int i = 0; i < 4; i++){
			turnPlateCases(zind + 1);
			turnPlate(zind);
		}
	}

	private static void turnPlate(int ind){
		boolean[][] newArr = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				newArr[4 - j][i] = updatedArr[ind][i][j];
			}
		}
		updatedArr[ind] = newArr;
	}

	private static void bfs(int x, int y){
		int epx = N - 1 - x;
		int epy = N - 1 - y;
		int epz = N - 1;

		if(updatedArr[epz][epy][epx]){
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {x, y, 0, 0});

			boolean[][][] visited = new boolean[N][N][N];
			visited[0][y][x] = true;

			while(!q.isEmpty()){
				int[] xyzc = q.poll();

				if(xyzc[0] == epx && xyzc[1] == epy && xyzc[2] == epz){
					result = Math.min(result, xyzc[3]);
					canMake = true;
					return;
				}

				for(int i = 0; i < 6; i++){
					int nx = xyzc[0] + dxyz[i][0];
					int ny = xyzc[1] + dxyz[i][1];
					int nz = xyzc[2] + dxyz[i][2];
					if(isInBoundary(nx) && isInBoundary(ny) && isInBoundary(nz) && !visited[nz][ny][nx] && updatedArr[nz][ny][nx]){
						visited[nz][ny][nx] = true;
						q.add(new int[] {nx, ny, nz, xyzc[3]+1});
					}
				}
			}
		}
	}

	private static boolean isInBoundary(int c){
		return 0 <= c && c < N;
	}


	private static void getInput(BufferedReader br) throws IOException {
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				String[] l = br.readLine().split(" ");
				for(int k = 0; k < N; k++){
					arr[i][j][k] = l[k].equals("1");
				}
			}
		}
	}
}