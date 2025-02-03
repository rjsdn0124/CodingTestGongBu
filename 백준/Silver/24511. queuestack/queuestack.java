import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static final String SPACE = " ";
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		solution(br);
		// solution();
		System.out.print(sb);
	}

	private static void solution(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		String[] qs = br.readLine().split(SPACE);
		String[] l = br.readLine().split(SPACE);
		int m = Integer.parseInt(br.readLine());
		for(int i = N - 1; i >= 0 && m > 0; i--) {
			if(qs[i].equals("0")){
				m--;
				sb.append(Integer.parseInt(l[i])).append(SPACE);
			}
		}

		l = br.readLine().split(SPACE);
		for(int i = 0; i < m; i++){
			sb.append(Integer.parseInt(l[i])).append(SPACE);
		}
	}
}