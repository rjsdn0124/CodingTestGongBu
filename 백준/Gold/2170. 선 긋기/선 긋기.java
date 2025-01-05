import java.io.*;
import java.util.*;

public class Main{
	private static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Line[] lines = getInput(br);
		Arrays.sort(lines);
		int result = solution(lines);
		System.out.println(result);
	}

	private static int solution(Line[] lines){
		Line line = lines[0];
		int result = 0;
		int start = line.start;
		int end = line.end;

		// 돌면서 시작 보다 끝이 작을 때까지 반복.
		for(Line l: lines){
			if(l.start > end){
				// 찾으면 길이 계산 및 시작점 갱신
				result += end - start;
				start = l.start;
				end = l.end;
			}else {
				end = Math.max(l.end, end);
			}
		}
		result += end - start;

		return result;
	}


	private static Line[] getInput(BufferedReader br) throws IOException {
		// 입력.
		N = Integer.parseInt(br.readLine());

		Line[] arr = new Line[N];

		for(int i = 0; i < N; i++){
			String[] input = br.readLine().split(" ");
			arr[i] = new Line(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}

		return arr;
	}
}

class Line implements Comparable<Line>{
	int start;
	int end;

	public Line(int start, int end){
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Line l){
		return this.start - l.start;
	}
}