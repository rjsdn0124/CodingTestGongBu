import java.io.*;

public class Main{
	private static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		// 1개나 3개 똑같음.
		// 결국 1개 가져가는 경우.
		System.out.print(N % 2 == 1 ? "SK" : "CY");
	}


	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
	}
}