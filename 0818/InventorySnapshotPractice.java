class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;

        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = new int[quantities.length];

            for (int i = 0; i < quantities.length; i++) {
                this.quantities[i] = quantities[i];
            }
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        int[] copy = new int[quantities.length];

        for (int i = 0; i < quantities.length; i++) {
            copy[i] = quantities[i];
        }

        return copy;
    }

    public int totalQuantity() {
        int total = 0;

        for (int quantity : quantities) {
            total += quantity;
        }

        return total;
    }

    public int outOfStockCount() {
        int count = 0;

        for (int quantity : quantities) {
            if (quantity == 0) {
                count++;
            }
        }

        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        int[] data = {5, 0, 3, 0};

        InventorySnapshot snapshot =
                new InventorySnapshot("W001", data);

        System.out.println("倉庫編號："
                + snapshot.getWarehouseId());

        System.out.println("總數量："
                + snapshot.totalQuantity());

        System.out.println("缺貨項數："
                + snapshot.outOfStockCount());

        data[0] = 100;

        System.out.println();
        System.out.println("修改原本陣列後：");
        System.out.println("總數量："
                + snapshot.totalQuantity());
    }
}