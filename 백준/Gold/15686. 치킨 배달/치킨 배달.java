import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, hCount, cCount, result = Integer.MAX_VALUE;
	private static int[][] houses;
	private static int[][] chickens;
	private static int[] selected;
	private static int[][] chickenDists;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.println(result);
	}

	private static void solution(){
		getChickenDists();
		cCount -= M;
		dfs(0, 0);
	}

	private static void dfs(int ind, int depth){
		// M까지 치킨집 선택하기.
		if(depth == M) {
			// M에 도달하면 결과 갱신.
			int sum = 0;
			for (int i = 0; i < hCount; i++) {
				int chickenDist = Integer.MAX_VALUE;
				for(int sc: selected) {
					chickenDist = Math.min(chickenDist, chickenDists[sc][i]);
				}
				sum += chickenDist;
			}
			result = Math.min(result, sum);
			return;
		}

		depth++;
		for(int i = ind; i < cCount + depth; i++){
			selected[depth - 1] = i;
			dfs(i + 1, depth);
		}
	}

	private static void getChickenDists(){
		chickenDists = new int[cCount][hCount];
		for(int i = 0; i < cCount; i++){
			for(int j = 0; j < hCount; j++){
				chickenDists[i][j] = calChickenDist(chickens[i], houses[j]);
			}
		}
	}

	private static int calChickenDist(int[] chicken, int[] house){
		return Math.abs(chicken[0] - house[0]) + Math.abs(chicken[1] - house[1]);
	}

	private static void getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		houses = new int[N * 2][2];
		chickens = new int[13][2];
		selected = new int[M];

		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				if (line[j].equals("1")) {
					houses[hCount++] = new int[] {j, i};
				}else if(line[j].equals("2")){
					chickens[cCount++] = new int[] {j, i};
				}
			}
		}
	}
}