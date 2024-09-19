import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = getInput(br);
        StringBuilder sb = new StringBuilder();
        System.out.println(rec(n, 1, 3, 2, sb));
        System.out.println(sb.toString());
    }
    public static int getInput(BufferedReader br) throws IOException{
        return Integer.parseInt(br.readLine());
    }

    public static int rec(int n, int departure, int arrive, int left, StringBuilder sb){
        int result = 0;
        if(n == 0){
            return result;
        }
        result += rec(n-1,departure, left, arrive, sb);
        sb.append(departure).append(" ").append(arrive).append("\n");
        result += 1;
        result += rec(n-1, left, arrive, departure, sb);
        return result;
    }
}