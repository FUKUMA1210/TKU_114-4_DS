import java.util.ArrayList;
import java.util.List;

public class LibraryBookBst {

    static class Book {
        String isbn;
        String title;
        String author;
        boolean available;

        Book(
                String isbn,
                String title,
                String author) {

            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public String toString() {

            String status;

            if (available) {
                status = "可借閱";
            } else {
                status = "已借出";
            }

            return isbn
                    + " "
                    + title
                    + " 作者：" + author
                    + " 狀態：" + status;
        }
    }

    static class Node {
        Book data;
        Node left;
        Node right;

        Node(Book data) {
            this.data = data;
        }
    }

    static class Bst {
        Node root;

        boolean add(Book book) {

            if (book == null) {
                return false;
            }

            if (book.isbn == null
                    || book.isbn.trim().isEmpty()) {
                return false;
            }

            if (root == null) {
                root = new Node(book);
                return true;
            }

            Node current = root;

            while (true) {

                int compare =
                        book.isbn.compareTo(
                                current.data.isbn
                        );

                if (compare == 0) {
                    return false;
                }

                if (compare < 0) {

                    if (current.left == null) {
                        current.left =
                                new Node(book);
                        return true;
                    }

                    current = current.left;

                } else {

                    if (current.right == null) {
                        current.right =
                                new Node(book);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Book find(String isbn) {

            Node current = root;

            while (current != null) {

                int compare =
                        isbn.compareTo(
                                current.data.isbn
                        );

                if (compare == 0) {
                    return current.data;
                }

                if (compare < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean borrow(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            if (!book.available) {
                return false;
            }

            book.available = false;

            return true;
        }

        boolean returnBook(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            if (book.available) {
                return false;
            }

            book.available = true;

            return true;
        }

        boolean remove(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            if (!book.available) {
                return false;
            }

            root = remove(root, isbn);

            return true;
        }

        Node remove(
                Node node,
                String isbn) {

            if (node == null) {
                return null;
            }

            int compare =
                    isbn.compareTo(
                            node.data.isbn
                    );

            if (compare < 0) {

                node.left =
                        remove(
                                node.left,
                                isbn
                        );

            } else if (compare > 0) {

                node.right =
                        remove(
                                node.right,
                                isbn
                        );

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        minimum(node.right);

                node.data = successor.data;

                node.right =
                        remove(
                                node.right,
                                successor.data.isbn
                        );
            }

            return node;
        }

        Node minimum(Node node) {

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        List<Book> range(
                String low,
                String high) {

            List<Book> result =
                    new ArrayList<>();

            if (low.compareTo(high) > 0) {
                return result;
            }

            range(
                    root,
                    low,
                    high,
                    result
            );

            return result;
        }

        void range(
                Node node,
                String low,
                String high,
                List<Book> result) {

            if (node == null) {
                return;
            }

            String isbn =
                    node.data.isbn;

            if (low.compareTo(isbn) < 0) {

                range(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (low.compareTo(isbn) <= 0
                    && isbn.compareTo(high) <= 0) {

                result.add(node.data);
            }

            if (isbn.compareTo(high) < 0) {

                range(
                        node.right,
                        low,
                        high,
                        result
                );
            }
        }

        List<Book> inorder() {

            List<Book> result =
                    new ArrayList<>();

            inorder(root, result);

            return result;
        }

        void inorder(
                Node node,
                List<Book> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.data);

            inorder(node.right, result);
        }
    }

    public static void main(String[] args) {

        Bst library = new Bst();

        System.out.println(
                "新增圖書："
                + library.add(
                        new Book(
                                "978001",
                                "Java",
                                "AA"
                        )
                )
        );

        System.out.println(
                "新增圖書："
                + library.add(
                        new Book(
                                "978003",
                                "C++",
                                "BB"
                        )
                )
        );

        System.out.println(
                "新增圖書："
                + library.add(
                        new Book(
                                "978002",
                                "Python",
                                "CC"
                        )
                )
        );

        System.out.println(
                "新增重複ISBN："
                + library.add(
                        new Book(
                                "978001",
                                "其他書籍",
                                "其他作者"
                        )
                )
        );

        System.out.println(
                "尋找圖書："
                + library.find("978002")
        );

        System.out.println(
                "借閱圖書："
                + library.borrow("978002")
        );

        System.out.println(
                "再次借閱："
                + library.borrow("978002")
        );

        System.out.println(
                "刪除已借出的書："
                + library.remove("978002")
        );

        System.out.println(
                "歸還圖書："
                + library.returnBook("978002")
        );

        System.out.println(
                "刪除圖書："
                + library.remove("978002")
        );

        System.out.println();
        System.out.println("ISBN範圍查詢：");

        for (Book book :
                library.range(
                        "978001",
                        "978003")) {

            System.out.println(book);
        }

        System.out.println();
        System.out.println("依ISBN排序的圖書報表：");

        for (Book book :
                library.inorder()) {

            System.out.println(book);
        }
    }
}