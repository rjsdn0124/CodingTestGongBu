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
		int pointer = m - 1;

		while(totalMove < m){
			int thisPoint = pointer;
			for(int i = n - 1; i >= 0; i--){
				for(int j = thisPoint; j >= 0; j--){
					if(!visited[j] && container[j] <= crain[i]){
						totalMove++;
						visited[j] = true;
						thisPoint = j - 1;
						if(i == n - 1){
							pointer = thisPoint;
						}
						break;
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