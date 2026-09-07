import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    private Deque<String> history = new ArrayDeque<>();

    public void visit(String page) {
        if (page == null) {
            return;
        }

        history.push(page);
        System.out.println("瀏覽：" + page);
    }

    public String back() {
        String page = history.pollFirst();

        if (page == null) {
            System.out.println("沒有上一頁");
            return null;
        }

        System.out.println("返回：" + page);
        return page;
    }

    public String current() {
        String page = history.peekFirst();

        if (page == null) {
            System.out.println("目前沒有頁面");
            return null;
        }

        System.out.println("目前頁面：" + page);
        return page;
    }

    public static void main(String[] args) {

        BrowserBackStack browser = new BrowserBackStack();

        browser.visit("Google");
        browser.visit("YouTube");
        browser.current();

        browser.visit("GitHub");
        browser.current();

        browser.back();
        browser.current();

        browser.back();
        browser.current();

        browser.back();
        browser.current();

        browser.back();
    }
}