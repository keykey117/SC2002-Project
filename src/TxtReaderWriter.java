import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Purpose of this class:
 * 1. Read from .txt into raw ArrayList<String[]> using readFile(String fileSource) --> taken care of automatically
 * 2. Write from raw ArrayList<String[]> into .txt file using writeFile(String fileSource) --> taken care of automatically

 * Notes:
 * ArrayList should only be read and written once by respective DB class.
 * Updates to stores should update DB class only and not ArrayList
 */
public class TxtReaderWriter {

    /**
     * ArraryList of String Arrays containing elements from each line of the txt file
     */
    private ArrayList<String[]> rawData = new ArrayList<>();
    /**
     * Header line for each file
     */
    private String header;
    /**
     * File path for txt file
     */
    private final String filePath;

    /**
     * Allows for reading of .txt file
     * @param filePath source of the file
     */
    public TxtReaderWriter(String filePath) {
        this.filePath = filePath;
        readFile();
    }

    /**
     * Obtains Raw data from txt file
     * @return ArrayList which contains String elements from each line of the txt file
     */
    public ArrayList<String[]> getRawData() {
        return rawData;
    }

    /**
     * Changes RawData read from .txt file if there is any update
     * @param rawData new ArrayList of String elements to replace current rawData
     */
    public void setRawData(ArrayList<String[]> rawData) {
        this.rawData = rawData;
        writeFile();
    }

    /**
     * Updates txt file to save before terminating program
     */
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

    /**
     * Reads txt file and splits individual elements in each line by delimiter | which are then passed to rawData
     */
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
