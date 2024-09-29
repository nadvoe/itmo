package FileSystem;

import java.util.Stack;

public class FileStack {

    private static final Stack<String> filesStack = new Stack<>();
    public FileStack(){
    }

    public static Stack<String> getFileStack(){
        return filesStack;
    }

    public static void addFile(String filename){
        filesStack.push(filename);
    }

    public static void removeFile(){
        filesStack.pop();
    }

}