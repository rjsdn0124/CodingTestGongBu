
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        String[] inputs = getInputs();
        long a = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        int c = Integer.parseInt(inputs[2]);

        
        System.out.println(calc(a,b,c));
    }

    public static String[] getInputs() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine().split(" ");
    }

    public static long calc(long a, int b, int c){
        if(b == 1){
            return a%c;
        }
        long result = calc(a, b/2, c);
        result = result * result % c;
        if(b % 2 == 1){
            result = result * a % c;
        }
        return result;
    }
}