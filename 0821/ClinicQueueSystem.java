import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Patient {
    private String patientId;
    private String name;

    public Patient(String patientId, String name) {
        this.patientId = (patientId == null || patientId.trim().isEmpty()) ? "UNKNOWN" : patientId;
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + patientId + "] " + name;
    }
}

public class ClinicQueueSystem {
    private Queue<Patient> waitingQueue = new LinkedList<>();
    private List<Patient> completedList = new ArrayList<>();

    public void register(Patient patient) {
        if (patient != null) {
            waitingQueue.offer(patient);
            System.out.println("掛號成功: " + patient);
        }
    }

    public void cancel(String patientId) {
        if (patientId == null || patientId.trim().isEmpty()) {
            return;
        }
        Iterator<Patient> iterator = waitingQueue.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getPatientId().equals(patientId)) {
                iterator.remove();
                System.out.println("取消掛號成功: " + p);
                return;
            }
        }
        System.out.println("找不到病歷號: " + patientId);
    }

    public void callNext() {
        Patient p = waitingQueue.poll();
        if (p == null) {
            System.out.println("目前無人等候。");
        } else {
            completedList.add(p);
            System.out.println("請 " + p + " 進入診間。");
        }
    }

    public void peekNext() {
        Patient p = waitingQueue.peek();
        if (p == null) {
            System.out.println("目前無人等候。");
        } else {
            System.out.println("下一位是: " + p);
        }
    }

    public void printCompleted() {
        System.out.println("--- 當日完成清單 ---");
        if (completedList.isEmpty()) {
            System.out.println("尚無完成就診紀錄。");
        } else {
            for (Patient p : completedList) {
                System.out.println(p);
            }
        }
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();
        clinic.register(new Patient("P001", "Alice"));
        clinic.register(new Patient("P002", "Bob"));
        clinic.register(new Patient("P003", "Charlie"));

        clinic.peekNext();
        clinic.callNext();

        clinic.cancel("P002");
        clinic.cancel("P999");

        clinic.callNext();
        clinic.callNext();

        clinic.printCompleted();
    }
}