import java.util.*;
import java.io.*;

public class Main{
    static int folderCount;
    static int fileCount;
    static Map<String, Folder> folders;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        folders = new HashMap<>();
        folders.put("main", new Folder(null));
        setBaseInfo(br);
        setFolderStructure(br);
        StringBuilder sb = new StringBuilder();
        runQueries(br, sb);
        System.out.println(sb);
    }

    public static String getInputs(BufferedReader br) throws IOException{
        return br.readLine();
    }

    public static void setBaseInfo(BufferedReader br) throws IOException{
        String[] inputs = getInputs(br).split(" ");
        folderCount = Integer.parseInt(inputs[0]);
        fileCount = Integer.parseInt(inputs[1]);
    }

    public static void setFolderStructure(BufferedReader br) throws IOException{
        String[] inputs;
        for(int i = 0; i < folderCount + fileCount; i++){
            inputs = getInputs(br).split(" ");
            Folder parent = folders.get(inputs[0]);
            if(parent == null){ 
                parent = new Folder(null);
                setNewFolder(parent, inputs[0]);
            }

            if(inputs[2].equals("1")){
                Folder child = new Folder(parent);
                Folder createdFolder = folders.get(inputs[1]);
                if(createdFolder != null){
                    child = createdFolder;
                    child.setParent(parent);
                    updateParentFolder(parent, child.getFiles(), child.getFileCount());
                }
                setNewFolder(child, inputs[1]);
            }
            else{
                updateFileList(parent, inputs[1]);
            }
        }
    }

    public static void runQueries(BufferedReader br, StringBuilder sb) throws IOException{
        int queryLen = Integer.parseInt(getInputs(br));
        String[] inputs;
        Folder now;

        for(int i = 0; i < queryLen; i++){
            inputs = br.readLine().split("/");
            now = folders.get(inputs[inputs.length-1]);
            sb.append(now.getFiles().size()).append(" ").append(now.getFileCount()).append("\n");
        }
    }

    public static void updateFileList(Folder folder, String fileName){
        folder.getFiles().add(fileName);
        folder.updateCount(1);
        if(folder.getParent() != null) updateFileList(folder.getParent(), fileName);
    }

    public static void updateParentFolder(Folder parent, Set<String> files, int count){
        for(String s: files){
            parent.getFiles().add(s);
        }
        parent.updateCount(count);
        parent = parent.getParent();
        if(parent != null) updateParentFolder(parent, files, count);
    }

    public static void setNewFolder(Folder child, String childName){
        folders.put(childName, child);
    }
}

class Folder{
    Set<String> childFiles;
    Folder parent;
    int fileCount = 0;

    public Folder(Folder parent){
        childFiles = new HashSet<>();
        this.parent = parent;
    }

    public Set<String> getFiles(){
        return this.childFiles;
    }
    public void setParent(Folder parent){
        this.parent = parent;
    }

    public Folder getParent(){
        return this.parent;
    }

    public void updateCount(int count){
        fileCount+=count;
    }

    public int getFileCount(){
        return this.fileCount;
    }
}