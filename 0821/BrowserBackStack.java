import java.util.ArrayDeque;
import java.util.Deque;

class BrowserHistory {
    private Deque<String> stack = new ArrayDeque<>();

    public void visit(String url) {
        if (url == null || url.trim().isEmpty()) return;
        stack.push(url);
        System.out.println("訪問網址: " + url);
    }

    public void back() {
        if (stack.size() > 1) {
            stack.pop();
            System.out.println("返回上一頁，當前頁面: " + current());
        } else if (stack.size() == 1) {
            System.out.println("已是首頁，無法再返回，當前頁面: " + current());
        } else {
            System.out.println("目前沒有瀏覽紀錄。");
        }
    }

    public String current() {
        if (stack.isEmpty()) {
            return "無";
        }
        return stack.peek();
    }
}

public class BrowserBackStack {
    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();
        history.visit("google.com");
        history.visit("github.com");
        history.visit("stackoverflow.com");
        history.visit("youtube.com");
        history.visit("chatgpt.com");

        System.out.println("--- 開始連續返回操作 ---");
        history.back();
        history.back();
        history.back();
        history.back();
        history.back();
    }
}