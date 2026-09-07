import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    static void addLast(List<Integer> list, int value) {
        list.add(value);
    }

    static void insert(List<Integer> list, int index, int value) {
        if (index < 0 || index > list.size()) {
            return;
        }

        list.add(index, value);
    }

    static int search(List<Integer> list, int value) {
        return list.indexOf(value);
    }

    static boolean remove(List<Integer> list, int value) {
        return list.remove(Integer.valueOf(value));
    }

    static int sum(List<Integer> list) {
        int total = 0;

        for (int value : list) {
            total += value;
        }

        return total;
    }

    static void testList(String name, List<Integer> list) {

        System.out.println(name);

        addLast(list, 10);
        addLast(list, 20);
        addLast(list, 30);

        System.out.println("尾端新增：" + list);

        insert(list, 1, 15);

        System.out.println("位置1插入15：" + list);

        System.out.println("搜尋20：" + search(list, 20));

        System.out.println("刪除20：" + remove(list, 20));
        System.out.println("刪除後：" + list);

        System.out.println("總和：" + sum(list));

        System.out.println();
    }

    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        testList("ArrayList", arrayList);
        testList("LinkedList", linkedList);

        System.out.println("兩者功能結果一致");
        System.out.println("ArrayList使用陣列，依index取得資料通常較快。");
        System.out.println("LinkedList使用節點連結，依index取得資料需要逐步走訪。");
        System.out.println("ArrayList中間插入或刪除可能需要搬移後面的資料。");
        System.out.println("LinkedList已找到節點位置後，插入或刪除只需要調整連結。");
    }
}