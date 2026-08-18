import java.util.Objects;

class Transaction {
    private final int sequence;
    private final String type;
    private final int amount;
    private final String note;

    public Transaction(int sequence, String type, int amount, String note) {
        this.sequence = sequence;
        this.type = (type == null) ? "UNKNOWN" : type.toUpperCase().trim();
        this.amount = Math.max(0, amount);
        this.note = (note == null) ? "" : note.trim();
    }

    public int getSequence() { return sequence; }
    public String getType() { return type; }
    public int getAmount() { return amount; }
    public String getNote() { return note; }

    @Override
    public String toString() {
        return String.format("[序號 #%d] 類型: %-12s | 金額: $%6d | 說明: %s",
                sequence, type, amount, note);
    }
}

class Wallet {
    private final String walletId;
    private final String owner;
    private int balance;
    private final Transaction[] transactions;
    private int transactionCount;

    public static final int MAX_TRANSACTIONS = 5;

    public Wallet(String walletId, String owner, int initialBalance) {
        this.walletId = (walletId == null) ? "UNKNOWN" : walletId.trim();
        this.owner = (owner == null) ? "UNKNOWN" : owner.trim();
        this.balance = Math.max(0, initialBalance);
        this.transactions = new Transaction[MAX_TRANSACTIONS];
        this.transactionCount = 0;
    }

    public boolean isFull() {
        return transactionCount >= transactions.length;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) {
            System.out.println("【存款失敗】金額必須大於 0");
            return false;
        }
        if (isFull()) {
            System.out.println("【存款失敗】" + owner + " 的交易紀錄已滿 (容量 " + MAX_TRANSACTIONS + " 筆)，不得修改餘額！");
            return false;
        }

