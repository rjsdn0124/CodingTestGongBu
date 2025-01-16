import java.io.*;

public class Main{
	private static int N, M;
	private static boolean[][] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		int result = solution();
		System.out.println(result);
	}

	private static int solution(){
		int result = 0;
		for(int i = 1; i <= N; i++){
			if(arr[i][0] && dfs(0, i)){
				result++;
			}
		}
		return result;
	}

	private static boolean dfs(int x, int y){
		if(++x == M) return true;
		y--;
		for(int i = 0; i < 3; i++){
			if(arr[y][x]){
				arr[y][x] = false;
				if(dfs(x, y)) {
					return true;
				}
			}
			y++;
		}
		return false;
	}

	private static void getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]) - 2;

		arr = new boolean[N + 2][M];

		for(int i = 1; i <= N; i++){
			String l = br.readLine();
			for(int j = 0; j < M; j++){
				arr[i][j] = l.charAt(j + 1) == '.';
			}
		}
	}
}