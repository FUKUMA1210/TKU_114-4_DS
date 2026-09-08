import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {

    public static Set<String> union(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>(first);
        result.addAll(second);

        return result;
    }

    public static Set<String> intersection(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>(first);
        result.retainAll(second);

        return result;
    }

    public static Set<String> firstOnly(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>(first);
        result.removeAll(second);

        return result;
    }

    public static Set<String> secondOnly(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>(second);
        result.removeAll(first);

        return result;
    }

    public static void main(String[] args) {
        Set<String> first = new HashSet<>();
        first.add("遊戲");
        first.add("音樂");
        first.add("電影");
        first.add("攝影");

        Set<String> second = new HashSet<>();
        second.add("音樂");
        second.add("電影");
        second.add("旅遊");
        second.add("運動");

        System.out.println("第一個人的興趣：" + first);
        System.out.println("第二個人的興趣：" + second);

        System.out.println("\n聯集：" + union(first, second));
        System.out.println("共同興趣：" + intersection(first, second));
        System.out.println("第一個人獨有：" + firstOnly(first, second));
        System.out.println("第二個人獨有：" + secondOnly(first, second));

        System.out.println("\n原本第一個集合：" + first);
        System.out.println("原本第二個集合：" + second);
    }
}