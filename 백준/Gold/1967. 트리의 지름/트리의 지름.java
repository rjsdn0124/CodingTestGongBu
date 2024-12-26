import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static int result = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<int[]>[] infos = getInput(br);
		dfs(infos,  1);
		System.out.println(result);
	}

	private static int dfs(List<int[]>[] infos, int nodeNum){
		int[] weights = new int[2];
		int maxChildWeight = 0;

		for(int[] info: infos[nodeNum]){
			int childWeight = dfs(infos, info[0]) + info[1];
			maxChildWeight = Math.max(maxChildWeight, childWeight);

			for(int i = 0; i < 2; i++){
				if(weights[i] < childWeight){
					int temp = weights[i];
					weights[i] = childWeight;
					childWeight = temp;
				}
			}
		}
		result = Math.max(result, weights[0] + weights[1]);
		return maxChildWeight;
	}

	private static List<int[]>[] getInput(BufferedReader br) throws IOException{
		// 입력.
		n = Integer.parseInt(br.readLine());
		List<int[]>[] infos = new LinkedList[n + 1];

		for(int i = 1; i <= n; i++){
			infos[i] = new LinkedList<>();
		}

		for(int i = 0; i < n - 1; i++){
			String[] input = br.readLine().split(" ");
			int parent = Integer.parseInt(input[0]);
			int child = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			infos[parent].add(new int[]{child, weight});
		}

		return infos;
	}
}