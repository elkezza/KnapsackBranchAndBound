
/**
 * Represents a node in the state space tree of the branch and bound process for the knapsack problem.
 */
public class Node {
    private static int idCounter = 0; // To keep track of the node IDs
    private final int nodeId;
    private final Integer parentId; // Parent ID can be null for the root
    private int level; // level of node in decision tree
    private long profit; // profit of nodes on path from root to this node
    private long bound; // upper bound of maximum profit
    private long weight; // total weight so far

    public Node(int level, long profit, long weight) {
        this.nodeId = idCounter++;
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.parentId = null; // Root node has no parent
        this.bound = 0; // Will be set later
    }
    // The constructor for child nodes
    public Node(int level, long profit, long weight, Integer parentId) {
        this.nodeId = idCounter++;
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.parentId = parentId; // Set the parent ID
        this.bound = 0; // Will be set later

    }


    public static void setIdCounter(int idCounter) {
        Node.idCounter = idCounter;
    }

    public int getNodeId() {
        return nodeId;
    }

    public Integer getParentId() {
        return parentId;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public long getBound() {
        return bound;
    }

    public void setBound(long bound) {
        this.bound = bound;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}