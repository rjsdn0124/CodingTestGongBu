import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] sarr = sc.nextLine().split(" ");
        int n = Integer.parseInt(sarr[0]);
        int s = Integer.parseInt(sarr[1]);
        int m = Integer.parseInt(sarr[2]);

        boolean[][] arr = new boolean[n+1][m+1];
        int ind = 0;
        arr[ind++][s] = true;
        sarr = sc.nextLine().split(" ");
        for(String str: sarr){
            calc(arr, ind++, Integer.parseInt(str), m);
        }

        int result = -1;
        for(int i = m; i >= 0; i--){
            if(arr[n][i]) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }

    public static void calc(boolean[][] arr, int ind, int v, int m){
        for(int i = 0; i < arr[ind].length; i++){
            if(arr[ind - 1][i]){
                if(i + v <= m){
                    arr[ind][i+v] = true;
                }
                if(i - v >= 0){
                    arr[ind][i-v] = true;
                }
            }
        }
    }
}
