package filesystem;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class File {
    boolean isfile = false;
    private Map<String, File> files;
    private StringBuffer buffer;

    public void addContent(String content){
        buffer.append(content);
    }
    public File() {
        this.files = new HashMap<>();
        this.buffer = new StringBuffer();
    }
}
