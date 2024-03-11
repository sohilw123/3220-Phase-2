import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Backend {
    private DatasetManager datasetManager;
    private UsageStatisticsManager usageStatsManager;
    private final String USER_CREDENTIALS_FILE_PATH = "test1.csv"; // Path to the CSV file

    // Constructor
    public Backend(DatasetManager datasetManager, UsageStatisticsManager usageStatsManager) {
        this.datasetManager = datasetManager;
        this.usageStatsManager = usageStatsManager;
        createIfNotExists(USER_CREDENTIALS_FILE_PATH); // Create user credentials file if not exists
    }

    // Method to authenticate user
    public boolean authenticateUser(String username, String password) {
        Map<String, String> credentials = readUserCredentials();
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }

    // Method to read user credentials from CSV file
    private Map<String, String> readUserCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_CREDENTIALS_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
            	System.out.println(line);
                String[] data = line.split(",");
                credentials.put(data[0], data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(credentials);
        return credentials;
    }

    // Method to create a file if it does not exist
    private void createIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Getter method for datasetManager
    public DatasetManager getDatasetManager() {
        return datasetManager;
    }

    // Getter method for usageStatsManager
    public UsageStatisticsManager getUsageStatisticsManager() {
        return usageStatsManager;
    }
}
