import java.util.ArrayList;
import java.util.List;

public class BookIsbnHashTable {

    private static class Book {
        private String isbn;
        private String title;

        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String toString() {
            return isbn + "=" + title;
        }
    }

    private List<List<Book>> buckets;
    private int size;

    public BookIsbnHashTable(int bucketCount) {
        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        size = 0;
    }

    private int index(String isbn) {
        return Math.floorMod(isbn.hashCode(), buckets.size());
    }

    public void put(String isbn, String title) {
        List<Book> bucket = buckets.get(index(isbn));

        for (Book book : bucket) {
            if (book.isbn.equals(isbn)) {
                book.title = title;
                return;
            }
        }

        bucket.add(new Book(isbn, title));
        size++;
    }

    public String get(String isbn) {
        List<Book> bucket = buckets.get(index(isbn));

        for (Book book : bucket) {
            if (book.isbn.equals(isbn)) {
                return book.title;
            }
        }

        return null;
    }

    public boolean remove(String isbn) {
        List<Book> bucket = buckets.get(index(isbn));

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).isbn.equals(isbn)) {
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

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    public void bucketReport() {
        System.out.println("===== Bucket Report =====");

        for (int i = 0; i < buckets.size(); i++) {
            System.out.println("Bucket " + i + " : " + buckets.get(i));
        }
    }

    public static void main(String[] args) {

        BookIsbnHashTable table = new BookIsbnHashTable(5);

        table.put("978001", "Java");
        table.put("978002", "資料結構");
        table.put("978003", "演算法");
        table.put("978004", "資料庫");

        table.put("978001", "Java第2版");

        table.bucketReport();

        System.out.println();
        System.out.println("搜尋978001：" + table.get("978001"));
        System.out.println("目前書籍數量：" + table.size());
        System.out.println("Load Factor：" + table.loadFactor());

        System.out.println();
        System.out.println("刪除978002：" + table.remove("978002"));
        System.out.println("目前書籍數量：" + table.size());

        table.bucketReport();
    }
}