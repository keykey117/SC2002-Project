import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SupervisorDB {
    /**
     * HashMap containing all the Supervisors
     */
    private final Map<String, Supervisor> supervisorMap;

    private final String filePath = "./data/supervisor.txt";
    private final TxtReaderWriter supervisorReaderWriter;

    private static SupervisorDB instance = null;

    private SupervisorDB() {
        this.supervisorReaderWriter = new TxtReaderWriter(this.filePath);
        this.supervisorMap = loadSupervisorMap(supervisorReaderWriter.getRawData());
    }

    public static SupervisorDB getInstance() {
        if (instance == null) {
            instance = new SupervisorDB();
        }
        return instance;
    }

    public void close() {
        supervisorReaderWriter.setRawData(parseSupervisorMap());
    }

    public Supervisor getSupervisor(String supervisorID){
        return supervisorMap.get(supervisorID);
    }

    private Map<String, Supervisor> loadSupervisorMap(ArrayList<String[]> rawData) {
        Map<String, Supervisor> supervisorMap = new HashMap<>();
        for (String[] line : rawData){
            Supervisor supervisor = new Supervisor(line[0], line[1], line[1].split("@")[0]);
            supervisorMap.put(line[1].split("@")[0], supervisor);
        }
        return supervisorMap;
    }


    private ArrayList<String[]> parseSupervisorMap() {
        List<String[]> arrayList = new ArrayList<>();
        Set<String> keys = supervisorMap.keySet();
        for (String key : keys) {
            Supervisor supervisor = supervisorMap.get(key);
            String[] out = new String[]{supervisor.getName(), supervisor.getEmail()};
            arrayList.add(out);
        }
        return (ArrayList<String[]>) arrayList;
    }


}
