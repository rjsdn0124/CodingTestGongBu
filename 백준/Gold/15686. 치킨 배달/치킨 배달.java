import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, result = Integer.MAX_VALUE;
	private static List<int[]> houses;
	private static List<int[]> chickens;
	private static int[] selected;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.println(result);
	}

	private static void solution(){
		selected = new int[M];
		dfs(0, 0);
	}

	private static void dfs(int ind, int depth){
		// M까지 치킨집 선택하기.
		if(depth == M) {
			// M에 도달하면 결과 갱신.
			int sum = 0;
			for (int i = 0; i < houses.size(); i++) {
				int chickenDist = Integer.MAX_VALUE;
				for(int sc: selected) {
					chickenDist = Math.min(chickenDist, calChickenDist(chickens.get(sc), houses.get(i)));
				}
				sum += chickenDist;
			}
			result = Math.min(result, sum);
			return;
		}
		for(int i = ind; i < chickens.size() - M + depth + 1; i++){
			selected[depth] = i;
			dfs(i + 1, depth + 1);
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