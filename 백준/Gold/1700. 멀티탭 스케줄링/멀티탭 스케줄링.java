import java.io.*;
import java.util.*;

public class Main{
	private static int N, M, now = 0, result = 0;
	private static int[] arr;
	private static Electronic[] electronics;
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
			electronics[arr[now++]].removeElement();
		}

		// 가득 찼을 때.
		while(now < M) {
			// 이미 set에 있는 경우.
			if(!set.contains(arr[now])){
				int max = 0;
				int targetInd = 0;
				// 하나를 무조건 뽑아야함.
				for(int i : set){
					// 뽑기 우선순위
					// 1. 다음에 예정이 없는 친구
					if(electronics[i].list.size() == 0){
						targetInd = i;
						break;
					}else{
						// 2. 다음이 가장 먼 친구 - 다음에 예정이 있다면 다시 꼽아야함.
						if(max < electronics[i].list.get(0)){
							targetInd = i;
							max = electronics[i].list.get(0);
						}
					}

				}
				set.remove(targetInd);
				result++;
			}
			set.add(arr[now]);
			electronics[arr[now++]].removeElement();
		}
	}

	private static void getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		arr = new int[M];
		electronics = new Electronic[M];

		for(int i = 0; i < M; i++) {
			electronics[i] = new Electronic();
		}

		String[] l = br.readLine().split(" ");
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(l[i]) - 1;
			electronics[arr[i]].list.add(i);
		}
	}
}

class Electronic{
	List<Integer> list;

	public Electronic(){
		list = new LinkedList<>();
	}

	public void removeElement(){
		this.list.remove(0);
	}
}