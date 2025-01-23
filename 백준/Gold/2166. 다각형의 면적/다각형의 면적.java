import java.io.*;

public class Main{
	private static int N;
	private static long result = 0;
	private static long[][] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.printf("%.1f", Math.abs(0.5 * result));
	}

	private static void solution(){
		for(int i = 0; i < N; i++){
			result += arr[i + 1][0] * arr[i][1] - arr[i][0] * arr[i + 1][1];
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new long[N + 1][2];

		for(int i = 0; i < N; i++){
			String[] l = br.readLine().split(" ");
			arr[i] = new long[]{Long.parseLong(l[0]), Long.parseLong(l[1])};
		}
		arr[N] = arr[0];
	}
}