class Account {
    private String accountNumber;
    private String ownerName;
    private int balance;

    public Account(String accountNumber, String ownerName, int initialBalance) {
        this.accountNumber = (accountNumber == null) ? "UNKNOWN" : accountNumber.trim();
        this.ownerName = (ownerName == null) ? "UNKNOWN" : ownerName.trim();
        this.balance = Math.max(0, initialBalance);
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public int getBalance() { return balance; }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "帳戶: " + accountNumber + " (" + ownerName + ") | 餘額: $" + balance;
    }
}

class TransferService {
    public static boolean transfer(Account source, Account target, int amount) {
        // 驗證 1：來源與目標不能為 null
        if (source == null || target == null) {
            System.out.println("【轉帳失敗】來源或目標帳戶不可為 null");
            return false;
        }

        // 驗證 2：來源與目標不能是同一個物件
        if (source == target) {
            System.out.println("【轉帳失敗】不能向同一個帳戶進行轉帳");
            return false;
        }

        // 驗證 3：金額必須大於 0 且來源帳戶餘額足夠
        if (amount <= 0) {
            System.out.println("【轉帳失敗】轉帳金額必須大於 0");
            return false;
        }

        if (source.getBalance() < amount) {
            System.out.println("【轉帳失敗】來源帳戶餘額不足");
            return false;
        }

        // 通過所有驗證後執行轉帳交易
        source.withdraw(amount);
        target.deposit(amount);
        System.out.println("【轉帳成功】成功從 " + source.getOwnerName() + " 轉帳 $" + amount + " 至 " + target.getOwnerName());
        return true;
    }
}

public class AccountTransferService {
    public static void main(String[] args) {
        System.out.println("=== 課後作業四：跨帳戶轉帳服務測試 ===");

        Account accA = new Account("ACT-001", "Alice", 5000);
        Account accB = new Account("ACT-002", "Bob", 1000);

        System.out.println("初始狀態:");
        System.out.println(" - " + accA);
        System.out.println(" - " + accB);

        System.out.println("\n--- 測試 1：正常成功轉帳 $2000 ---");
        TransferService.transfer(accA, accB, 2000);
        System.out.println("轉帳後狀態:");
        System.out.println(" - " + accA);
        System.out.println(" - " + accB);

        System.out.println("\n--- 測試 2：餘額不足轉帳 $10000 (應失敗且雙方餘額不變) ---");
        TransferService.transfer(accA, accB, 10000);
        System.out.println("檢查狀態:");
        System.out.println(" - " + accA);
        System.out.println(" - " + accB);

        System.out.println("\n--- 測試 3：同帳戶轉帳 (應失敗) ---");
        TransferService.transfer(accA, accA, 500);

        System.out.println("\n--- 測試 4：null 目標轉帳 (應失敗) ---");
        TransferService.transfer(accA, null, 500);

        System.out.println("\n--- 測試 5：負數金額轉帳 (應失敗) ---");
        TransferService.transfer(accA, accB, -300);

        System.out.println("\n最終帳戶狀態確認:");
        System.out.println(" - " + accA);
        System.out.println(" - " + accB);
    }
}