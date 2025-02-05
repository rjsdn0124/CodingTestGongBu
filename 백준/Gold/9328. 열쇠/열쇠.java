import java.io.*;
import java.util.*;

public class Main{
	private static int N, h, w;
	private static final String SPACE = " ";
	private static char[][] arr;
	private static boolean[] keys = new boolean['z' - 'a' + 1];
	private static boolean[][] visited;
	private static Queue<int[]> q;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		solution(br);
	}

	private static void solution(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());

		while(N-- > 0){
			init(br);
			q = new LinkedList<>();
			visited = new boolean[h][w];
			findEntrance();

			System.out.println(bfs());
		}
	}

	private static int bfs(){
		int result = 0;
		int cycleCount = 0;
		while(!q.isEmpty()){
			int[] xy = q.poll();
			char c = arr[xy[1]][xy[0]];
			if(c <= 'z' && c >= 'a'){
				// 소문자 만난 경우
				keys[c - 'a'] = true;
			}else if(c <= 'Z' && c >= 'A'){
				// 대문자 만난 경우
				if(!keys[c - 'A']){
					if(q.size() < cycleCount){
						break;
					}
					cycleCount++;
					q.add(xy);
					continue;
				}
			}else if(c == '$'){
				// 결과 업데이트
				result++;
			}
			cycleCount = 0;
			for(int i = 0; i < 4; i++){
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];

				if(0 <= nx && nx < w && 0 <= ny && ny < h && !visited[ny][nx] && arr[ny][nx] != '*'){
					q.add(new int[] { nx, ny });
					visited[ny][nx] = true;
				}
			}
		}
		return result;
	}
	private static void findEntrance(){
		for(int i = 0; i < w; i++){
			addInQ(i, 0);
			addInQ(i, h - 1);
		}

		for(int i = 1; i < h - 1; i++){
			addInQ(0, i);
			addInQ(w - 1, i);
		}
	}

	private static void addInQ(int x, int y){
		if(arr[y][x] != '*'){
			q.add(new int[]{x, y});
			visited[y][x] = true;
		}
	}

	private static void init(BufferedReader br) throws IOException {
		String[] l = br.readLine().split(SPACE);
		h = Integer.parseInt(l[0]);
		w = Integer.parseInt(l[1]);
		arr = new char[h][];

		for(int i = 0; i < h; i++){
			arr[i] = br.readLine().toCharArray();
		}

		Arrays.fill(keys, false);

		String keyStr = br.readLine();
		for(char k : keyStr.toCharArray()){
			if(k == '0') break;
			keys[k - 'a'] = true;
		}
	}
}