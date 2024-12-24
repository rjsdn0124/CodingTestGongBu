import java.io.*;
import java.util.*;

public class Main{
	private static int n;
	private static int m;
	private static int[] dx = new int[] {1 , 0, -1, 0};
	private static int[] dy = new int[] {0 , 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);
		int result = solution(arr);
		System.out.println(result);
	}

	private static int solution(int[][] arr){
		// 섬 번호 매겨주고 몇 번 섬에 어떤 좌표들이 있는지 반환
		List<List<int[]>> islands = getIslands(arr);
		// 섬간 다리의 최소 길이 배열.
		PriorityQueue<Bridge> pq = getBridgeInfo(arr, islands);
		// 다리 연결하기
		return connectOptimizedBridge(pq, islands.size());
	}

	private static int connectOptimizedBridge(PriorityQueue<Bridge> pq, int size){
		int[] visited = new int[size + 1];
		for(int i = 0; i < visited.length; i++){
			visited[i] = i;
		}

		int result = 0;
		while(!pq.isEmpty()){
			Bridge b = pq.poll();

			if(visited[b.getIsland1()] != visited[b.getIsland2()]){
				result += b.getLen();
				updateVisit(b, visited);
			}

			if(checkValid(visited)){
				return result;
			}
		}
		return -1;
	}

	private static boolean checkValid(int[] visited){
		for(int i = 1; i < visited.length - 1; i++){
			if(visited[i] != visited[i+1]){
				return false;
			}
		}
		return true;
	}

	private static void updateVisit(Bridge b, int[] visited){
		int targetNum = visited[b.getIsland2()];
		for(int i = 0; i < visited.length; i++){
			if(visited[i] == targetNum) {
				visited[i] = visited[b.getIsland1()];
			}
		}
	}

	private static PriorityQueue<Bridge> getBridgeInfo(int[][] arr, List<List<int[]>> islands){
		PriorityQueue<Bridge> pq = new PriorityQueue<>();
		// 섬과 섬을 잇는 가장 최소 값 저장하기.
		int iNum = 1;
		for(List<int[]> island: islands){
			for(int[] coord: island){
				findBridge(iNum, coord, arr, pq);
			}
			iNum++;
		}
		return pq;
	}

	private static void findBridge(int iNum, int[] coord, int[][] arr, PriorityQueue<Bridge> pq){
		for(int i = 0; i < 4; i++){
			int nx = coord[0] + dx[i];
			int ny = coord[1] + dy[i];
			int len = 0;
			while(0 <= nx && nx < m && 0 <= ny && ny < n){
				if(arr[ny][nx] > 0){
					if(len >= 2 && arr[ny][nx] != iNum){
						pq.add(new Bridge(len, iNum, arr[ny][nx]));
					}
					break;

				}
				nx += dx[i];
				ny += dy[i];
				len++;
			}
		}
	}

	private static List<List<int[]>> getIslands(int[][] arr){
		List<List<int[]>> islands = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		int iNum = 1;

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(arr[i][j] == 1 && !visited[i][j]){
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[]{j, i});
					// bfs로 내 섬 찾기.
					visited[i][j] = true;
					islands.add(bfs(arr, visited, q, iNum));
					iNum++;
				}
			}
		}

		return islands;
	}

	private static List<int[]> bfs(int[][] arr, boolean[][] visited, Queue<int[]> q, int iNum){
		List<int[]> neighbors = new LinkedList<>();

		while(!q.isEmpty()){
			int[] temp = q.poll();
			neighbors.add(temp);
			arr[temp[1]][temp[0]] = iNum;
			for(int i = 0; i < 4; i++){
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];

				if(0 <= nx && nx < m && 0 <= ny && ny < n && arr[ny][nx] == 1 && !visited[ny][nx]){
					q.add(new int[]{nx, ny});
					visited[ny][nx] = true;
				}
			}
		}

		return neighbors;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		int[][] arr = new int[n][m];

		for(int i = 0; i < n; i++){
			input = br.readLine().split(" ");
			for(int j = 0; j < m; j++){
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		return arr;
	}
}

class Bridge implements Comparable<Bridge>{
	int len;
	int island1;
	int island2;

	public Bridge(int len, int island1, int island2){
		this.len = len;
		this.island1 = island1;
		this.island2 = island2;
	}

	public int getLen(){
		return this.len;
	}

	public int getIsland1(){
		return this.island1;
	}

	public int getIsland2(){
		return this.island2;
	}

	@Override
	public int compareTo(Bridge b){
		return this.len - b.getLen();
	}
}
