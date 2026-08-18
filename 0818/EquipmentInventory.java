class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
        this.availableCount = Math.max(0, availableCount);
    }

    public boolean borrowOne() {
        if (this.availableCount > 0) {
            this.availableCount--;
            return true;
        }
        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            this.availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return "設備編號: " + id + " | 名稱: " + name + " | 可借數量: " + availableCount;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAvailableCount() { return availableCount; }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        System.out.println("=== 課堂實作題一：設備庫存物件測試 ===");

        Equipment eq1 = new Equipment("EQ001", "筆記型電腦", 1);
        Equipment eq2 = new Equipment("", "  ", -5);

        System.out.println("初始狀態：");
        System.out.println(eq1);
        System.out.println(eq2);
        System.out.println("----------------------------------------");

        System.out.println("【測試 1：eq1 借用】");
        System.out.println("第一次借用 eq1: " + (eq1.borrowOne() ? "成功" : "失敗"));
        System.out.println("第二次借用 eq1: " + (eq1.borrowOne() ? "成功" : "失敗"));
        System.out.println("當前 eq1 狀態: " + eq1);
        System.out.println("----------------------------------------");

        System.out.println("【測試 2：eq1 歸還】");
        System.out.println("歸還 -3 個 eq1 (無效數量)...");
        eq1.returnItems(-3);
        System.out.println("當前 eq1 狀態: " + eq1);

        System.out.println("歸還 2 個 eq1 (有效數量)...");
        eq1.returnItems(2);
        System.out.println("當前 eq1 狀態: " + eq1);
    }
}