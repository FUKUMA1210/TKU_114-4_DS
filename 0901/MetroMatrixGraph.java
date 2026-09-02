import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {

    private String[] stations;
    private boolean[][] matrix;

    public MetroMatrixGraph(String[] stations) {
        this.stations = stations;

        matrix = new boolean[stations.length][stations.length];
    }

    private int indexOf(String station) {
        for (int i = 0; i < stations.length; i++) {
            if (stations[i].equals(station)) {
                return i;
            }
        }

        return -1;
    }

    public void addEdge(String station1, String station2) {

        int a = indexOf(station1);
        int b = indexOf(station2);

        if (a == -1 || b == -1) {
            System.out.println("站名不存在");
            return;
        }

        matrix[a][b] = true;
        matrix[b][a] = true;
    }

    public List<String> neighbors(String station) {

        List<String> result = new ArrayList<>();

        int index = indexOf(station);

        if (index == -1) {
            return result;
        }

        for (int i = 0; i < stations.length; i++) {
            if (matrix[index][i]) {
                result.add(stations[i]);
            }
        }

        return result;
    }

    public int degree(String station) {

        int index = indexOf(station);

        if (index == -1) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < stations.length; i++) {
            if (matrix[index][i]) {
                count++;
            }
        }

        return count;
    }

    public int edgeCount() {

        int count = 0;

        for (int i = 0; i < stations.length; i++) {
            for (int j = i + 1; j < stations.length; j++) {
                if (matrix[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    public void matrixReport() {

        System.out.println("===== Metro Matrix =====");

        System.out.print("       ");

        for (String station : stations) {
            System.out.print(station + " ");
        }

        System.out.println();

        for (int i = 0; i < stations.length; i++) {

            System.out.print(stations[i] + " ");

            for (int j = 0; j < stations.length; j++) {

                if (matrix[i][j]) {
                    System.out.print("  1  ");
                } else {
                    System.out.print("  0  ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        String[] stations = {
                "A", "B", "C", "D", "E"
        };

        MetroMatrixGraph metro =
                new MetroMatrixGraph(stations);

        metro.addEdge("A", "B");
        metro.addEdge("A", "C");
        metro.addEdge("B", "D");
        metro.addEdge("C", "D");
        metro.addEdge("D", "E");

        System.out.println("A的鄰站：" +
                metro.neighbors("A"));

        System.out.println("D的鄰站：" +
                metro.neighbors("D"));

        System.out.println("A的Degree：" +
                metro.degree("A"));

        System.out.println("D的Degree：" +
                metro.degree("D"));

        System.out.println("Edge總數：" +
                metro.edgeCount());

        System.out.println();

        metro.matrixReport();
    }
}