import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = getInput(br);
        int n = Integer.parseInt(inputs[0]);
        long r = Long.parseLong(inputs[1]);
        long c = Long.parseLong(inputs[2]);

        System.out.println(visit(n, r, c));
    }

    public static String[] getInput(BufferedReader br) throws IOException{
        return br.readLine().split(" ");
    }

    public static long visit(int n, long r, long c){
        long result = 0;
        if(n == 0){
            return result;
        }
        
        long cutline = (long)Math.pow(2, n-1);

        if(r >= cutline){
            result += cutline * cutline * 2;
            r -= cutline;
        }
        if(c >= cutline){
            result += cutline * cutline;
            c -= cutline;
        }
        return result + visit(n-1, r, c);
    }
}