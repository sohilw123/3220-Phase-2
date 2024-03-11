import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UsageStatisticsManager {
	private final String USAGE_STATS_FILE_PATH = "usage_statistics.csv"; // Path to the CSV file

	// Constructor
	public UsageStatisticsManager() {
		createIfNotExists(USAGE_STATS_FILE_PATH); // Create usage statistics file if not exists
	}

	// Method to track dataset usage
	public void trackUsage(Dataset dataset) {
		Map<String, Integer> stats = readUsageStatistics();
		String datasetName = dataset.getName();
		int count = stats.getOrDefault(datasetName, 0);
		stats.put(datasetName, count + 1);
		writeUsageStatistics(stats);
	}

	// Method to read usage statistics from CSV file
	private Map<String, Integer> readUsageStatistics() {
		Map<String, Integer> stats = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(USAGE_STATS_FILE_PATH))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				stats.put(data[0], Integer.parseInt(data[1]));
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		return stats;
	}

	// Method to write usage statistics to CSV file
	private void writeUsageStatistics(Map<String, Integer> stats) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(USAGE_STATS_FILE_PATH))) {
			for (Map.Entry<String, Integer> entry : stats.entrySet()) {
				bw.write(entry.getKey() + "," + entry.getValue() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
