public class IntegratedStructureAudit {

    public enum Structure {
        LIST,
        QUEUE,
        BST,
        HEAP,
        HASH_TABLE,
        GRAPH
    }

    public enum Requirement {
        INDEX_ACCESS,
        FIFO_PROCESS,
        SORTED_RANGE,
        HIGHEST_PRIORITY,
        KEY_LOOKUP,
        RELATION_TRAVERSAL
    }

    public static boolean isReasonable(
            Structure structure,
            Requirement requirement) {

        if (requirement == Requirement.INDEX_ACCESS) {
            return structure == Structure.LIST;
        }

        if (requirement == Requirement.FIFO_PROCESS) {
            return structure == Structure.QUEUE;
        }

        if (requirement == Requirement.SORTED_RANGE) {
            return structure == Structure.BST;
        }

        if (requirement == Requirement.HIGHEST_PRIORITY) {
            return structure == Structure.HEAP;
        }

        if (requirement == Requirement.KEY_LOOKUP) {
            return structure == Structure.HASH_TABLE;
        }

        if (requirement == Requirement.RELATION_TRAVERSAL) {
            return structure == Structure.GRAPH;
        }

        return false;
    }

    public static String expectedStructure(
            Requirement requirement) {

        if (requirement == Requirement.INDEX_ACCESS) {
            return "List";
        }

        if (requirement == Requirement.FIFO_PROCESS) {
            return "Queue";
        }

        if (requirement == Requirement.SORTED_RANGE) {
            return "BST";
        }

        if (requirement == Requirement.HIGHEST_PRIORITY) {
            return "Heap";
        }

        if (requirement == Requirement.KEY_LOOKUP) {
            return "Hash Table";
        }

        if (requirement == Requirement.RELATION_TRAVERSAL) {
            return "Graph";
        }

        return "Unknown";
    }

    public static void audit(
            String scenario,
            Structure structure,
            Requirement requirement) {

        System.out.println("情境：" + scenario);
        System.out.println("使用結構：" + structure);
        System.out.println("主要需求：" + requirement);

        if (isReasonable(structure, requirement)) {

            System.out.println(
                    "診斷結果：合理"
            );

        } else {

            System.out.println(
                    "診斷結果：不合理"
            );

            System.out.println(
                    "建議使用：" +
                            expectedStructure(requirement)
            );
        }

        System.out.println("------------------------");
    }

    public static void main(String[] args) {

        audit(
                "需要快速依照索引取得資料",
                Structure.LIST,
                Requirement.INDEX_ACCESS
        );

        audit(
                "客服案件依照先來先服務",
                Structure.QUEUE,
                Requirement.FIFO_PROCESS
        );

        audit(
                "需要查詢指定範圍的排序資料",
                Structure.BST,
                Requirement.SORTED_RANGE
        );

        audit(
                "每次都要取得最高優先工作",
                Structure.HEAP,
                Requirement.HIGHEST_PRIORITY
        );

        audit(
                "依照學號快速查詢學生",
                Structure.HASH_TABLE,
                Requirement.KEY_LOOKUP
        );

        audit(
                "分析朋友之間的關係",
                Structure.GRAPH,
                Requirement.RELATION_TRAVERSAL
        );

        audit(
                "依照ID快速查詢資料",
                Structure.LIST,
                Requirement.KEY_LOOKUP
        );

        audit(
                "每次取得最高優先工作",
                Structure.QUEUE,
                Requirement.HIGHEST_PRIORITY
        );

        audit(
                "分析城市道路是否連通",
                Structure.BST,
                Requirement.RELATION_TRAVERSAL
        );
    }
}