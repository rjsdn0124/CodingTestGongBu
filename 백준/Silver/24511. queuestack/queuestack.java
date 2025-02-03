import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main{
	private static int N;
	private static final String SPACE = " ";
	private static StringBuilder sb = new StringBuilder();
	private static List<Integer> arr;
	private static int[] reqs;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init(br);
		solution();
		System.out.print(sb);
	}

	private static void solution(){
		// 큐면 인덱스 하나씩 줄이고 출력, 그 자리에 넣기
		int nowInd = 0;
		int arrSize = arr.size();

		for(int req: reqs){
			if(arrSize > 0) {
				nowInd = (nowInd + arrSize - 1) % arrSize;
				sb.append(arr.get(nowInd)).append(" ");
				arr.set(nowInd, req);
			}else{
				sb.append(req).append(" ");
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new ArrayList<>();

		String[] qs = br.readLine().split(SPACE);
		String[] l = br.readLine().split(SPACE);
		for(int i = 0; i < N; i++) {
			if(qs[i].equals("0")){
				arr.add(Integer.parseInt(l[i]));
			}
		}

		int m = Integer.parseInt(br.readLine());
		reqs = new int[m];

		l = br.readLine().split(SPACE);
		for(int i = 0; i < m; i++){
			reqs[i] = Integer.parseInt(l[i]);
		}
	}
}