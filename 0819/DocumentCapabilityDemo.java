interface Exportable {
    void exportDoc();
}

interface Compressible {
    void compressDoc();
}

class BackupDocument implements Exportable, Compressible {
    @Override
    public void exportDoc() {
        System.out.println("Exporting document...");
    }

    @Override
    public void compressDoc() {
        System.out.println("Compressing document...");
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument doc = new BackupDocument();

        Exportable exportableRef = doc;
        Compressible compressibleRef = doc;

        System.out.println("exportableRef == compressibleRef: " + (exportableRef == compressibleRef));

        exportableRef.exportDoc();
        
        compressibleRef.compressDoc();
    }
}