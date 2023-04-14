import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProjectDB {
    private LinkedHashMap<String, Project> projectLinkedHashMap;
    private final String filePath = "projects2.txt";
    private final TxtReaderWriter projectReaderWriter;

    private static ProjectDB instance = null;

    private ProjectDB() {
        this.projectReaderWriter = new TxtReaderWriter(this.filePath);
        this.projectLinkedHashMap = loadProjectLinkedHashMap(projectReaderWriter.getRawData());
    }

    public static ProjectDB getInstance() {
        if (instance == null) {
            instance = new ProjectDB();
        }
        return instance;
    }

    public LinkedHashMap<String, Project> getProjectLinkedHashMap() {
        return projectLinkedHashMap;
    }

    public void printAllProjects() {
        LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
        for (Project project: projects.values()) {
            project.toString();
        }
    }

//    public void close() {
//        projectReaderWriter.setRawData(parseProjectHashMap());
//    }

    private LinkedHashMap<String, Project> loadProjectLinkedHashMap(ArrayList<String[]> rawData) {
        LinkedHashMap<String, Project> projectLinkedHashMap = new LinkedHashMap<>();
        for (String[] line : rawData) {
            int projID = Integer.parseInt(line[0]);
            String projSupervisor = line[1];
            String projSupervisorEmail = line[2];
            String projTitle = line[3];
            Project project = new Project(projID, projSupervisor, projSupervisorEmail, projTitle);
            projectLinkedHashMap.put(line[0], project);
        }
        return projectLinkedHashMap;
    }

    // Fix later
//    private ArrayList<String[]> parseProjectHashMap() {
//        List<String[]> arrayList = new ArrayList<>();
//        Set<String> keys = projectHashMap.keySet();
//        for (String key : keys) {
//            Project project = projectHashMap.get(key);
//            String[] out = new String[]{project.getUsername(), project.getPassword(), String.valueOf(credential.getRole())};
//            arrayList.add(out);
//        }
//        return (ArrayList<String[]>) arrayList;
//    }
}
