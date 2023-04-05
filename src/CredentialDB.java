import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CredentialDB {

    private final Map<String, Credential> credentialMap;
    private final String filePath;
    private final TxtReaderWriter credentialReaderWriter;

    private static CredentialDB instance = null;

    private CredentialDB(String filePath) {
        this.filePath = filePath;
        this.credentialReaderWriter = new TxtReaderWriter(filePath);
        this.credentialMap = loadCredentialMap(credentialReaderWriter.getRawData());
    }

    public static CredentialDB getInstance(String filePath) {
        if (instance == null) {
            instance = new CredentialDB(filePath);
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

    public String getRole(String role){
        Credential credential = credentialMap.get(role);
        if (credential != null){
            return credential.getRole();
        }
        return null;
    }

    private Map<String, Credential> loadCredentialMap(ArrayList<String[]> rawData) {
        Map<String, Credential> credentialMap = new HashMap<>();
        for (String[] line : rawData) {
            Credential credential = new Credential(line[0], line[1], line[2]);
            credentialMap.put(line[0], credential);
        }
        return credentialMap;
    }


    private ArrayList<String[]> parseCredentialMap() {
        List<String[]> arrayList = new ArrayList<>();
        Set<String> keys = credentialMap.keySet();
        for (String key : keys) {
            Credential credential = credentialMap.get(key);
            String[] out = new String[]{credential.getUsername(), credential.getPassword(), credential.getRole()};
            arrayList.add(out);
        }
        return (ArrayList<String[]>) arrayList;
    }

}
