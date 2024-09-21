import java.util.*;
import java.io.*;

public class Main{
    static int SIZE = 9;
    static int[][] sudoku = new int[SIZE][SIZE];
    static List<Integer> zeros = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        play(0);

        arrayPrinter();
    }

    public static void getInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < SIZE; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < SIZE; j++){
                sudoku[i][j] = Integer.parseInt(line[j]);
                if(sudoku[i][j] == 0){
                    zeros.add(j*10+i);
                }
            }
        }
    }

    public static boolean play(int depth){
        if(zeros.size() == depth){
            return true;
        }

        int xy = zeros.get(depth);
        int x = xy / 10;
        int y = xy % 10;

        for(int i = 1; i <= 9; i++){
            if(checkNumValid(x, y, i)){
                sudoku[y][x] = i;
                if(play(depth+1)){
                    return true;
                }
                sudoku[y][x] = 0;
            }
        }

        return false;
    }

    public static boolean checkNumValid(int x, int y, int n){
        return garoCheck(y, n) && seroCheck(x, n) && blockCheck(x,y,n);
    }

    public static boolean garoCheck(int y, int n){
        for(int t: sudoku[y]){
            if(t == n){
                return false;
            }
        }
        return true;
    }

    public static boolean seroCheck(int x, int n){
        for(int[] t: sudoku){
            if(t[x] == n){
                return false;
            }
        }
        return true;
    }

    public static boolean blockCheck(int x, int y, int n){
        int spx = x / 3 * 3;
        int spy = y / 3 * 3;

        for(int i = spy; i < spy + 3; i++){
            for(int j = spx; j < spx + 3; j++){
                if(sudoku[i][j] == n){
                    return false;
                }
            }
        }
        return true;
    }

    public static void arrayPrinter(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
}