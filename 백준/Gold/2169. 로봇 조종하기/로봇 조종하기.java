import java.io.*;

public class Main{
    static int N;
    static int M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = getInput(br);
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        int[][] arr = createArr(N, M, br);
        
        int result = getResult(arr);
        System.out.println(result);
    }

    private static String[] getInput(BufferedReader br) throws IOException{
        return br.readLine().split(" ");
    }

    private static int[][] createArr(int n, int m, BufferedReader br) throws IOException{
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++){
            arr[i] = new int[m];
            String[] inputs = getInput(br);

            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        return arr;
    }

    private static int getResult(int[][] arr){
        int[] prevRecord = new int[M];
        int[] leftRecord = new int[M];
        int[] rightRecord = new int[M];

        for(int i = 0; i < N; i++){
            if(i == 0){
                prevRecord[0] += arr[i][0];
                for(int j = 1; j < M; j++){
                    prevRecord[j] = prevRecord[j-1] + arr[i][j];
                }
            }
            else{
                // 맨 왼쪽만 위의 값.
                leftRecord[0] = prevRecord[0] + arr[i][0];
                // 맨 오른쪽만 위의 값.
                rightRecord[M-1] = prevRecord[M-1] + arr[i][M-1];
    
                for(int j = 1; j < M; j++){
                    int rightInd = M-j-1;
    
                    // 왼쪽 값 가져오기.
                    leftRecord[j] = leftRecord[j-1];
                    leftRecord[j] = Math.max(leftRecord[j], prevRecord[j]);
                    // 오른쪽 값 가져오기.
                    rightRecord[rightInd] = rightRecord[rightInd+1];
                    rightRecord[rightInd] = Math.max(rightRecord[rightInd], prevRecord[rightInd]);
                
                    leftRecord[j] += arr[i][j];
                    rightRecord[rightInd] += arr[i][rightInd];
    
                }
                
                for(int j = 0; j < M; j++){
                    prevRecord[j] = Math.max(leftRecord[j], rightRecord[j]);
                }
            }
        }
        return prevRecord[M-1];
    }
}