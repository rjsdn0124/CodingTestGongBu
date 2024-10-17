import java.io.*;
import java.util.*;

public class Main{
    static int INNING = 0;
    static int[][] HITS;
    static List<List<Integer>> HITTER_SEQUENCES = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        INNING = Integer.parseInt(getInput(br)[0]);
        setArr(br);

        System.out.println(getResult());
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

    private static int getResult(){
        int max = 0;
        
        createHitSequences(0,new ArrayList<>(), new boolean[9]);
        
        for(List<Integer> hitSequence: HITTER_SEQUENCES){
            int result = 0;
            int hitter = 0;
            for(int i = 0; i < INNING; i++){
                int outCount = 0;
                boolean[] base = new boolean[4];
                while(outCount < 3){
                    hitter %= 9;
                    int hit = HITS[i][hitSequence.get(hitter++)];
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

        return max;
    }

    private static void createHitSequences(int depth, List<Integer> prev, boolean[] visited){
        if(depth == 9){
            HITTER_SEQUENCES.add(List.copyOf(prev));
            return;
        }
        if(depth == 3){
            prev.add(0);
            visited[0] = true;
            createHitSequences(depth+1, prev, visited);
            visited[0] = false;
            prev.remove(depth);
        }
        for(int i = 1; i < 9; i++){
            if(!visited[i]){
                prev.add(i);
                visited[i] = true;
                createHitSequences(depth+1, prev, visited);
                visited[i] = false;
                prev.remove(depth);
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