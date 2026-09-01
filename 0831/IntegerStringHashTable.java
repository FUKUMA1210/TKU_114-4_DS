import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {

    private static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    private List<List<Entry>> buckets;
    private int size;

    public IntegerStringHashTable(int bucketCount) {
        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        size = 0;
    }

    private int index(int key) {
        return Math.floorMod(key, buckets.size());
    }

    public void put(int key, String value) {
        List<Entry> bucket = buckets.get(index(key));

        for (Entry entry : bucket) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        List<Entry> bucket = buckets.get(index(key));

        for (Entry entry : bucket) {
            if (entry.key == key) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public boolean remove(int key) {
        List<Entry> bucket = buckets.get(index(key));

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key == key) {
                bucket.remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        System.out.println("Bucket報告：");

        for (int i = 0; i < buckets.size(); i++) {
            System.out.println(i + " = " + buckets.get(i));
        }
    }

    public static void main(String[] args) {

        IntegerStringHashTable table =
                new IntegerStringHashTable(5);

        table.put(12, "小明");
        table.put(7, "小華");
        table.put(22, "小美");
        table.put(-3, "阿強");
        table.put(7, "小華更新");

        table.bucketReport();

        System.out.println();
        System.out.println("get(7) = " + table.get(7));
        System.out.println("containsKey(22) = " + table.containsKey(22));
        System.out.println("目前 size = " + table.size());

        System.out.println();
        System.out.println("remove(12) = " + table.remove(12));
        System.out.println("目前 size = " + table.size());

        table.bucketReport();
    }
}