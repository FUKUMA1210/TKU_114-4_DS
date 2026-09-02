import java.util.LinkedHashMap;
import java.util.Map;

public class LogisticsWeightedGraph {

    private Map<String, Map<String, Integer>> graph;

    public LogisticsWeightedGraph() {
        graph = new LinkedHashMap<>();
    }

    public void addVertex(String vertex) {

        if (!graph.containsKey(vertex)) {
            graph.put(vertex,
                    new LinkedHashMap<>());
        }
    }

    public boolean addEdge(
            String from,
            String to,
            int weight) {

        if (weight < 0) {
            System.out.println("權重不可為負數");
            return false;
        }

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {

            System.out.println("Vertex不存在");
            return false;
        }

        graph.get(from).put(to, weight);

        return true;
    }

    public boolean removeEdge(
            String from,
            String to) {

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {

            System.out.println("Vertex不存在");
            return false;
        }

        if (graph.get(from).containsKey(to)) {
            graph.get(from).remove(to);
            return true;
        }

        return false;
    }

    public Integer getWeight(
            String from,
            String to) {

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {

            System.out.println("Vertex不存在");
            return null;
        }

        return graph.get(from).get(to);
    }

    public void report() {

        System.out.println("===== 物流成本網路 =====");

        for (String from : graph.keySet()) {

            System.out.println(
                    from + " : " + graph.get(from)
            );
        }
    }

    public static void main(String[] args) {

        LogisticsWeightedGraph graph =
                new LogisticsWeightedGraph();

        graph.addVertex("台北倉庫");
        graph.addVertex("桃園倉庫");
        graph.addVertex("台中倉庫");
        graph.addVertex("高雄倉庫");

        graph.addEdge(
                "台北倉庫",
                "桃園倉庫",
                500
        );

        graph.addEdge(
                "桃園倉庫",
                "台中倉庫",
                800
        );

        graph.addEdge(
                "台中倉庫",
                "高雄倉庫",
                1200
        );

        graph.addEdge(
                "台北倉庫",
                "台中倉庫",
                1000
        );

        System.out.println("原本資料：");
        graph.report();

        System.out.println();

        System.out.println("更新台北到台中成本");

        graph.addEdge(
                "台北倉庫",
                "台中倉庫",
                900
        );

        graph.report();

        System.out.println();

        System.out.println(
                "台北到台中成本：" +
                        graph.getWeight(
                                "台北倉庫",
                                "台中倉庫"
                        )
        );

        System.out.println();

        System.out.println(
                "刪除桃園到台中：" +
                        graph.removeEdge(
                                "桃園倉庫",
                                "台中倉庫"
                        )
        );

        graph.report();

        System.out.println();

        graph.addEdge(
                "台北倉庫",
                "不存在",
                500
        );

        graph.addEdge(
                "台北倉庫",
                "高雄倉庫",
                -100
        );
    }
}