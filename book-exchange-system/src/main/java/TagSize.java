import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.*;

public class TagSize {
    @AllArgsConstructor
    @ToString
    static
    class Tag {
        String tag;
        Directory directory;

        public void addFile(File file) {
            directory.addFile(file);
        }
    }
@Data
    static class File {
        private String name;
        private int size;

    public File(String f1, int i) {
        name=f1;
        size=i;
    }
}

    static class Directory {
        List<File> files= new ArrayList<>();
        int totalSize = 0;

        public void addFile(File file) {
           files.add(file);
            totalSize += file.getSize();
        }
    }

     Queue<Tag> pq = new PriorityQueue<>(Comparator.comparingInt(t -> -t.directory.totalSize));

    public void addTag(Tag tag) {
        pq.add(tag);
    }

   public   List<Tag> getTopKTags(int k) {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < k; i++)
            tags.add(pq.poll());
        pq.addAll(tags);
        return tags;
    }

    public static void main(String[] args) {
        Tag tag1 = new Tag("T1", new Directory());
        tag1.addFile(new File("F1", 100));
        tag1.addFile(new File("F2", 200));

        Tag tag2 = new Tag("T2", new Directory());
        tag2.addFile(new File("F3", 500));
        final TagSize tagSize = new TagSize();
        tagSize.addTag(tag1);
        tagSize.addTag(tag2);
        System.out.println(tagSize.getTopKTags(1));
    }
}
