import java.io.*;

public class Main{
	private static long N;
	private static long M;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		getInput(br);
		long result = solution();
		System.out.println(result);
	}

	private static long solution(){
		// 2의 배수인 친구들까지의 1 개수 구함.
		char[] binary = Long.toBinaryString(M).toCharArray();
		long [] dp = dp(binary);
		// 앞에 1 붙여가면서 계산
		long end = getOneCount(binary, dp);
		binary = Long.toBinaryString(N - 1).toCharArray();
		long start = getOneCount(binary, dp);
		// 시작, 끝 결과 빼주기
		return end - start;
	}

	private static long getOneCount(char[] binary, long[] dp){
		long oneCount = 0;
		long prevOnes = 0;
		long elements = (long)Math.pow(2, binary.length - 1);
		for(int i = 0; i < binary.length; i++){
			if(binary[i] == '1'){
				oneCount += dp[binary.length - i - 1] + prevOnes++ * elements;
			}
			elements /= 2;
		}
		return oneCount+prevOnes;
	}

	private static long[] dp(char[] binary){
		int len = binary.length;
		long[] dp = new long[len];
		dp[0] = 0;
		if(len > 1){
			dp[1] = 1;
		}

		long count = 2;
		for(int i = 2; i < binary.length; i++){
			dp[i] = dp[i - 1] * 2 + count;
			count *= 2;
		}

		return dp;
	}

	private static void getInput(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		N = Long.parseLong(line[0]);
		M = Long.parseLong(line[1]);
	}
}
