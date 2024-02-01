import java.io.*;
import java.util.*;

/**
 * The Main class contains the entry point of the program, which tests the knapsack solver with sample data.
 */
public class Main {

        public static void main(String[] args) {


            try {
                long startTime = System.nanoTime();
                runAllTheDatasets("data/p01/");

                // unknown correlation type
                runAllTheDatasets("data/p01/");
                runAllTheDatasets("data/p02/");
                runAllTheDatasets("data/p03/");
                runAllTheDatasets("data/p04/");
                runAllTheDatasets("data/p05/");
                runAllTheDatasets("data/p06/");
                runAllTheDatasets("data/p07/");
                runAllTheDatasets("data/p08/");
                runAllTheDatasets("data/p09/");
                runAllTheDatasets("data/p10/");

                //Correlation Type Uncorrelated data instances
                runAllTheDatasets("data/p11/");
                runAllTheDatasets("data/p12/");
                runAllTheDatasets("data/p13/");
                runAllTheDatasets("data/p14/");
                runAllTheDatasets("data/p15/");
                runAllTheDatasets("data/p16/");
                runAllTheDatasets("data/p17/");

                // Correlation Type: Weakly correlated instances
                runAllTheDatasets("data/p18/");
                runAllTheDatasets("data/p19/");
                runAllTheDatasets("data/p20/");
                runAllTheDatasets("data/p21/");
                runAllTheDatasets("data/p22/");
                runAllTheDatasets("data/p23/");
                runAllTheDatasets("data/p24/");

                // Correlation Type: Strongly correlated instances
                runAllTheDatasets("data/p25/");
                runAllTheDatasets("data/p26/");
                runAllTheDatasets("data/p27/");
                runAllTheDatasets("data/p28/");
                // // Correlation Type: Strongly correlated instances took too much time
//                runAllTheDatasets("D://project/new version/KnapsackBranchAndBound/data/p29/");
//                runAllTheDatasets("D://project/new version/KnapsackBranchAndBound/data/p30/");
//                runAllTheDatasets("D://project/new version/KnapsackBranchAndBound/data/p31/");

                // Random data instance Side example
                runAllTheDatasets("D://project/new version/KnapsackBranchAndBound/data/p100/");
                // Random data instances
                runAllTheDatasets("D://project/new version/KnapsackBranchAndBound/data/p90/");
                runAllTheDatasets("D://project/new version/KnapsackBranchAndBound/data/p91/");
                runAllTheDatasets("D://project/new version/KnapsackBranchAndBound/data/p92/");

                long endTime = System.nanoTime();
                long duration = (endTime - startTime);// / 1_000_000; // Convert to milliseconds
                System.out.println("Execution Time for all 31 Datasets:: " + duration/ 1_000_000.0 + " ms");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void runAllTheDatasets(String datasetPath) throws IOException {
            System.out.println("################################# Start #################################");
            String basePath = datasetPath;
            try {
                String correlationType =  readCorrelationType(basePath+"description.txt");
                System.out.println(correlationType);
                // reading knapsack capacity
                int capacity = readCapacity(basePath + "capacity.txt");
                Item[] items = readItems(basePath + "weights.txt", basePath + "profits.txt");
                System.out.println("Capacity: "+capacity);
                System.out.println("Items number: "+items.length);
                Knapsack knapsack = new Knapsack();
                // Start timing
                long startTime = System.nanoTime();
                long maxProfit = knapsack.branchAndBound(items, capacity);
                // End timing
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);// / 1_000_000; // Convert to milliseconds

                System.out.println("Maximum Profit: " + maxProfit);

                boolean[] optimalSelection = readOptimalSelection(basePath + "optimal_solution.txt");
                // Read the known optimal profit
                int optimalProfit = readOptimalProfit(basePath + "profits.txt",basePath + "optimal_solution.txt");

                // Compare the calculated profit with the known optimal profit
                if (maxProfit == optimalProfit) {
                    System.out.println( "\u001B[102m"+"Success! "+ "\u001B[0m"+"The calculated profit matches the known optimal profit.");

                } else {
                    System.out.println("\u001B[101m"+"Mismatch!"+ "\u001B[0m"+" The calculated profit does not match the known optimal profit.");
                }
                System.out.println("Known Optimal Profit: " + optimalProfit);
                System.out.println("Execution Time: " + duration/ 1_000_000.0 + " ms");

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("################################# end   #################################");
        }

    /**
     * Reads the capacity of the knapsack from a given file.
     *
     * @param filePath the path to the file containing the knapsack capacity.
     * @return the capacity as an integer.
     * @throws IOException if an I/O error occurs reading from the file.
     */
    private static int readCapacity(String filePath) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int capacity = Integer.parseInt(reader.readLine().trim());
        reader.close();
        return capacity;
    }

    /**
     * Reads the capacity of the knapsack from a given file.
     *
     * @param filePath the path to the file containing the knapsack capacity.
     * @return the capacity as an integer.
     * @throws IOException if an I/O error occurs reading from the file.
     */
    private static String readCorrelationType(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder correlationType = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    correlationType.append(line.trim()).append(System.lineSeparator());
                }
            }
            return correlationType.toString().trim(); // Convert the StringBuilder to a string and trim it
        }
    }
    /**
     * Reads items with their weights and values from corresponding files.
     *
     * @param weightsFilePath the path to the file containing the weights of the items.
     * @param profitsFilePath the path to the file containing the profits of the items.
     * @return an array of {@code Item} objects constructed from the file data.
     * @throws IOException if an I/O error occurs reading from the files.
     */
    private static Item[] readItems(String weightsFilePath, String profitsFilePath) throws IOException {

        List<Item> items = new ArrayList<>();
        BufferedReader weightReader = new BufferedReader(new FileReader(weightsFilePath));
        BufferedReader profitReader = new BufferedReader(new FileReader(profitsFilePath));

        String weightLine;
        while ((weightLine = weightReader.readLine()) != null) {
            int weight = Integer.parseInt(weightLine.trim());
            int profit = Integer.parseInt(profitReader.readLine().trim());
            items.add(new Item(profit, weight));
        }

        weightReader.close();
        profitReader.close();
        return items.toArray(new Item[0]);
    }


    /**
     * Reads the optimal selection of items from a file where each line corresponds to whether an item is selected.
     *
     * @param filePath the path to the file containing the optimal selection of items.
     * @return an array of booleans where each boolean indicates if the corresponding item is selected.
     * @throws IOException if an I/O error occurs reading from the file.
     */
    private static boolean[] readOptimalSelection(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<Boolean> selection = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            selection.add(line.trim().equals("1"));
        }
        reader.close();

        boolean[] optimalSelection = new boolean[selection.size()];
        for (int i = 0; i < selection.size(); i++) {
            optimalSelection[i] = selection.get(i);
        }
        return optimalSelection;
    }


    /**
     * Reads the optimal profit from the given files by combining the profits and the optimal selection.
     *
     * @param profitsFilePath    the path to the file containing the profits of the items.
     * @param selectionFilePath  the path to the file containing the optimal selection of items.
     * @return the total profit as an integer.
     * @throws IOException if an I/O error occurs reading from the files.
     */
    private static int readOptimalProfit(String profitsFilePath, String selectionFilePath) throws IOException {
        BufferedReader profitReader = new BufferedReader(new FileReader(profitsFilePath));
        BufferedReader selectionReader = new BufferedReader(new FileReader(selectionFilePath));
        int optimalProfit = 0;
        String selectionLine;

        while ((selectionLine = selectionReader.readLine()) != null) {
            int selected = Integer.parseInt(selectionLine.trim());
            int profit = Integer.parseInt(profitReader.readLine().trim());
            if (selected == 1) {
                optimalProfit += profit;
            }
        }

        profitReader.close();
        selectionReader.close();

        return optimalProfit;
    }
}