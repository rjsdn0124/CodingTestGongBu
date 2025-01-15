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
			int[] visi = new int[studentCount];
			int totalTeamMateCount = 0;

			for (int i = 0; i < studentCount; i++) {
				students[i] = Integer.parseInt(line[i]) - 1;
				if(i == students[i]){
					totalTeamMateCount++;
					visi[i] = -1;
				}
			}

			for(int i = 0; i < studentCount; i++){
				if(visi[i] == 0) {
					totalTeamMateCount += createTeam(visi, students, i, 1);
				}
			}

			sb.append(studentCount - totalTeamMateCount).append("\n");
		}

		return sb;
	}

	private static int createTeam(int[] visited, int[] students, int ind, int result){
		if(visited[ind] > 0){
			return result - visited[ind];
		}else if(visited[ind] == 0){
			visited[ind] = result++;
			result = createTeam(visited, students, students[ind], result);
			visited[ind] = -1;
			return result;
		}else{
			return 0;
		}
	}
}