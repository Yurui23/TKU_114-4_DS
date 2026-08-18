class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, int initialBalance) {
        this.walletId = (walletId == null) ? "UNKNOWN" : walletId.trim();
        this.owner = (owner == null) ? "UNKNOWN" : owner.trim();
        this.balance = Math.max(0, initialBalance);
        this.transactionCount = 0;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        this.transactionCount++;
        return true;
    }

    public boolean pay(int amount) {
        if (amount <= 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        this.transactionCount++;
        return true;
    }

    public boolean refund(int amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        this.transactionCount++;
        return true;
    }

    public String getWalletId() { return walletId; }
    public String getOwner() { return owner; }
    public int getBalance() { return balance; }
    public int getTransactionCount() { return transactionCount; }

    @Override
    public String toString() {
        return "錢包編號: " + walletId + " | 持有者: " + owner + " | 餘額: $" + balance + " | 交易次數: " + transactionCount;
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        System.out.println("=== 課後作業一：封裝式電子錢包測試 ===");
        DigitalWallet wallet = new DigitalWallet("W1001", "Alex", 1000);
        System.out.println("初始狀態: " + wallet);

        System.out.println("\n--- 測試 1：正常儲值 $500 ---");
        boolean depositOk = wallet.deposit(500);
        System.out.println("儲值結果: " + (depositOk ? "成功" : "失敗") + " | 當前狀態: " + wallet);

        System.out.println("\n--- 測試 2：正常付款 $300 ---");
        boolean payOk = wallet.pay(300);
        System.out.println("付款結果: " + (payOk ? "成功" : "失敗") + " | 當前狀態: " + wallet);

        System.out.println("\n--- 測試 3：餘額不足付款 $2000 (應失敗且狀態不變) ---");
        boolean overpayOk = wallet.pay(2000);
        System.out.println("付款結果: " + (overpayOk ? "成功" : "失敗") + " | 當前狀態: " + wallet);

        System.out.println("\n--- 測試 4：無效金額測試 (負數金額) ---");
        boolean invalidPay = wallet.pay(-100);
        System.out.println("負數付款結果: " + (invalidPay ? "成功" : "失敗") + " | 當前狀態: " + wallet);

        System.out.println("\n--- 測試 5：退款 $200 ---");
        boolean refundOk = wallet.refund(200);
        System.out.println("退款結果: " + (refundOk ? "成功" : "失敗") + " | 當前狀態: " + wallet);
    }
}