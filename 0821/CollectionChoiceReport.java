import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionChoiceReport {

    public static void main(String[] args) {

        System.out.println("=== 集合選擇報告與實作 ===");
        System.out.println();
        
        List<String> searchHistory = new ArrayList<>();

        searchHistory.add("Java");
        searchHistory.add("Data Structure");
        searchHistory.add("Java");

        System.out.println("1. 搜尋紀錄");
        System.out.println("interface：List");
        System.out.println("implementation：ArrayList");
        System.out.println("結果：" + searchHistory);
        System.out.println();

        Set<String> memberIds = new LinkedHashSet<>();

        memberIds.add("M01");
        memberIds.add("M02");
        memberIds.add("M01");

        System.out.println("2. 會員編號");
        System.out.println("interface：Set");
        System.out.println("implementation：LinkedHashSet");
        System.out.println("結果：" + memberIds);
        System.out.println();

        Map<String, Integer> scores = new LinkedHashMap<>();

        scores.put("S01", 85);
        scores.put("S02", 92);
        scores.put("S03", 78);

        System.out.println("3. 學生成績");
        System.out.println("interface：Map");
        System.out.println("implementation：LinkedHashMap");
        System.out.println("查詢 S02：" + scores.get("S002"));
        System.out.println();

        Deque<String> printQueue = new ArrayDeque<>();

        printQueue.offerLast("文件A");
        printQueue.offerLast("文件B");
        printQueue.offerLast("文件C");

        System.out.println("4. 列印工作");
        System.out.println("interface：Deque");
        System.out.println("implementation：ArrayDeque");
        System.out.println("處理：" + printQueue.pollFirst());
        System.out.println("剩餘：" + printQueue);
        System.out.println();

        Deque<String> undoStack = new ArrayDeque<>();

        undoStack.push("輸入文字");
        undoStack.push("刪除文字");
        undoStack.push("修改文字");

        System.out.println("5. 復原最近操作");
        System.out.println("interface：Deque");
        System.out.println("implementation：ArrayDeque");
        System.out.println("Undo：" + undoStack.pollFirst());
        System.out.println("剩餘：" + undoStack);
    }
}