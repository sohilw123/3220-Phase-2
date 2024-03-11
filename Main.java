import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Backend backend = new Backend(new DatasetManager(), new UsageStatisticsManager());
        Scanner scanner = new Scanner(System.in);

        // Authentication
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = backend.authenticateUser(username, password);
        if (!isAuthenticated) {
            System.out.println("Authentication failed. Invalid username or password.");
            return; // Exit if authentication fails
        }

        System.out.println("Authentication successful.");

        // Loop for handling user choices
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addDataset(backend, scanner);
                    break;
                case 2:
                    updateDataset(backend, scanner);
                    break;
                case 3:
                    deleteDataset(backend, scanner);
                    break;
                case 4:
                    trackDatasetUsage(backend, scanner);
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Display menu options
    private static void displayMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Add Dataset");
        System.out.println("2. Update Dataset");
        System.out.println("3. Delete Dataset");
        System.out.println("4. Track Dataset Usage");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a dataset
    private static void addDataset(Backend backend, Scanner scanner) {
        System.out.print("Enter dataset name: ");
        String name = scanner.nextLine();
        System.out.print("Enter dataset description: ");
        String description = scanner.nextLine();
        System.out.print("Enter dataset fields (comma-separated): ");
        String[] fields = scanner.nextLine().split(",");
        System.out.print("Enter dataset source: ");
        String source = scanner.nextLine();
        
        Dataset dataset = new Dataset(name, description, fields, source);
        backend.getDatasetManager().addDataset(dataset);
        System.out.println("Dataset added successfully.");
    }

    // Update a dataset
    private static void updateDataset(Backend backend, Scanner scanner) {
        System.out.print("Enter dataset name to update: ");
        String name = scanner.nextLine();
        Dataset dataset = backend.getDatasetManager().findDatasetByName(name);
        if (dataset != null) {
            System.out.print("Enter new dataset description: ");
            String description = scanner.nextLine();
            System.out.print("Enter new dataset fields (comma-separated): ");
            String[] fields = scanner.nextLine().split(",");
            System.out.print("Enter new dataset source: ");
            String source = scanner.nextLine();

            dataset.setDescription(description);
            dataset.setFields(fields);
            dataset.setSource(source);

            backend.getDatasetManager().updateDataset(dataset);
            System.out.println("Dataset updated successfully.");
        } else {
            System.out.println("Dataset not found.");
        }
    }

    // Delete a dataset
    private static void deleteDataset(Backend backend, Scanner scanner) {
        System.out.print("Enter dataset name to delete: ");
        String name = scanner.nextLine();
        Dataset dataset = backend.getDatasetManager().findDatasetByName(name);
        if (dataset != null) {
            backend.getDatasetManager().deleteDataset(dataset);
            System.out.println("Dataset deleted successfully.");
        } else {
            System.out.println("Dataset not found.");
        }
    }

    // Track dataset usage
    private static void trackDatasetUsage(Backend backend, Scanner scanner) {
        System.out.print("Enter dataset name to track usage: ");
        String name = scanner.nextLine();
        Dataset dataset = backend.getDatasetManager().findDatasetByName(name);
        if (dataset != null) {
            backend.getUsageStatisticsManager().trackUsage(dataset);
            System.out.println("Dataset usage tracked successfully.");
        } else {
            System.out.println("Dataset not found.");
        }
    }
}
