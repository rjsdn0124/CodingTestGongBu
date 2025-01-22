import java.io.*;
import java.util.*;

public class Main{
	private static int N = 5;
	private static boolean[][][][] arr = new boolean[N][4][N][N];
	private static boolean[][][] updatedArr = new boolean[N][N][N];
	private static int result = Integer.MAX_VALUE;
	private static boolean canMake = false;
	private static boolean[] plateVisited = new boolean[N];
	private static int[][] dxyz = {{1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution(0);
		System.out.print(canMake ? result : -1);
	}

	private static void solution(int zInd){
		if(zInd == 5){
			if(updatedArr[0][0][0]){
				bfs();
			}
			return;
		}
		for(int i = 0; i < N; i++){
			if(!plateVisited[i]){
				plateVisited[i] = true;
				for(int j = 0; j < 4; j++){
					updatedArr[zInd] = arr[i][j];
					solution(zInd + 1);
				}
				plateVisited[i] = false;
			}
		}
	}

	private static void turnPlate(int ind, int tc){
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[ind][tc+1][4 - j][i] = arr[ind][tc][i][j];
			}
		}
	}

	private static void bfs(){
		int epx = N - 1;
		int epy = N - 1;
		int epz = N - 1;

		if(updatedArr[epz][epy][epx]){
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {0, 0, 0, 0});

			boolean[][][] visited = new boolean[N][N][N];
			visited[0][0][0] = true;

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

	private static void init(BufferedReader br) throws IOException {
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				String[] l = br.readLine().split(" ");
				for(int k = 0; k < N; k++){
					arr[i][0][j][k] = l[k].equals("1");
					for(int m = 0; m < 3; m++){
						turnPlate(i, m);
					}
				}
			}
		}
	}
}