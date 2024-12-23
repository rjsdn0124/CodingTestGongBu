import java.io.*;
import java.util.*;

public class Main{
	private static int COUNT = 4;
	private static int SIZE = 8;
	private static int[][] cogwheels;
	private static int[] twelveLoc;
	private static int k;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = getInput(br);

		int result = solution(arr);

		System.out.println(result);
	}

	private static int solution(int[][] arr){
		for(int i = 0; i < k; i++){
			doCycle(arr[i], 0);
		}

		return getResult();
	}

	private static void doCycle(int[] req, int type){
		int ninePole = getNinePole(req[0]);
		int threePole = getThreePole(req[0]);

		if(type < 1 && req[0] > 1){
			int leftThreePole = getThreePole(req[0] - 1);
			if(leftThreePole != ninePole){
				doCycle(new int[]{req[0] - 1, -req[1]}, -1);
			}
		}

		if(type > -1 && req[0] < COUNT){
			int rightNinePole = getNinePole(req[0] + 1);
			if(rightNinePole != threePole){
				doCycle(new int[]{req[0] + 1, -req[1]}, 1);
			}
		}
		twelveLoc[req[0] - 1] = (twelveLoc[req[0] - 1] - req[1] + SIZE) % SIZE;
	}

	private static int getThreePole(int num){
		return cogwheels[num-1][(twelveLoc[num - 1] + 2) % SIZE];
	}

	private static int getNinePole(int num){
		return cogwheels[num-1][(twelveLoc[num - 1] + SIZE - 2) % SIZE];
	}

	private static int getResult(){
		int result = 0;
		int score = 1;
		for(int i= 0; i < COUNT; i++){
			if(cogwheels[i][twelveLoc[i]] == '1'){
				result += score;
			}
			score *= 2;
		}
		return result;
	}

	private static int[][] getInput(BufferedReader br) throws IOException{
		cogwheels = new int[COUNT][SIZE];
		twelveLoc = new int[COUNT];

		for(int i = 0; i < COUNT; i++){
			char[] input = br.readLine().toCharArray();
			for(int j = 0; j < SIZE; j++){
				cogwheels[i][j] = input[j];
			}
			twelveLoc[i] = 0;
		}

		k = Integer.parseInt(br.readLine());

		int[][] arr = new int[k][2];
		for(int i = 0; i < k; i++){
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 2; j++){
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		return arr;
	}
}
