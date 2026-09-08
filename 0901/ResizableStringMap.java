import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap {

    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    private List<List<Entry>> buckets;
    private int size;

    public ResizableStringMap(int bucketCount) {
        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int index(String key) {
        return Math.floorMod(key.hashCode(), buckets.size());
    }

    public void put(String key, String value) {
        int index = index(key);
        List<Entry> chain = buckets.get(index);

        for (Entry entry : chain) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        chain.add(new Entry(key, value));
        size++;

        if (loadFactor() > 0.75) {
            resize();
        }
    }

    public String get(String key) {
        int index = index(key);

        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(String key) {
        int index = index(key);
        List<Entry> chain = buckets.get(index);

        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)) {
                chain.remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    private void resize() {
        int newBucketCount = buckets.size() * 2 + 1;

        List<List<Entry>> oldBuckets = buckets;

        buckets = new ArrayList<>();

        for (int i = 0; i < newBucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (List<Entry> chain : oldBuckets) {
            for (Entry entry : chain) {
                int index = index(entry.key);
                buckets.get(index).add(entry);
            }
        }
    }

    public void printBuckets() {
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println(i + " -> " + buckets.get(i));
        }
    }

    public static void main(String[] args) {
        ResizableStringMap map = new ResizableStringMap(3);

        map.put("A01", "蘋果");
        map.put("A02", "香蕉");
        map.put("A03", "葡萄");
        map.put("A04", "西瓜");

        System.out.println("資料數量：" + map.size());
        System.out.println("A02：" + map.get("A02"));

        map.put("A02", "芒果");
        System.out.println("更新後A02：" + map.get("A02"));

        System.out.println("刪除A03：" + map.remove("A03"));

        System.out.println("\n目前bucket：");
        map.printBuckets();

        System.out.println("Load factor：" + map.loadFactor());
    }
}