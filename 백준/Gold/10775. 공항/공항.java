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
		Gate gate = new Gate(g);
		while(gates[g] != null){
			gate = gates[g];
			g = gate.num;
			if(g < 1) return false;
		}
		gate.num -= 1;
		gates[g] = gate;

		return true;
	}
}

class Gate{
	int num;
	public Gate(int num){
		this.num = num;
	}
}