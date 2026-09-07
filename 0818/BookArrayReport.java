class Book {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Book(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getTotalValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return "書號：" + id
                + "，書名：" + name
                + "，價格：" + price
                + "，庫存：" + stock;
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
                new Book("B001", "Java", 500, 10),
                new Book("B002", "資料結構", 650, 3),
                new Book("B003", "演算法", 800, 5),
                new Book("B004", "資料庫系統", 700, 2)
        };

        System.out.println("所有書籍：");

        for (Book book : books) {
            System.out.println(book);
        }

        int totalValue = 0;

        for (Book book : books) {
            totalValue += book.getTotalValue();
        }

        System.out.println();
        System.out.println("庫存總價值：" + totalValue);

        Book highestPriceBook = books[0];

        for (Book book : books) {
            if (book.getPrice() > highestPriceBook.getPrice()) {
                highestPriceBook = book;
            }
        }

        System.out.println();
        System.out.println("價格最高的書：");
        System.out.println(highestPriceBook);

        System.out.println();
        System.out.println("庫存小於或等於3的書：");

        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}