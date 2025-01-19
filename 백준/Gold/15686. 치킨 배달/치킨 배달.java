import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, result = Integer.MAX_VALUE;
	private static List<int[]> houses;
	private static List<int[]> chickens;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.println(result);
	}

	private static void solution(){
		int[] chickenDist = new int[houses.size()];
		Arrays.fill(chickenDist, Integer.MAX_VALUE);
		dfs(0, 0, chickenDist);
	}

	private static void dfs(int ind, int depth, int[] chickenDist){
		// M까지 치킨집 선택하기.
		if(depth == M) {
			// M에 도달하면 결과 갱신.
			int sum = 0;
			for(int i = 0; i < chickenDist.length; i++){
				sum += chickenDist[i];
			}
			result = Math.min(result, sum);
			return;
		}
		depth++;
		for(int i = ind; i < chickens.size() - M + depth; i++){
			int[] newCD = new int[chickenDist.length];
			// 과정 중 선택된 치킨집과 집과의 치킨 거리 갱신.
			int[] chicken = chickens.get(i);
			for(int j = 0; j < chickenDist.length; j++){
				newCD[j] = Math.min(chickenDist[j], calChickenDist(chicken, houses.get(j)));
			}
			dfs(i, depth, newCD);
		}
	}

	private static int calChickenDist(int[] chicken, int[] house){
		return Math.abs(chicken[0] - house[0]) + Math.abs(chicken[1] - house[1]);
	}

	private static void getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		houses = new ArrayList<>();
		chickens = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				if (line[j].equals("1")) {
					houses.add(new int[] {j, i});
				}else if(line[j].equals("2")){
					chickens.add(new int[] {j, i});
				}
			}
		}
	}
}