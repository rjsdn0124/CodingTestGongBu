import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int g, result = 0;
		Gate[] gates = new Gate[n + 1];
		for(int i = 0; i < m; i++){
			g = Integer.parseInt(br.readLine());
			if(!solution(gates, g)){
				break;
			}
			result++;
		}
		System.out.println(result);
	}

	private static boolean solution(Gate[] gates, int g){
		return dfs(gates, new Gate(g), g) >= 0;
	}

	private static int dfs(Gate[] gates, Gate gate, int g){
		int x = g - 1;
		if(gates[g] != null){
			x = dfs(gates, gates[g], gates[g].num);
		}else{
			gates[g] = gate;
		}
		gates[g].num = x;
		return x;
	}
}

class Gate{
	int num;
	public Gate(int num){
		this.num = num;
	}
}