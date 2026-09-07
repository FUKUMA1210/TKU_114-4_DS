class Device {

    public void runDiagnostic() {
        System.out.println("設備執行一般檢查");
    }
}

class Laptop extends Device {

    @Override
    public void runDiagnostic() {
        System.out.println("筆記型電腦：檢查電池與系統");
    }
}

class Printer extends Device {

    @Override
    public void runDiagnostic() {
        System.out.println("印表機：檢查墨水與列印功能");
    }

    public void cleanPrintHead() {
        System.out.println("印表機：清潔列印頭");
    }
}

class Router extends Device {

    @Override
    public void runDiagnostic() {
        System.out.println("路由器：檢查網路連線");
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {

        Device[] devices = {
                new Laptop(),
                new Printer(),
                new Router(),
                new Printer()
        };

        for (Device device : devices) {

            device.runDiagnostic();

            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}