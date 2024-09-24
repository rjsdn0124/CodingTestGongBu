import java.util.*;
import java.io.*;

public class Main{
    static int SIZE;
    static int result = 0;
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        getInput();
        arr = new boolean[SIZE][SIZE];
        place(0);
        System.out.println(result);
    }

    public static void getInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        SIZE = Integer.parseInt(br.readLine());
    }

    public static void place(int depth){
        if(depth == SIZE){
            result++;
            return;
        }
        for(int i = 0; i < SIZE; i++){
            if(checkValid(i, depth)){
                arr[depth][i] = true;
                place(depth+1);
                arr[depth][i] = false;
            }
        }
    }

    public static boolean checkValid(int x, int y){
        return checkSero(x, y) && checkDaegak(x, y);
    }

    public static boolean checkSero(int x, int y){
        for(int i = 0; i < y; i++){
            if(arr[i][x]){
                return false;
            }
        }
        return true;
    }

    public static boolean checkDaegak(int x, int y){
        int left = x;
        int right = x;
        for(int i = y-1; i >= 0; i--){
            if((left > 0 && arr[i][--left]) || (right < SIZE - 1 && arr[i][++right])){
                return false;
            }
        }
        return true;
    }
}