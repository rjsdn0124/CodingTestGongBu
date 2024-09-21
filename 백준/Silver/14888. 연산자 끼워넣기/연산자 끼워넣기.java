import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int[] NUMS;
    static int[] OPS = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(getInput(br)[0]);
        NUMS = new int[N];
        String[] numstrs = getInput(br);
        for(int i = 0; i < N; i++){
            NUMS[i] = Integer.parseInt(numstrs[i]);
        }
        
        String[] opstrs = getInput(br);
        
        for(int i = 0; i < 4; i++){
            OPS[i] = Integer.parseInt(opstrs[i]);
        }
        dfs(1, NUMS[0]);
        System.out.println(max);
        System.out.println(min);
    }

    public static String[] getInput(BufferedReader br) throws IOException{
        return br.readLine().split(" ");
    }

    public static void dfs(int depth, int sum){
        int res = 0;
        if(depth == N){
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }
        for(int i = 0; i < 4; i++){
            if(OPS[i] > 0) {
                OPS[i]--;
                res = calc(sum, NUMS[depth], i);
                dfs(depth+1, res);
                OPS[i]++;
            }
        }
    }

    // 계산기
    public static int calc(int x, int y, int ops){
        switch(ops){
            case 0:
                return x+y;
            case 1:
                return x - y;
            case 2:
            return x * y;
            case 3:
            return x / y;
            default:
            return x;
        }
    }
}