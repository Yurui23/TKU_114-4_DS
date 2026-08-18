final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = quantities.clone();
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        return quantities.clone();
    }

    public int totalQuantity() {
        int total = 0;
        for (int q : quantities) {
            total += q;
        }
        return total;
    }

    public int outOfStockCount() {
        int count = 0;
        for (int q : quantities) {
            if (q == 0) {
                count++;
            }
        }
        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        int[] testQuantities = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-01", testQuantities);

        System.out.println("=== 課堂實作題五：Immutable 庫存快照 ===");
        System.out.println("倉庫編號: " + snapshot.getWarehouseId());
        System.out.println("總數量 (totalQuantity): " + snapshot.totalQuantity());
        System.out.println("缺貨品項數 (outOfStockCount): " + snapshot.outOfStockCount());

        System.out.println("----------------------------------------");
        System.out.println("=== 邊界測試：傳入 null 陣列 ===");
        InventorySnapshot nullSnapshot = new InventorySnapshot("WH-NULL", null);
        System.out.println("null 陣列長度: " + nullSnapshot.getQuantities().length);
        System.out.println("null 陣列總數量: " + nullSnapshot.totalQuantity());
        System.out.println("null 陣列缺貨數: " + nullSnapshot.outOfStockCount());

        System.out.println("----------------------------------------");
        System.out.println("=== Immutability 驗證：修改外部原陣列 ===");
        testQuantities[0] = 999;
        System.out.println("修改外部陣列後，快照總數量仍然為: " + snapshot.totalQuantity());
    }
}