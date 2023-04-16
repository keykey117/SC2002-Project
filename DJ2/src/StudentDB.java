import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDB {

    private final Map<String, Student> studentMap;
    private final String filePath = "./data/students.txt";
    private final TxtReaderWriter studentReaderWriter;

    private static StudentDB instance = null;

    private StudentDB() {
        this.studentReaderWriter = new TxtReaderWriter(this.filePath);
        this.studentMap = loadStudentMap(studentReaderWriter.getRawData());
    }

    public static StudentDB getInstance() {
        if (instance == null) {
            instance = new StudentDB();
        }
        return instance;
    }

    public void close() {
        studentReaderWriter.setRawData(parseStudentMap());
    }

    private Map<String, Student> loadStudentMap(ArrayList<String[]> rawData) {
        Map<String, Student> studentMap = new HashMap<>();
            for (String[] line : rawData){
            Student student = new Student(line[0], line[1], line[1].split("@")[0]);
            studentMap.put(line[1].split("@")[0], student);
        }
        return studentMap;
    }


    private ArrayList<String[]> parseStudentMap() {
        List<String[]> arrayList = new ArrayList<>();
        Set<String> keys = studentMap.keySet();
        for (String key : keys) {
            Student student = studentMap.get(key);
            String[] out = new String[]{student.getName(), student.getEmail()};
            arrayList.add(out);
        }
        return (ArrayList<String[]>) arrayList;
    }

    public Student getStudent(String studentID){
        return studentMap.get(studentID);
    }

}
