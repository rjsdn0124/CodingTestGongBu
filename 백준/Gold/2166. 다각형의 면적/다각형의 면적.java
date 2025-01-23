import java.io.*;

public class Main{
	private static int N;
	private static double result = 0D;
	private static int[][] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		System.out.printf("%.1f", solution());
	}

	private static double solution(){
		double result = 0;
		for(int i = 0; i < N; i++){
			result += 0.5 * arr[i + 1][0] * arr[i][1];
			result -= 0.5 * arr[i][0] * arr[i + 1][1];
		}
		return Math.abs(result);
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][2];

		for(int i = 0; i < N; i++){
			String[] l = br.readLine().split(" ");
			arr[i] = new int[]{Integer.parseInt(l[0]), Integer.parseInt(l[1])};
		}
		arr[N] = arr[0];
	}
}