import java.io.*;
import java.util.*;

public class Main{
    static int INNING = 0;
    static int[][] HITS;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        INNING = Integer.parseInt(getInput(br)[0]);
        setArr(br);
        
        playForEachHitSequences(0,new ArrayList<>(), new boolean[9]);

        System.out.println(max);
    }

    private static String[] getInput(BufferedReader br) throws IOException{
        return br.readLine().split(" ");
    }

    private static void setArr(BufferedReader br) throws IOException{
        HITS = new int[INNING][9];
        for(int i = 0; i < INNING; i++){
            HITS[i] = new int[9];
            String[] inputs = getInput(br);
            
            for(int j = 0; j < 9; j++){
                HITS[i][j] = Integer.parseInt(inputs[j]);
            }
        }
    }

    private static void play(List<Integer> hitSequence){
        int result = 0;
        int hitter = 0;

        for(int i = 0; i < INNING; i++){
            int outCount = 0;
            int[] hits = HITS[i];
            boolean[] base = new boolean[4];

            while(outCount < 3){
                hitter %= 9;
                int hit = hits[hitSequence.get(hitter++)];

                if(hit == 0) {
                    outCount++;
                }
                else{
                    base[0] = true;
                    result += updateBase(base, hit);
                }
            }
            
        }

        max = Math.max(max, result);
    }

    private static void playForEachHitSequences(int depth, List<Integer> list, boolean[] visited){
        if(depth == 9){
            play(list);
            return;
        }

        if(depth == 3){
            list.add(0);
            visited[0] = true;
            playForEachHitSequences(depth+1, list, visited);
            visited[0] = false;
            list.remove(depth);
        }

        for(int i = 1; i < 9; i++){
            if(!visited[i]){
                list.add(i);
                visited[i] = true;
                playForEachHitSequences(depth+1, list, visited);
                visited[i] = false;
                list.remove(depth);
            }
        }
    }

    private static int updateBase(boolean[] base, int hit){
        int score = 0;

        for(int i = 3; i >= 0; i--){
            if(base[i]){
                if(i + hit >= 4){
                    score++;
                    base[i] = false;
                }
                else{
                    base[i+hit] = true;
                    base[i] = false;
                }
            }
        }
        return score;
    }
}