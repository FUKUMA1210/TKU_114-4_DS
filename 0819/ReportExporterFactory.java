interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {

    public void export(String title, int[] values) {
        System.out.println("CSV 報表：" + title);

        if (values == null) {
            System.out.println("沒有資料");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }
}

class JsonExporter implements ReportExporter {

    public void export(String title, int[] values) {
        System.out.println("JSON 報表：" + title);

        if (values == null) {
            System.out.println("沒有資料");
            return;
        }

        System.out.print("[");

        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);

            if (i < values.length - 1) {
                System.out.print(",");
            }
        }

        System.out.println("]");
    }
}

class TextExporter implements ReportExporter {

    public void export(String title, int[] values) {
        System.out.println("文字報表：" + title);

        if (values == null) {
            System.out.println("沒有資料");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            System.out.println("數值：" + values[i]);
        }
    }
}

public class ReportExporterFactory {

    public static ReportExporter createExporter(String format) {

        if (format == null) {
            return new TextExporter();
        }

        if (format.equals("csv")) {
            return new CsvExporter();
        }

        if (format.equals("json")) {
            return new JsonExporter();
        }

        if (format.equals("text")) {
            return new TextExporter();
        }

        return new TextExporter();
    }

    public static void exportReport(
            ReportExporter exporter, String title, int[] values) {

        exporter.export(title, values);
    }

    public static void main(String[] args) {

        int[] values = {10, 20, 30};

        ReportExporter exporter1 = createExporter("csv");
        exportReport(exporter1, "CSV成績報表", values);

        System.out.println();

        ReportExporter exporter2 = createExporter("json");
        exportReport(exporter2, "JSON成績報表", values);

        System.out.println();

        ReportExporter exporter3 = createExporter("abc");
        exportReport(exporter3, "未知格式報表", values);

        System.out.println();

        ReportExporter exporter4 = createExporter("text");
        exportReport(exporter4, "文字報表", null);
    }
}