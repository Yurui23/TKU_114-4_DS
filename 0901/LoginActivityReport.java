import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class LoginRecord {
    String account;
    String ip;
    LoginRecord(String account, String ip) {
        this.account = account;
        this.ip = ip;
    }
}

public class LoginActivityReport {
    public static void analyzeLogins(List<LoginRecord> records) {
        Map<String, Integer> accountCounts = new HashMap<>();
        Set<String> uniqueIps = new HashSet<>();

        if (records != null) {
            for (LoginRecord record : records) {
                if (record.account != null) {
                    accountCounts.put(record.account, accountCounts.getOrDefault(record.account, 0) + 1);
                }
                if (record.ip != null) {
                    uniqueIps.add(record.ip);
                }
            }
        }

        System.out.println("--- 異常重複登入報告 ---");
        List<String> abnormalAccounts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : accountCounts.entrySet()) {
            if (entry.getValue() > 1) {
                abnormalAccounts.add(entry.getKey() + " (" + entry.getValue() + " times)");
            }
        }
        Collections.sort(abnormalAccounts); // 保證 HashSet 衍生結果順序固定
        for (String acc : abnormalAccounts) {
            System.out.println(acc);
        }
        
        System.out.println("不同 IP 數量: " + uniqueIps.size());
    }

    public static void main(String[] args) {
        List<LoginRecord> logs = new ArrayList<>();
        logs.add(new LoginRecord("User1", "192.168.1.1"));
        logs.add(new LoginRecord("User2", "192.168.1.2"));
        logs.add(new LoginRecord("User1", "192.168.1.3"));
        analyzeLogins(logs);
    }
}