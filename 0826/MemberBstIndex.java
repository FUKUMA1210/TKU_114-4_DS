public class MemberBstIndex {

    static class Member {

        private int memberId;
        private String name;
        private String email;

        public Member(
                int memberId,
                String name,
                String email) {

            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        public int getMemberId() {
            return memberId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {

            return "Member{id="
                    + memberId
                    + ", name='"
                    + name
                    + "', email='"
                    + email
                    + "'}";
        }
    }


    static class Node {

        Member member;

        Node left;
        Node right;

        Node(Member member) {
            this.member = member;
        }
    }

    private Node root;


    public boolean add(Member member) {

        if (member == null) {
            return false;
        }

        if (member.getEmail() == null
                || member.getEmail().trim().isEmpty()) {

            return false;
        }

        if (root == null) {

            root = new Node(member);

            return true;
        }

        Node current = root;

        while (true) {

            int id =
                    member.getMemberId();

            int currentId =
                    current.member.getMemberId();

            if (id == currentId) {
                return false;
            }

            if (id < currentId) {

                if (current.left == null) {

                    current.left =
                            new Node(member);

                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {

                    current.right =
                            new Node(member);

                    return true;
                }

                current = current.right;
            }
        }
    }


    public Member find(int memberId) {

        Node current = root;

        while (current != null) {

            int currentId =
                    current.member.getMemberId();

            if (memberId == currentId) {

                return current.member;
            }

            if (memberId < currentId) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }


    public boolean updateEmail(
            int memberId,
            String newEmail) {

        if (newEmail == null
                || newEmail.trim().isEmpty()) {

            return false;
        }

        Member member = find(memberId);

        if (member == null) {
            return false;
        }

        member.setEmail(newEmail);

        return true;
    }


    public boolean remove(int memberId) {

        if (find(memberId) == null) {
            return false;
        }

        root = remove(root, memberId);

        return true;
    }

    private Node remove(
            Node node,
            int memberId) {

        if (memberId
                < node.member.getMemberId()) {

            node.left =
                    remove(node.left, memberId);

        } else if (memberId
                > node.member.getMemberId()) {

            node.right =
                    remove(node.right, memberId);

        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor =
                    findMin(node.right);

            node.member =
                    successor.member;

            node.right =
                    remove(
                            node.right,
                            successor.member.getMemberId());
        }

        return node;
    }

    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }


    public void inorderReport() {

        System.out.println("===== Member Report =====");

        inorderReport(root);
    }

    private void inorderReport(Node node) {

        if (node == null) {
            return;
        }

        inorderReport(node.left);

        System.out.println(node.member);

        inorderReport(node.right);
    }


    public static void main(String[] args) {

        MemberBstIndex index =
                new MemberBstIndex();


        System.out.println(
                "新增會員："
                        + index.add(
                                new Member(
                                        1003,
                                        "小明",
                                        "ming@gmail.com")));

        System.out.println(
                "新增會員："
                        + index.add(
                                new Member(
                                        1001,
                                        "小華",
                                        "hua@gmail.com")));

        System.out.println(
                "新增會員："
                        + index.add(
                                new Member(
                                        1005,
                                        "小美",
                                        "mei@gmail.com")));

        System.out.println(
                "新增會員："
                        + index.add(
                                new Member(
                                        1002,
                                        "小安",
                                        "an@gmail.com")));

        System.out.println();
        System.out.println(
                "重複 Id："
                        + index.add(
                                new Member(
                                        1001,
                                        "測試",
                                        "test@gmail.com")));


        System.out.println(
                "空 Email："
                        + index.add(
                                new Member(
                                        1004,
                                        "測試",
                                        " ")));


        System.out.println("\n搜尋會員：");

        Member member =
                index.find(1002);

        System.out.println(member);


        System.out.println("\n修改 Email：");

        System.out.println(
                index.updateEmail(
                        1002,
                        "newemail@gmail.com"));

        System.out.println(
                index.find(1002));


        System.out.println("\n刪除會員：");

        System.out.println(
                index.remove(1003));


        System.out.println();

        index.inorderReport();
    }
}