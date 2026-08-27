class Student {
    String studentId;
    String name;

    Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String toString() {
        return studentId + " " + name;
    }
}

class StudentNode {
    Student data;
    StudentNode left;
    StudentNode right;

    StudentNode(Student data) {
        this.data = data;
    }
}

class StudentBst {
    private StudentNode root;

    boolean insert(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new StudentNode(student);
            return true;
        }

        StudentNode current = root;

        while (true) {
            if (student.studentId.equals(current.data.studentId)) {
                return false;
            }

            if (student.studentId.compareTo(current.data.studentId) < 0) {
                if (current.left == null) {
                    current.left = new StudentNode(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new StudentNode(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Student search(String studentId) {
        StudentNode current = root;

        while (current != null) {
            int result = studentId.compareTo(current.data.studentId);

            if (result == 0) {
                return current.data;
            }

            if (result < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    boolean delete(String studentId) {
        if (search(studentId) == null) {
            return false;
        }

        root = delete(root, studentId);
        return true;
    }

    private StudentNode delete(StudentNode node, String studentId) {
        if (node == null) {
            return null;
        }

        int result = studentId.compareTo(node.data.studentId);

        if (result < 0) {
            node.left = delete(node.left, studentId);
        } else if (result > 0) {
            node.right = delete(node.right, studentId);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            StudentNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data.studentId);
        }

        return node;
    }

    private StudentNode minimumNode(StudentNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(StudentNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.data + " | ");
        inorder(node.right);
    }
}

public class StudentBstIndex {
    public static void main(String[] args) {
        StudentBst tree = new StudentBst();

        System.out.println(tree.insert(new Student("411630001", "小明")));
        System.out.println(tree.insert(new Student("411630003", "小華")));
        System.out.println(tree.insert(new Student("411630002", "小美")));
        System.out.println(tree.insert(new Student("411630004", "小安")));

        System.out.println("重複：" +
                tree.insert(new Student("411630002", "重複學生")));

        System.out.println("Inorder：");
        tree.inorder();

        System.out.println("查詢 411630002：" +
                tree.search("411630002"));

        System.out.println("查詢 411630999：" +
                tree.search("411630999"));

        System.out.println("刪除 411630003：" +
                tree.delete("411630003"));

        System.out.println("刪除後：");
        tree.inorder();
    }
}