import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {

    private Map<String, Set<String>> users;

    public SocialNetworkGraph() {
        users = new HashMap<>();
    }

    public boolean addUser(String name) {
        if (users.containsKey(name)) {
            return false;
        }

        users.put(name, new HashSet<>());
        return true;
    }

    public boolean addFriend(String first, String second) {
        if (!users.containsKey(first) || !users.containsKey(second)) {
            return false;
        }

        if (first.equals(second)) {
            return false;
        }

        boolean added = users.get(first).add(second);

        if (added) {
            users.get(second).add(first);
        }

        return added;
    }

    public boolean removeFriend(String first, String second) {
        if (!users.containsKey(first) || !users.containsKey(second)) {
            return false;
        }

        if (!users.get(first).contains(second)) {
            return false;
        }

        users.get(first).remove(second);
        users.get(second).remove(first);

        return true;
    }

    public Set<String> mutualFriends(String first, String second) {
        Set<String> result = new HashSet<>();

        if (!users.containsKey(first) || !users.containsKey(second)) {
            return result;
        }

        result.addAll(users.get(first));
        result.retainAll(users.get(second));

        return result;
    }

    public List<String> getIsolatedUsers() {
        List<String> result = new ArrayList<>();

        for (String user : users.keySet()) {
            if (users.get(user).isEmpty()) {
                result.add(user);
            }
        }

        return result;
    }

    public void printFriends(String user) {
        if (!users.containsKey(user)) {
            System.out.println("找不到使用者");
            return;
        }

        System.out.println(user + "的好友：" + users.get(user));
    }

    public static void main(String[] args) {
        SocialNetworkGraph graph = new SocialNetworkGraph();

        graph.addUser("小明");
        graph.addUser("小華");
        graph.addUser("小美");
        graph.addUser("小王");
        graph.addUser("小陳");

        graph.addFriend("小明", "小華");
        graph.addFriend("小明", "小美");
        graph.addFriend("小華", "小美");
        graph.addFriend("小華", "小王");

        graph.printFriends("小明");
        graph.printFriends("小華");

        System.out.println("小明與小華的共友："
                + graph.mutualFriends("小明", "小華"));

        System.out.println("孤立使用者："
                + graph.getIsolatedUsers());

        graph.removeFriend("小明", "小華");

        System.out.println("\n解除好友後：");
        graph.printFriends("小明");
        graph.printFriends("小華");
    }
}