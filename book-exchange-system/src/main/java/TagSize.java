import java.util.*;

public class TagSize {
    class Tag {
        String tag;
        Files files;
    }
    class Files {
        Map<String, Integer> map = new HashMap<>();
        int totalSize = 0;
    }
    Queue<Tag>pq= new PriorityQueue<>(Comparator.comparingInt(t -> t.files.totalSize));
    public void addTag(Tag tag){
        pq.add(tag);
    }

    List<Tag>getTopKTags(int k){
        List<Tag>tags= new ArrayList<>();
        for(int i=0;i<k;i++)
            tags.add(pq.poll());
        pq.addAll(tags);
        return tags;
    }


}
