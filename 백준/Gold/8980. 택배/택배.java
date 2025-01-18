import java.io.*;
import java.util.*;

public class Main{
	private static int N, M;
	private static List<int[]>[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		getInput(br);
		int result = solution();
		System.out.println(result);
	}

	private static int solution(){
		int result = 0;

		// 정렬 기준?
		// 해당 마을에 도착했을 때 이전 마을들에서 값을 가져와서 처리하는게 좋지 않을까?
		// 많이 내리고 자주 담아야 최대가 될 듯.
		int[] maxPost = new int[N + 1];
		Arrays.fill(maxPost, M);

		// 가장 낮은 수의 마을 방문.
		for(int i = 1; i <= N; i++){
			// 방문 후 담을 수 있는만큼 친구들 다 담기.
			// 사이 마을의 담았던 친구들보다 담을 친구가 크다면 결과에서 빼고 마을 넘어가면서 반영한 후 처리.
			result += addPost(i, arr[i], maxPost);
		}

		return result;
	}

	private static int addPost(int now, List<int[]> reqs, int[] maxPost){
		int postCount = 0;
		for(int[] req: reqs){
			int tempPost = req[1];
			for(int i = req[0]; i < now; i++){
				tempPost = Math.min(tempPost, maxPost[i]);
				maxPost[i] -= tempPost;
			}
			postCount += tempPost;
		}
		return postCount;
	}

	private static void getInput(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		int reqs = Integer.parseInt(br.readLine());
		arr = new LinkedList[N + 1];

		for(int i = 1; i <= N; i++){
			arr[i] = new LinkedList<>();
		}
		for(int i = 0; i < reqs; i++){
			line = br.readLine().split(" ");
			arr[Integer.parseInt(line[1])].add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[2])});
		}
	}
}