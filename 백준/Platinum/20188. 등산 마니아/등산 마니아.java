import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static long result = 0;
	private static List<Integer>[] arr;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.print(result);
	}

	private static void solution(){
		// dfs 돌기. 인자 -> 자신 ind, depth
		// dfs에선 무조건 자신을 포함한 자식들만으로 이루어진 조합 계산.
		// dfs에서 계산해서 갱신해야하는거. 왼쪽 자신 + 오른쪽 자신 + 왼쪽 오른쪽. 모두 depth더해줘야함.
		// 자신이 정상일 때를 구했으면 실제 정상과 거리 계산해서 결과 갱신.
		// dfs 반환값은 자신 포함 갯수, 자신 포함 깊이의 합
		dfs(0, 0);
	}

	private static long[] dfs(int ind, int depth){
		long count = 0;
		long dSum = 0;
		long[] child = new long[2];
		visited[ind] = true;
		for(int i : arr[ind]){
			if(!visited[i]){
				child = dfs(i, depth+1);
				count += child[0];
				// 자식 깊이 합 + 자식 개수
				dSum += child[1] + child[0];
			}
		}
		long prevC = count - child[0];
		long prevS = dSum - child[1] - child[0];
		result += dSum + count * depth;
		result += prevS * child[0] + (child[0] + child[1]) * prevC + depth * prevC * child[0];
		count += 1;
		return new long[]{count, dSum};
	}

	private static void getInput(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new ArrayList[N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++){
			arr[i] = new ArrayList<>();
		}

		for(int i = 0; i < N - 1; i++) {
			String[] l = br.readLine().split(" ");
			int n1 = Integer.parseInt(l[0]) - 1;
			int n2 = Integer.parseInt(l[1]) - 1;
			arr[n1].add(n2);
			arr[n2].add(n1);
		}
	}
}