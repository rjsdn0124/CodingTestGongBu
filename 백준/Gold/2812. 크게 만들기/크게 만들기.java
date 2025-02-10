import java.io.*;
import java.util.*;

public class Main{
	private static int N, K;
	private static StringBuilder sb = new StringBuilder();
	private static char[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		solution();
		System.out.println(sb);
	}

	private static void solution(){
		// 인덱스 올라가면서 다음에 작아질 때 하나씩 빼주기
		Deque<Character> st = new ArrayDeque<>();
		for(int i = 0; i < N; i++){
			while(K > 0 && !st.isEmpty() && st.getLast() < arr[i]){
				st.removeLast();
				K--;
			}
			st.offer(arr[i]);
		}

		while(!st.isEmpty()){
			if(K-- > 0){
				st.removeLast();
			}else {
				sb.append(st.removeFirst());
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);

		arr = br.readLine().toCharArray();
	}
}