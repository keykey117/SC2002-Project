import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TxtReaderWriter {

    private ArrayList<String[]> rawData = new ArrayList<>();
    private String header;
    private final String filePath;

    public TxtReaderWriter(String filePath) {
        this.filePath = filePath;
        readFile();
    }

    public ArrayList<String[]> getRawData() {
        return rawData;
    }

    public void setRawData(ArrayList<String[]> rawData) {
        this.rawData = rawData;
        writeFile();
    }

    private void writeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(header);
            for (String[] line : rawData) {
                writer.newLine();
                writer.write(String.join("|", line));
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            header = reader.readLine();

            String line = reader.readLine();
            while (line != null) {
                rawData.add(line.split("\\|"));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
