import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebsiteLinkGraph {

    private Map<String, Set<String>> graph;

    public WebsiteLinkGraph() {
        graph = new LinkedHashMap<>();
    }

    public void addPage(String page) {

        if (!graph.containsKey(page)) {
            graph.put(page, new LinkedHashSet<>());
        }
    }

    public void addLink(String from, String to) {

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {

            System.out.println("網站不存在");
            return;
        }

        graph.get(from).add(to);
    }

    public List<String> outgoingLinks(String page) {

        if (!graph.containsKey(page)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(graph.get(page));
    }

    public int incomingCount(String page) {

        int count = 0;

        for (String from : graph.keySet()) {

            if (graph.get(from).contains(page)) {
                count++;
            }
        }

        return count;
    }

    public List<String> noIncomingPages() {

        List<String> result = new ArrayList<>();

        for (String page : graph.keySet()) {

            if (incomingCount(page) == 0) {
                result.add(page);
            }
        }

        return result;
    }

    public List<String> noOutgoingPages() {

        List<String> result = new ArrayList<>();

        for (String page : graph.keySet()) {

            if (graph.get(page).isEmpty()) {
                result.add(page);
            }
        }

        return result;
    }

    public void report() {

        System.out.println("===== Website Links =====");

        for (String page : graph.keySet()) {
            System.out.println(
                    page + " : " + graph.get(page)
            );
        }
    }

    public static void main(String[] args) {

        WebsiteLinkGraph website =
                new WebsiteLinkGraph();

        website.addPage("首頁");
        website.addPage("商品");
        website.addPage("購物車");
        website.addPage("會員");
        website.addPage("關於我們");

        website.addLink("首頁", "商品");
        website.addLink("首頁", "會員");
        website.addLink("商品", "購物車");
        website.addLink("會員", "購物車");

        website.report();

        System.out.println();

        System.out.println("首頁outgoing：" +
                website.outgoingLinks("首頁"));

        System.out.println("購物車incoming：" +
                website.incomingCount("購物車"));

        System.out.println("沒有incoming的頁面：" +
                website.noIncomingPages());

        System.out.println("沒有outgoing的頁面：" +
                website.noOutgoingPages());
    }
}