import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {

    private List<String> vertices;
    private boolean[][] matrix;
    private int edgeCount;

    public CampusMatrixGraph(List<String> vertices) {
        this.vertices = new ArrayList<>(vertices);
        matrix = new boolean[vertices.size()][vertices.size()];
    }

    private int indexOf(String vertex) {
        return vertices.indexOf(vertex);
    }

    public boolean addEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (a == -1 || b == -1 || a == b) {
            return false;
        }

        if (matrix[a][b]) {
            return false;
        }

        matrix[a][b] = true;
        matrix[b][a] = true;

        edgeCount++;

        return true;
    }

    public boolean removeEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (a == -1 || b == -1) {
            return false;
        }

        if (!matrix[a][b]) {
            return false;
        }

        matrix[a][b] = false;
        matrix[b][a] = false;

        edgeCount--;

        return true;
    }

    public int degree(String vertex) {
        int index = indexOf(vertex);

        if (index == -1) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[index][i]) {
                count++;
            }
        }

        return count;
    }

    public List<String> neighbors(String vertex) {
        List<String> result = new ArrayList<>();

        int index = indexOf(vertex);

        if (index == -1) {
            return result;
        }

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[index][i]) {
                result.add(vertices.get(i));
            }
        }

        return result;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public static void main(String[] args) {
        List<String> places = new ArrayList<>();

        places.add("商館");
        places.add("圖書館");
        places.add("驚聲大樓");
        places.add("宮燈教室");

        CampusMatrixGraph graph = new CampusMatrixGraph(places);

        graph.addEdge("商館", "圖書館");
        graph.addEdge("商館", "驚聲大樓");
        graph.addEdge("圖書館", "宮燈教室");

        graph.addEdge("商館", "圖書館");

        System.out.println("商館的旁邊：" + graph.neighbors("商館"));
        System.out.println("商館的degree：" + graph.degree("商館"));
        System.out.println("edge數量：" + graph.getEdgeCount());

        graph.removeEdge("商館", "圖書館");

        System.out.println("\n移除商館與圖書館的連線後：");
        System.out.println("商館的旁邊：" + graph.neighbors("商館"));
        System.out.println("edge數量：" + graph.getEdgeCount());
    }
}