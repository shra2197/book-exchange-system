import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {
    
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Integer val = super.get(key);
        return val == null ? -1 : val;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache lruCache= new LRUCache(3);
        lruCache.put(1,2);
        lruCache.put(2,3);
        lruCache.put(3,2);
        System.out.println(lruCache);
        System.out.println(lruCache.get(1));
        lruCache.put(4,2);
        System.out.println(lruCache);

    }
}