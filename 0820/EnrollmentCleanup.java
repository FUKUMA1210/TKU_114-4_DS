import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Amy");
        names.add("Ben");
        names.add("");
        names.add("Amy");
        names.add(null);
        names.add("Eric");
        names.add("Ben");
        names.add("   ");

        System.out.println("清理前：");
        System.out.println(names);

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();

            if (name == null || name.isBlank()) {
                iterator.remove();
            }
        }

        System.out.println("清理後：");
        System.out.println(names);

        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (String name : names) {

            if (!uniqueNames.add(name)) {
                duplicateNames.add(name);
            }
        }

        System.out.println("重複姓名：");
        System.out.println(duplicateNames);
    }
}