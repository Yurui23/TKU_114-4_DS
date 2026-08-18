interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- 輸出 CSV ---");
        System.out.println("Title," + title);
        System.out.print("Values,");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + (i < values.length - 1 ? "," : ""));
            }
        }
        System.out.println("\n");
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- 輸出 JSON ---");
        System.out.println("{");
        System.out.println("  \"title\": \"" + title + "\",");
        System.out.print("  \"values\": [");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + (i < values.length - 1 ? ", " : ""));
            }
        }
        System.out.println("]");
        System.out.println("}\n");
    }
}

class TextExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- 輸出 TEXT ---");
        System.out.println("Report Title: " + title);
        System.out.print("Data Values: ");
        if (values != null) {
            for (int val : values) {
                System.out.print(val + " ");
            }
        }
        System.out.println("\n");
    }
}

public class ReportExporterFactory {

    public static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }
        if ("csv".equalsIgnoreCase(format)) {
            return new CsvExporter();
        } else if ("json".equalsIgnoreCase(format)) {
            return new JsonExporter();
        } else {
            return new TextExporter();
        }
    }

    public static void exportReport(ReportExporter exporter, String title, int[] values) {
        if (exporter == null) {
            System.out.println("Exporter cannot be null.");
            return;
        }
        String safeTitle = (title == null || title.trim().isEmpty()) ? "Untitled Report" : title;
        exporter.export(safeTitle, values);
    }

    public static void main(String[] args) {
        int[] data = {10, 25, 50, 75};
        
        ReportExporter exp1 = createExporter("json");
        exportReport(exp1, null, data);

        ReportExporter exp2 = createExporter(null);
        exportReport(exp2, "   ", data);
        
        exportReport(null, "Test", data);
    }
}