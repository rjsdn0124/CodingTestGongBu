import java.io.*;
import java.util.*;

public class Main{
    static int size;
    static int[][] arr;
    static Shark shark;
    static int result = 0;
    static int[] dx = new int[]{0, -1, 1, 0};
    static int[] dy = new int[]{-1, 0, 0, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        getInputs(br);
        searching();
        System.out.println(result);
    }

    // 입력
    public static void getInputs(BufferedReader br) throws IOException{
        size = Integer.parseInt(br.readLine());
        arr = new int[size][size];
        String[] input;

        for(int i = 0; i < size; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j < size; j++){
                arr[i][j] = Integer.parseInt(input[j]);
                if(arr[i][j] == 9) {
                    shark = new Shark(2, j, i);
                    arr[i][j] = 0;
                }
            }
        }
    }

    public static int makeInfoInt(int count, int x, int y) { return count * 10000 + x * 100 + y; }

    public static int[] separateInfoInt(int info){
        return new int[]{info / 10000, (info % 10000) / 100, info % 100};
    }

    public static boolean bfs(){
        visited = new boolean[size][size];
        Queue<Integer> que = new LinkedList<>();
        int[] sharkLoc = shark.getCoord();
        que.add(makeInfoInt(0, sharkLoc[0], sharkLoc[1]));
        visited[sharkLoc[1]][sharkLoc[0]] = true;
        int threshold = size * size + 1;
        int tx = size, ty = size;
        boolean flag = true;

        while(!que.isEmpty()){
            int infoInt = que.poll();
            int[] separatedInfoInt = separateInfoInt(infoInt);
            int count = separatedInfoInt[0] + 1;
            int x = separatedInfoInt[1];
            int y = separatedInfoInt[2];

            if(threshold < count) {
                break;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= size || ny < 0 || ny >= size || visited[ny][nx] || arr[ny][nx] > shark.getSize()) continue;
                
                if(arr[ny][nx] > 0 && shark.getSize() > arr[ny][nx]){
                    threshold = count;
                    if(ny < ty){
                        ty = ny;
                        tx = nx;
                    }
                    else if(ny == ty && nx < tx){
                        ty = ny;
                        tx = nx;
                    }
                    flag = false;
                }
                else{
                    que.add(makeInfoInt(count, nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }
        if(!flag) eating(tx, ty, threshold);
        return flag;
    }

    public static void searching(){
        while(true){ 
            if(bfs()) return;
        }
    }

    public static void eating(int x, int y, int count){
        shark.eat(x, y);
        arr[y][x] = 0;
        result += count;
    }
}

class Shark{
    int[] coord = new int[2];
    int ate;
    int size;
    public Shark(int size, int x, int y){
        this.size = size;
        this.coord = new int[]{x,y};
    }

    public int getSize(){
        return this.size;
    }

    public int[] getCoord(){
        return this.coord;
    }

    public void eat(int x, int y){
        this.updateCoord(x, y);
        if(++ate == size){
            size++;
            ate = 0;
        }
    }

    private void updateCoord(int x, int y){
        this.coord[0] = x;
        this.coord[1] = y;
    }
}