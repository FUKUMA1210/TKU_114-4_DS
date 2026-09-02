import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoginActivityReport {

    public static void main(String[] args) {

        String[][] logs = {
                {"user01", "192.168.1.1"},
                {"user02", "192.168.1.2"},
                {"user01", "192.168.1.3"},
                {"user03", "192.168.1.1"},
                {"user02", "192.168.1.2"},
                {"user01", "192.168.1.1"},
                {"user04", "192.168.1.4"}
        };

        Map<String, Integer> loginCount = new HashMap<>();
        Set<String> ipSet = new HashSet<>();

        for (String[] log : logs) {
            String account = log[0];
            String ip = log[1];

            if (loginCount.containsKey(account)) {
                loginCount.put(account,
                        loginCount.get(account) + 1);
            } else {
                loginCount.put(account, 1);
            }

            ipSet.add(ip);
        }

        System.out.println("===== 每個帳號登入次數 =====");

        for (String account : loginCount.keySet()) {
            System.out.println(
                    account + "：" + loginCount.get(account) + " 次"
            );
        }

        System.out.println();
        System.out.println("不同IP數量：" + ipSet.size());

        System.out.println();
        System.out.println("===== 異常重複登入報告 =====");

        boolean found = false;

        for (String account : loginCount.keySet()) {
            if (loginCount.get(account) > 1) {
                System.out.println(
                        account + "重複登入"
                                + loginCount.get(account) + "次"
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有異常重複登入");
        }
    }
}