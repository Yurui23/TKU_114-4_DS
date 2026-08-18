class Device {
    public void runDiagnostic() {
        System.out.println("Running Device diagnostic...");
    }
}

class Laptop extends Device {
    @Override
    public void runDiagnostic() {
        System.out.println("Laptop diagnostic: Checking CPU and Memory.");
    }
}

class Printer extends Device {
    @Override
    public void runDiagnostic() {
        System.out.println("Printer diagnostic: Checking ink levels.");
    }

    public void cleanPrintHead() {
        System.out.println("Cleaning Printer head...");
    }
}

class Router extends Device {
    @Override
    public void runDiagnostic() {
        System.out.println("Router diagnostic: Checking network connection.");
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = new Device[4];
        devices[0] = new Laptop();
        devices[1] = new Printer();
        devices[2] = new Router();
        devices[3] = new Printer();

        for (Device device : devices) {
            device.runDiagnostic();
            
            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
            
            System.out.println("-------------------------");
        }
    }
}