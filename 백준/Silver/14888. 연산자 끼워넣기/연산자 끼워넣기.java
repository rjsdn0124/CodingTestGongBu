import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int[] NUMS;
    static int[] OPS = new int[4];
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
        int[] result = dfs(1, NUMS[0]);
        System.out.println(result[1]);
        System.out.println(result[0]);
    }

    public static String[] getInput(BufferedReader br) throws IOException{
        return br.readLine().split(" ");
    }

    public static int[] dfs(int depth, int prevNum){
        int[] resArr = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int res = 0;
        int[] tmparr;
        for(int i = 0; i < 4; i++){
            if(OPS[i] > 0) {
                OPS[i]--;
                res = calc(prevNum, NUMS[depth], i);
                if(depth < N - 1){
                    tmparr = dfs(depth+1, res);
                    resArr[0] = Math.min(resArr[0], tmparr[0]);
                    resArr[1] = Math.max(resArr[1], tmparr[1]);
                }
                else{
                    resArr[0] = res;
                    resArr[1] = res;
                }
                OPS[i]++;
            }
        }
        
        return resArr;
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