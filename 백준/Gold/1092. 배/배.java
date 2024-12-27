import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static int m;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] crain = getInputCrain(br);
		int[] container = getInputContainer(br);

		int result = crain[n - 1] >= container[m - 1] ? solution(crain, container) : -1;
		System.out.println(result);
	}

	private static int solution(int[] crain, int[] container){
		int result = 0;
		int totalMove = 0;
		boolean[] visited = new boolean[m];
		int[] pointers = new int[n];
		for(int i = 0; i < n; i++){
			pointers[i] = m - 1;
		}

		while(totalMove < m){
			if(n == 1 || pointers[n - 2] == -1){
				result += m - totalMove;
				break;
			}
			for(int i = n - 1; i >= 0; i--){
				for(int j = pointers[i]; j >= 0; j--){
					if(!visited[j] && container[j] <= crain[i]){
						totalMove++;
						visited[j] = true;
						pointers[i] = j - 1;
						break;
					}else if(j == 0){
						pointers[i] = -1;
					}
				}
			}
			result++;
		}
		return result;
	}

	private static int[] getInputCrain(BufferedReader br) throws IOException{
		// 입력.
		n = Integer.parseInt(br.readLine());


		String[] input = br.readLine().split(" ");
		int[] crain = new int[n];

		for(int i = 0; i < n; i++){
			crain[i] = Integer.parseInt(input[i]);
		}
		Arrays.sort(crain);
		return crain;
	}

	private static int[] getInputContainer(BufferedReader br) throws IOException{
		// 입력.
		m = Integer.parseInt(br.readLine());


		String[] input = br.readLine().split(" ");
		int[] container = new int[m];

		for(int i = 0; i < m; i++){
			container[i] = Integer.parseInt(input[i]);
		}
		Arrays.sort(container);
		return container;
	}
}