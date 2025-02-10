import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int len = Integer.parseInt(str[0]);
        int del = Integer.parseInt(str[1]);
        Stack<Character> stack = new Stack<>();
        String input = br.readLine();
        
        for(int i = 0; i < len; i++){
            if(stack.isEmpty()){
                stack.push(input.charAt(i));
            }else{
                while(stack.peek() < input.charAt(i)){
                    if(del == 0){
                        break;
                    }
                    stack.pop();
                    del--;
                    if(stack.isEmpty()){
                        break;
                    }
                }
                stack.push(input.charAt(i));
            }
        }
        char[] arr = new char[stack.size()];
        int j = 0;
        while(!stack.isEmpty()){
            arr[j++] = stack.pop();
        }
        for(int i = arr.length - 1; i >= del; i--){
            System.out.print(arr[i]);
        }
    }
}