        this.balance += amount;
        this.transactionCount++;
        this.transactions[transactionCount - 1] = new Transaction(transactionCount, "DEPOSIT", amount, "錢包加值");
        System.out.println("【存款成功】" + owner + " 儲值 $" + amount + "，最新餘額: $" + balance);
        return true;
    }

    public boolean pay(int amount) {
        if (amount <= 0) {
            System.out.println("【付款失敗】金額必須大於 0");
            return false;
        }
        if (this.balance < amount) {
            System.out.println("【付款失敗】" + owner + " 餘額不足 (當前: $" + balance + ", 需: $" + amount + ")");
            return false;
        }
        if (isFull()) {
            System.out.println("【付款失敗】" + owner + " 的交易紀錄已滿，不得修改餘額！");
            return false;
        }

        this.balance -= amount;
        this.transactionCount++;
        this.transactions[transactionCount - 1] = new Transaction(transactionCount, "PAY", amount, "消費購物");
        System.out.println("【付款成功】" + owner + " 消費 $" + amount + "，最新餘額: $" + balance);
        return true;
    }

    public boolean transferTo(Wallet target, int amount) {
        if (target == null) {
            System.out.println("【轉帳失敗】目標錢包不可為 null");
            return false;
        }
        if (target == this) {
            System.out.println("【轉帳失敗】不能向自己的錢包轉帳");
            return false;
        }
        if (amount <= 0) {
            System.out.println("【轉帳失敗】轉帳金額必須大於 0");
            return false;
        }
        if (this.balance < amount) {
            System.out.println("【轉帳失敗】" + owner + " 餘額不足 (當前: $" + balance + ", 欲轉: $" + amount + ")");
            return false;
        }

        if (this.isFull()) {
            System.out.println("【轉帳失敗】來源帳戶 (" + owner + ") 交易陣列已滿，不得修改雙方餘額！");
            return false;
        }
        if (target.isFull()) {
            System.out.println("【轉帳失敗】目標帳戶 (" + target.getOwner() + ") 交易陣列已滿，不得修改雙方餘額！");
            return false;
        }

        this.balance -= amount;
        target.balance += amount;

        this.transactionCount++;
        this.transactions[this.transactionCount - 1] = new Transaction(
                this.transactionCount,
                "TRANSFER_OUT",
                amount,
                "轉出至 " + target.getOwner() + " (" + target.getWalletId() + ")"
        );

        target.transactionCount++;
        target.transactions[target.transactionCount - 1] = new Transaction(
                target.transactionCount,
                "TRANSFER_IN",
                amount,
                "收到來自 " + this.owner + " (" + this.walletId + ")"
        );

        System.out.println("【轉帳成功】" + this.owner + " 成功轉帳 $" + amount + " 至 " + target.getOwner());
        return true;
    }

    public Transaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }
        return null;
    }

    public int totalByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return 0;
        }
        String searchType = type.trim().toUpperCase();
        int total = 0;

        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equalsIgnoreCase(searchType)) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    public void printStatement() {
        System.out.println("==================================================================================");
        System.out.println("【電子錢包交易對帳單 (Statement)】");
        System.out.println("錢包編號: " + walletId + " | 持有者: " + owner + " | 當前餘額: $" + balance);
        System.out.println("交易筆數: " + transactionCount + " / " + MAX_TRANSACTIONS + " 筆");
        System.out.println("----------------------------------------------------------------------------------");
        if (transactionCount == 0) {
            System.out.println(" (尚無任何交易紀錄)");
        } else {
            for (int i = 0; i < transactionCount; i++) {
                System.out.println(" " + transactions[i]);
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("【交易分類統計】");
        System.out.println(" - 總加值 (DEPOSIT)     : $" + totalByType("DEPOSIT"));
        System.out.println(" - 總消費 (PAY)         : $" + totalByType("PAY"));
        System.out.println(" - 總轉出 (TRANSFER_OUT): $" + totalByType("TRANSFER_OUT"));
        System.out.println(" - 總轉入 (TRANSFER_IN) : $" + totalByType("TRANSFER_IN"));
        System.out.println("==================================================================================\n");
    }

    public String getWalletId() { return walletId; }
    public String getOwner() { return owner; }
    public int getBalance() { return balance; }
    public int getTransactionCount() { return transactionCount; }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   課後作業五：電子錢包交易系統擴充 - 功能測試");
        System.out.println("=================================================\n");

        Wallet wAlice = new Wallet("W1001", "Alice", 3000);
        Wallet wBob = new Wallet("W1002", "Bob", 1000);

        System.out.println("--- 1. 執行基礎交易 (儲值、付款、跨帳戶轉帳) ---");
        wAlice.deposit(1000);
        wAlice.pay(500);
        wAlice.transferTo(wBob, 1200);
        wBob.pay(300);
        wBob.transferTo(wAlice, 400);

        System.out.println("\n--- 2. 測試標準 1: findTransaction(int sequence) ---");
        int searchSeq = 3;
        Transaction tAlice3 = wAlice.findTransaction(searchSeq);
        System.out.println("Alice 尋找序號 #" + searchSeq + ": " + (tAlice3 != null ? tAlice3 : "未找到"));

        Transaction tNotFound = wAlice.findTransaction(99);
        System.out.println("Alice 尋找序號 #99: " + (tNotFound != null ? tNotFound : "未找到 (回傳 null)"));

        System.out.println("\n--- 3. 測試標準 2: totalByType(String type) ---");
        System.out.println("Alice 的總轉出金額 (TRANSFER_OUT): $" + wAlice.totalByType("TRANSFER_OUT"));
        System.out.println("Bob 的總轉入金額 (TRANSFER_IN): $" + wBob.totalByType("TRANSFER_IN"));

        System.out.println("\n--- 4. 測試標準 4: 交易陣列已滿時不得修改餘額 ---");
        System.out.println("Alice 當前筆數: " + wAlice.getTransactionCount() + ", 當前餘額: $" + wAlice.getBalance());
        wAlice.pay(100);
        System.out.println("Alice 填滿後筆數: " + wAlice.getTransactionCount() + " (已滿容量!)");

        int aliceBalBefore = wAlice.getBalance();
        wAlice.deposit(2000);
        System.out.println("檢查 Alice 餘額是否保持不變: " + (wAlice.getBalance() == aliceBalBefore ? "成功保持原狀 ($" + aliceBalBefore + ")" : "錯誤！餘額被變動"));

        int bobBalBefore = wBob.getBalance();
        wBob.transferTo(wAlice, 200);
        System.out.println("檢查轉帳失敗後 Bob 餘額是否未變: " + (wBob.getBalance() == bobBalBefore ? "成功 ($" + bobBalBefore + ")" : "錯誤！"));

        System.out.println("\n--- 5. 測試標準 5: 印出兩個錢包的完整 Statement ---");
        wAlice.printStatement();
        wBob.printStatement();

        printAssessmentAnswers();
    }

    private static void printAssessmentAnswers() {
        System.out.println("==================================================================================");
        System.out.println("                      【形成性評量觀念解析】");
        System.out.println("==================================================================================");
        System.out.println("1. BankAccount a = new BankAccount(...); BankAccount b = a; 中，建立了幾個物件？");
        System.out.println("   答：只建立了一個 BankAccount 物件。`new` 關鍵字只執行一次；`b = a` 只是將記憶體位址複製給變數 b，兩者指向同一個實例。\n");

        System.out.println("2. 為什麼 balance 不適合設成 static？");
        System.out.println("   答：若設為 `static`，則該欄位屬於 Class 共享，所有客戶的錢包會共用「同一個餘額」，這完全違反了個體帳戶各自獨立的業務邏輯。\n");

        System.out.println("3. private field + public setter 是否一定完成良好封裝？說明原因。");
        System.out.println("   答：不一定。若 setter 完全沒有對傳入值做邊界檢查與業務邏輯校驗（例如允許設定負數 balance 或任意改動狀態），這只是「語法上的存取限制」，並沒有達到保護資料完整性與維護商業規則的「良好封裝」。\n");

        System.out.println("4. Order 包含 Customer 應使用 inheritance 還是 composition？");
        System.out.println("   答：應使用 Composition (組合)。因為 Order 與 Customer 是「Has-A (擁有)」關係而非「Is-A (是一種)」關係（訂單不是一種顧客，而是訂單包含顧客資訊）。\n");

        System.out.println("5. 對 null reference 呼叫 method 會發生什麼問題？");
        System.out.println("   答：會拋出 `java.lang.NullPointerPointerException` (NPE)，導致程式異常中斷。因此呼叫方法前應做判空檢查。\n");

        System.out.println("6. 說明物件陣列比平行陣列容易維護的原因。");
        System.out.println("   答：物件陣列將關聯欄位（如 id, name, price, stock）高內聚封裝在單一物件中，保持資料完整性；平行陣列需維護多個獨立陣列，在排序、刪除或傳遞時極易出現索引錯位與維護困難。");
        System.out.println("==================================================================================");
    }
}