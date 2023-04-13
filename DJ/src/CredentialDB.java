import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CredentialDB {

    private final Map<String, Credential> credentialMap;
    private final String filePath = "../credentials.txt";
    private final TxtReaderWriter credentialReaderWriter;

    private static CredentialDB instance = null;

    private CredentialDB() {
        this.credentialReaderWriter = new TxtReaderWriter(this.filePath);
        this.credentialMap = loadCredentialMap(credentialReaderWriter.getRawData());
    }

    public static CredentialDB getInstance() {
        if (instance == null) {
            instance = new CredentialDB();
        }
        return instance;
    }

    public void close() {
        credentialReaderWriter.setRawData(parseCredentialMap());
    }

    public String getPassword(String username) {
        Credential credential = credentialMap.get(username);
        if (credential != null) {
            return credential.getPassword();
        }
        return null;
    }

    public UserRole getRole(String username){
        Credential credential = credentialMap.get(username);
        if (credential != null){
            return credential.getRole();
        }
        return null;
    }

    public Credential getCredential(String username){
        Credential credential = credentialMap.get(username);
        if (credential != null){
            return credential;
        }
        return null;
    }

    private Map<String, Credential> loadCredentialMap(ArrayList<String[]> rawData) {
        Map<String, Credential> credentialMap = new HashMap<>();
        for (String[] line : rawData) {
            UserRole user = switch(line[2]){
                case "student" -> UserRole.STUDENT;
                case "supervisor" -> UserRole.SUPERVISOR;
                case "fypcoordinator" -> UserRole.COORDINATOR;
                default -> UserRole.STUDENT;
            };
            Credential credential = new Credential(line[0], line[1], user);
            credentialMap.put(line[0], credential);
        }
        return credentialMap;
    }


    private ArrayList<String[]> parseCredentialMap() {
        List<String[]> arrayList = new ArrayList<>();
        Set<String> keys = credentialMap.keySet();
        for (String key : keys) {
            Credential credential = credentialMap.get(key);
            String[] out = new String[]{credential.getUsername(), credential.getPassword(), String.valueOf(credential.getRole())};
            arrayList.add(out);
        }
        return (ArrayList<String[]>) arrayList;
    }

}
