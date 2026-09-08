public class DataStructureDecisionReport {

    enum Requirement {

        INDEX_ACCESS,
        MIDDLE_INSERT,
        FIFO,
        LIFO,
        SORTED_RANGE,
        NEXT_PRIORITY,
        KEY_LOOKUP,
        KEY_UNIQUE,
        RELATION_TRAVERSAL,
        SHORTEST_PATH,
        CONNECTED_COMPONENTS,
        DEPENDENCY_ANALYSIS
    }

    public static String choose(Requirement requirement) {

        if (requirement == null) {
            return "UNKNOWN";
        }

        switch (requirement) {

            case INDEX_ACCESS:
                return "ArrayList";

            case MIDDLE_INSERT:
                return "LinkedList";

            case FIFO:
                return "ArrayDeque as Queue";

            case LIFO:
                return "ArrayDeque as Stack";

            case SORTED_RANGE:
                return "TreeMap / Balanced BST";

            case NEXT_PRIORITY:
                return "PriorityQueue / Heap";

            case KEY_LOOKUP:
                return "HashMap";

            case KEY_UNIQUE:
                return "HashSet";

            case RELATION_TRAVERSAL:
                return "Graph Adjacency List";

            case SHORTEST_PATH:
                return "Graph + BFS";

            case CONNECTED_COMPONENTS:
                return "Graph + BFS/DFS";

            case DEPENDENCY_ANALYSIS:
                return "Directed Graph + DFS";

            default:
                return "UNKNOWN";
        }
    }

    public static String getReason(Requirement requirement) {

        if (requirement == null) {
            return "沒有指定需求";
        }

        switch (requirement) {

            case INDEX_ACCESS:
                return "需要快速依index存取資料";

            case MIDDLE_INSERT:
                return "經常在中間插入或刪除資料";

            case FIFO:
                return "資料必須先進先出";

            case LIFO:
                return "資料必須後進先出";

            case SORTED_RANGE:
                return "需要排序與範圍查詢";

            case NEXT_PRIORITY:
                return "需要快速取得最高或最低優先權資料";

            case KEY_LOOKUP:
                return "需要依照key快速查詢";

            case KEY_UNIQUE:
                return "需要避免重複資料";

            case RELATION_TRAVERSAL:
                return "需要保存多對多關係並進行走訪";

            case SHORTEST_PATH:
                return "無權重Graph需要找最少edge";

            case CONNECTED_COMPONENTS:
                return "需要找出彼此連通的群組";

            case DEPENDENCY_ANALYSIS:
                return "需要分析有方向的相依關係";

            default:
                return "UNKNOWN";
        }
    }

    public static String getBigO(Requirement requirement) {

        if (requirement == null) {
            return "-";
        }

        switch (requirement) {

            case INDEX_ACCESS:
                return "get O(1)";

            case MIDDLE_INSERT:
                return "插入 O(n)";

            case FIFO:
                return "offer/poll O(1)";

            case LIFO:
                return "push/pop O(1)";

            case SORTED_RANGE:
                return "O(log n)";

            case NEXT_PRIORITY:
                return "peek O(1), add/remove O(log n)";

            case KEY_LOOKUP:
                return "平均 O(1)";

            case KEY_UNIQUE:
                return "平均 add/contains O(1)";

            case RELATION_TRAVERSAL:
                return "BFS/DFS O(V+E)";

            case SHORTEST_PATH:
                return "BFS O(V+E)";

            case CONNECTED_COMPONENTS:
                return "O(V+E)";

            case DEPENDENCY_ANALYSIS:
                return "DFS O(V+E)";

            default:
                return "-";
        }
    }

    public static void printReport(Requirement requirement) {

        if (requirement == null) {
            System.out.println("需求：null");
            System.out.println("選擇：UNKNOWN");
            System.out.println("理由：沒有指定需求");
            System.out.println("Big-O：-");
            return;
        }

        System.out.println("需求：" + requirement);
        System.out.println("選擇：" + choose(requirement));
        System.out.println("理由：" + getReason(requirement));
        System.out.println("主要Big-O：" + getBigO(requirement));
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("資料結構選擇報告\n");

        for (Requirement requirement : Requirement.values()) {
            printReport(requirement);
        }

        System.out.println("Missing Case：");
        printReport(null);
    }
}