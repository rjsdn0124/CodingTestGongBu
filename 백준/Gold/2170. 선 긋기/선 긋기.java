import java.io.*;
import java.util.*;

public class Main{
	private static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Line> pq = getInput(br);
		int result = solution(pq);
		System.out.println(result);
	}

	private static int solution(PriorityQueue<Line> pq){
		int result = 0;
		int start = Integer.MIN_VALUE;
		int end = Integer.MIN_VALUE;

		// 돌면서 시작 보다 끝이 작을 때까지 반복.
		while(!pq.isEmpty()){
			Line l = pq.poll();
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


	private static PriorityQueue<Line> getInput(BufferedReader br) throws IOException {
		// 입력.
		N = Integer.parseInt(br.readLine());

		PriorityQueue<Line> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++){
			String[] input = br.readLine().split(" ");
			pq.add(new Line(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
		}

		return pq;
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