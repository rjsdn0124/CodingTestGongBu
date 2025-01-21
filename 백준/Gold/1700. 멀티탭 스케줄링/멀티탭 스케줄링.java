import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, now = 0, result = 0;
	private static int[] arr;
	private static List<Integer>[] electronics;
	private static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		solution();
		System.out.print(result);
	}

	private static void solution(){
		// 일단 멀티탭 가득 채울 때까지 계속 set에 저장.
		while(set.size() < N && now < M){
			set.add(arr[now]);
			electronics[arr[now++]].remove(0);
		}

		// 가득 찼을 때.
		while(now < M) {
			int targetInd = arr[now];
			// 이미 set에 있는 경우.
			if(!set.contains(targetInd)){
				// 하나를 무조건 뽑아야함.
				for(int i : set){
					// 뽑기 우선순위
					// 1. 다음에 예정이 없는 친구
					if(electronics[i].isEmpty()){
						targetInd = i;
						break;
					}else{
						// 2. 다음이 가장 먼 친구 - 다음에 예정이 있다면 다시 꼽아야함.
						if(electronics[targetInd].get(0) < electronics[i].get(0)){
							targetInd = i;
						}
					}

				}
				set.remove(targetInd);
				result++;
			}
			set.add(arr[now]);
			electronics[arr[now++]].remove(0);
		}
	}

	private static void getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		arr = new int[M];
		electronics = new LinkedList[M];

		for(int i = 0; i < M; i++) {
			electronics[i] = new LinkedList<>();
		}

		String[] l = br.readLine().split(" ");
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(l[i]) - 1;
			electronics[arr[i]].add(i);
		}
	}
}