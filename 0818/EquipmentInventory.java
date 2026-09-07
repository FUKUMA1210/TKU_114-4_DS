class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        if (id == null || id.trim().isEmpty()) {
            this.id = "Unknown";
        } else {
            this.id = id;
        }

        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }

        if (availableCount < 0) {
            this.availableCount = 0;
        } else {
            this.availableCount = availableCount;
        }
    }

    public boolean borrowOne() {
        if (availableCount > 0) {
            availableCount--;
            return true;
        }

        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return "設備編號：" + id
                + "，名稱：" + name
                + "，可借數量：" + availableCount;
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment equipment1 = new Equipment("E001", "筆記型電腦", 2);
        Equipment equipment2 = new Equipment("E002", "投影機", 0);

        System.out.println(equipment1);
        System.out.println("借用筆記型電腦：" + equipment1.borrowOne());
        System.out.println("借用筆記型電腦：" + equipment1.borrowOne());
        System.out.println("再次借用筆記型電腦：" + equipment1.borrowOne());

        equipment1.returnItems(3);
        System.out.println("歸還3台後：" + equipment1);

        System.out.println();

        System.out.println(equipment2);
        System.out.println("借用投影機：" + equipment2.borrowOne());
        equipment2.returnItems(2);
        System.out.println("歸還2台後：" + equipment2);
    }
}