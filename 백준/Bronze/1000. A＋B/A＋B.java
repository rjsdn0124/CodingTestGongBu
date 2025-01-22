import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        System.out.print(Integer.parseInt(line[0]) + Integer.parseInt(line[1]));
    }
}