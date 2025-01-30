import java.io.*;

public class Main{
	private static int N, L, result = 0;
	private static int[][] arr, rotatedArr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(result);
	}

	private static void solution(){
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				rotatedArr[i][j] = arr[j][i];
			}
		}

		for(int i = 0; i < N; i++){
			if(canPass(i, arr)){
				result++;
			}
			if(canPass(i, rotatedArr)){
				result++;
			}
		}
	}

	private static boolean canPass(int y, int[][] arr){
		int prev = arr[y][0];
		int prevCount = 1;
		for(int i = 1; i < N; i++){
			if(prev != arr[y][i]){
				if(Math.abs(prev - arr[y][i]) > 1){
					return false;
				}
				if(prev < arr[y][i]){
					if(prevCount < L) {
						return false;
					}else{
						prev = arr[y][i];
						prevCount = 1;
					}
				}else {
					prev = arr[y][i];
					for (int j = 0; j < L - 1; j++) {
						if (i + 1 == N || prev != arr[y][++i]) {
							return false;
						}
					}
					prevCount = 0;
				}
			}else{
				prevCount++;
			}
		}
		return true;
	}

	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);

		arr = new int[N][N];
		rotatedArr = new int[N][N];

		for(int i = 0; i < N; i++) {
			String[] l = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(l[j]);
				rotatedArr[j][i] = arr[i][j];
			}
		}
	}
}