import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(solution(br));
	}

	private static StringBuilder solution(BufferedReader br) throws IOException{
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		while(n-- > 0) {
			int studentCount = Integer.parseInt(br.readLine());

			String[] line = br.readLine().split(" ");
			int[] students = new int[studentCount];
			boolean[] visited = new boolean[studentCount];
			int totalTeamMateCount = 0;
			for (int i = 0; i < studentCount; i++) {
				students[i] = Integer.parseInt(line[i]) - 1;
				if(i == students[i]){
					totalTeamMateCount++;
					visited[i] = true;
				}
			}

			for(int i = 0; i < studentCount; i++){
				if(!visited[i]) {
					totalTeamMateCount += createTeam(visited, students, i);
				}
			}

			sb.append(studentCount - totalTeamMateCount).append("\n");
		}

		return sb;
	}

	private static int createTeam(boolean[] visited, int[] students, int ind){
		int result = 0;
		Map<Integer, Integer> map = new HashMap<>();

		while(!visited[ind]){
			map.put(ind, ++result);
			visited[ind] = true;
			ind = students[ind];
		}

		return map.containsKey(ind) ? result - map.get(ind) + 1 : 0;
	}
}