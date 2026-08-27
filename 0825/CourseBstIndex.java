import java.util.ArrayList;
import java.util.List;

public class CourseBstIndex {

    static class Course {
        String courseCode;
        String name;
        int credit;

        Course(String courseCode, String name, int credit) {
            this.courseCode = courseCode;
            this.name = name;
            this.credit = credit;
        }

        public String toString() {
            return courseCode + " " + name + " 學分=" + credit;
        }
    }

    static class Node {
        Course data;
        Node left;
        Node right;

        Node(Course data) {
            this.data = data;
        }
    }

    static class Bst {
        Node root;

        boolean add(Course course) {
            if (course == null) {
                return false;
            }

            if (course.courseCode == null
                    || course.courseCode.trim().isEmpty()) {
                return false;
            }

            if (course.credit < 1 || course.credit > 6) {
                return false;
            }

            if (root == null) {
                root = new Node(course);
                return true;
            }

            Node current = root;

            while (true) {
                int compare = course.courseCode
                        .compareTo(current.data.courseCode);

                if (compare == 0) {
                    return false;
                }

                if (compare < 0) {
                    if (current.left == null) {
                        current.left = new Node(course);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(course);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Course find(String code) {
            Node current = root;

            while (current != null) {
                int compare = code.compareTo(current.data.courseCode);

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

        boolean updateCredit(String code, int credit) {
            if (credit < 1 || credit > 6) {
                return false;
            }

            Course course = find(code);

            if (course == null) {
                return false;
            }

            course.credit = credit;
            return true;
        }

        boolean remove(String code) {
            if (find(code) == null) {
                return false;
            }

            root = remove(root, code);
            return true;
        }

        Node remove(Node node, String code) {

            if (node == null) {
                return null;
            }

            int compare = code.compareTo(node.data.courseCode);

            if (compare < 0) {
                node.left = remove(node.left, code);
            } else if (compare > 0) {
                node.right = remove(node.right, code);
            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor = minimumNode(node.right);

                node.data = successor.data;

                node.right = remove(
                        node.right,
                        successor.data.courseCode
                );
            }

            return node;
        }

        Node minimumNode(Node node) {
            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        List<Course> range(String low, String high) {
            List<Course> result = new ArrayList<>();

            if (low.compareTo(high) > 0) {
                return result;
            }

            range(root, low, high, result);

            return result;
        }

        void range(Node node, String low, String high,
                   List<Course> result) {

            if (node == null) {
                return;
            }

            String code = node.data.courseCode;

            if (low.compareTo(code) < 0) {
                range(node.left, low, high, result);
            }

            if (low.compareTo(code) <= 0
                    && code.compareTo(high) <= 0) {
                result.add(node.data);
            }

            if (code.compareTo(high) < 0) {
                range(node.right, low, high, result);
            }
        }

        List<Course> inorder() {
            List<Course> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        void inorder(Node node, List<Course> result) {
            if (node == null) {
                return;
            }

            inorder(node.left, result);
            result.add(node.data);
            inorder(node.right, result);
        }
    }

    public static void main(String[] args) {

        Bst index = new Bst();

        System.out.println("新增課程："
                + index.add(new Course("A", "AAAA", 3)));

        System.out.println("新增課程："
                + index.add(new Course("S", "SSSS", 3)));

        System.out.println("新增課程："
                + index.add(new Course("D", "DDDD", 3)));

        System.out.println("新增課程："
                + index.add(new Course("C", "CC", 4)));

        System.out.println("重複課程代碼："
                + index.add(new Course("A", "FFF", 2)));

        System.out.println("錯誤學分："
                + index.add(new Course("W", "WWW", 7)));

        System.out.println("尋找課程：" + index.find("S"));

        System.out.println("修改學分："
                + index.updateCredit("S", 4));

        System.out.println();
        System.out.println("range:");

        for (Course course : index.range("A", "S")) {
            System.out.println(course);
        }

        System.out.println();
        System.out.println("排序後課程:");

        for (Course course : index.inorder()) {
            System.out.println(course);
        }

        System.out.println("刪除："
                + index.remove("D"));

        System.out.println("不存在的課程："
                + index.remove("XXX"));
    }
}