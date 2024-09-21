import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int[][] arr;
    static int sum;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        getInput();

        Stack<Integer> members = new Stack<>();
        members.add(0);
        dfs(members, 0, 0);
        System.out.println(min);
    }

    public static void getInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++){
            String[] tmp = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(tmp[j]);
                sum += arr[i][j];
            }
        }
    }

    public static void dfs(Stack<Integer> members, int now, int prevSum){
        int count = members.size();
        if(count == N/2){
            List<Integer> enemies = new LinkedList<>();
            int enemySum = 0;
            for(int i = 0; i < N; i++){
                if(!members.contains(i)){
                    for(int j: enemies){
                        enemySum += arr[i][j] + arr[j][i];
                    }
                    enemies.add(i);
                }
            }
            min = Math.min(min, Math.abs(prevSum - enemySum));
            return;
        }

        for(int i = now+1; i <= N / 2 + count; i++){
            int newSum = prevSum;
            for(int j: members){
                newSum += arr[i][j] + arr[j][i];
            }
            members.push(i);
            dfs(members, i, newSum);
            members.pop();
        }
    }
}