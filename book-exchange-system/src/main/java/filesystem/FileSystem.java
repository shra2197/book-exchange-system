package filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystem {
    private File root;

    public FileSystem() {
        this.root = new File();
    }

    public List<String> ls(String path) {
        File currentDirectory = root;
        String[] dir = path.split("/");
        List<String> res = new ArrayList<>();
        if (!path.equals("/")) {
            for (int i = 0; i < dir.length; i++) {
                currentDirectory = currentDirectory.getFiles().get(dir[i]);
            }
            if (currentDirectory.isfile) {
                res.add(dir[dir.length - 1]);
                return res;
            }
        }
        res = new ArrayList<>(currentDirectory.getFiles().keySet());
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        File currentDirectory = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length; i++) {
            if (!currentDirectory.getFiles().containsKey(dirs[i])) {
                currentDirectory.getFiles().put(dirs[i], new File());
            }
            currentDirectory = currentDirectory.getFiles().get(dirs[i]);
        }
    }

    public void addContent(String path, String content) {
        File currentDirectory = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length - 1; i++)
            currentDirectory = currentDirectory.getFiles().get(dirs[i]);

        if (!currentDirectory.getFiles().containsKey(dirs[dirs.length - 1])) {
            currentDirectory.getFiles().put(dirs[dirs.length - 1], new File());
        }
        currentDirectory = currentDirectory.getFiles().get(dirs[dirs.length - 1]);
        currentDirectory.isfile = true;
        currentDirectory.addContent(content);
    }

    public String readContent(String path){
        File currentDirectory = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length; i++)
            currentDirectory = currentDirectory.getFiles().get(dirs[i]);
        return currentDirectory.getBuffer().toString();
    }


//["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
//[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
    public static void main(String[] args) {
        String[] cmd=new String[]{"ls","mkdir","addContentToFile","ls","readContentFromFile"};
       String[][] path= new String[][]{{"/"},{"/a/b/c"},{"/a/b/c/d","hello"},{"/"},{"/a/b/c/d"}};
        FileSystem obj = new FileSystem();
        for (int i = 0; i <cmd.length ; i++) {
            final String command = cmd[i];
                switch (command){
                    case "ls":
                        System.out.println(obj.ls(path[i][0]));
                        break;
                    case "mkdir":
                        obj.mkdir(path[i][0]);
                        break;
                    case "addContentToFile":
                        obj.addContent(path[i][0],path[i][1]);
                        break;
                    case "readContentFromFile":
                        System.out.println(obj.readContent(path[i][0]));
                        break;
                    default:
                        System.out.println("not valid command");
                }
        }
    }
}
