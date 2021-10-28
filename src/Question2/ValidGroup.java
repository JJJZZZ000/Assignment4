package Question2;

import java.io.*;
import java.util.Stack;

public class ValidGroup {
    public static boolean isValid(String fileName){
        // read the I/O Stream;
        try (BufferedReader bufInput = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            // create a Stack to store the left part of the grouping symbols;
            Stack<Character> stack = new Stack<>();
            while((line = bufInput.readLine()) != null){
                for(int i = 0; i < line.length(); i++){
                    char ops = line.charAt(i);
                    // if we meet the left part, we would push it into the stack;
                    if(ops == '[' || ops == '{' || ops == '(') stack.push(ops);
                    // if we meet the right part, we would pop the latest met left part symbol and check if it is right;
                    else if(ops == ']'){
                        // if the stack is empty or the last character is not we expected, we can directly return false;
                        if(stack.isEmpty()) return false;
                        char last_ops = stack.pop();
                        if(last_ops != '[') return false;
                    }
                    // same for '{}';
                    else if(ops == '}'){
                        if(stack.isEmpty()) return false;
                        char last_ops = stack.pop();
                        if(last_ops != '{') return false;
                    }
                    else if(ops == ')'){
                        if(stack.isEmpty()) return false;
                        char last_ops = stack.pop();
                        if(last_ops != '(') return false;
                    }
                }
            }
            // if the above process runs smoothly, then check if stack has some remain left part symbols, if there is, return false, otherwise return true;
            return stack.isEmpty();
        } catch (FileNotFoundException f) { // if some exception occurs like file cannot be fund, temporary file will be deleted and exit the program;
            System.out.println("File not found: " + f);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            System.exit(0);
        }
        return false;
    }
}
