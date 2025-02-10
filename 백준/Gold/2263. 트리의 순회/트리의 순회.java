import java.io.*;
import java.util.*;

public class Main{
	private static int N;
	private static StringBuilder sb = new StringBuilder();
	private static int[] inOrderInds;
	private static int[] postOrder;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		solution();
		System.out.println(sb);
	}

	private static void solution(){
		// dfs 돌면서 중위 후위 연산해서 같은 인덱스면 ok
		// post에서 root 찾고 in에서 갯수 가져온다.
		// post에서 갯수만큼 빼가면서 자식 찾기.
		// 자식을 root로 다시 시행.
		search(N - 1, 0, N - 1);
	}

	private static void search(int parentInd, int start, int end){
		int left = inOrderInds[postOrder[parentInd]] - start;
		int right = end - inOrderInds[postOrder[parentInd]];

		sb.append(postOrder[parentInd]).append(" ");
		if(left > 0) {
			search(parentInd - right - 1, start, start + left - 1);
		}
		if(right > 0) {
			search(parentInd - 1, end - right + 1, end);
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		inOrderInds = new int[N + 1];
		postOrder = new int[N];

		String[] inLine = br.readLine().split(" ");
		String[] postLine = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			inOrderInds[Integer.parseInt(inLine[i])] = i;
			postOrder[i] = Integer.parseInt(postLine[i]);
		}
	}
}