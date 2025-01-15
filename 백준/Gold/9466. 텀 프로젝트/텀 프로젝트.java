import java.io.*;

public class Main{
	private static int[] students;
	private static int[] visited;

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
			students = new int[studentCount];
			visited = new int[studentCount];

			for (int i = 0; i < students.length; i++) {
				students[i] = Integer.parseInt(line[i]) - 1;
				if(i == students[i]) {
					studentCount--;
					visited[i] = -1;
				}
			}

			for(int i = 0; i < students.length; i++){
				if(visited[i] == 0) {
					studentCount -= createTeam(i, 1);
				}
			}

			sb.append(studentCount).append("\n");
		}

		return sb;
	}

	private static int createTeam(int ind, int result){
		if(visited[students[ind]] == 0) {
			visited[ind] = result;
			result = createTeam(students[ind], ++result);
		}else if(visited[students[ind]] > 0){
			result -= visited[students[ind]] - 1;
		}else{
			result = 0;
		}
		visited[ind] = -1;

		return result;
	}
}