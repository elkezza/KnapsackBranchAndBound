
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The Knapsack class provides methods to solve the knapsack problem using the branch and bound technique.
 */
class Knapsack {


    /**
     * Solves the 0/1 knapsack problem using branch and bound.
     *
     * @param items     an array of items considered for the knapsack.
     * @param capacity  the maximum weight capacity of the knapsack.
     * @return the maximum profit that can be obtained.
     */
    public long branchAndBound(Item[] items, int capacity) {
        sortByRatio(items); // Sort items by value to weight ratio
        //PriorityQueue<Node> Q = new PriorityQueue<>(Comparator.comparingInt((Node n) -> -n.bound));
        // PriorityQueue to select mot promising node to explore, node that has higher bounds
        PriorityQueue<Node> Q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Long.compare(o2.getBound(), o1.getBound());
            }
        });
        // Start with a dummy node at level -1 and profit 0
        Node node = new Node(-1, 0, 0);
        Q.offer(node);

        // track the maximum profit found
        long maxProfit = 0;

        while (!Q.isEmpty()) {
            node = Q.poll();
            // Check if this is the first node or if we can add the next item
            // If we haven't started (level == -1) or if the current weight doesn't exceed the capacity,
            // we can continue branching.

            if (node.getLevel() == -1 || node.getWeight() < capacity) {
                // Consider the next item (level + 1) to include in the knapsack.
                Node nextNode = new Node(node.getLevel() + 1, node.getProfit() + items[node.getLevel() + 1].getValue(), node.getWeight() + items[node.getLevel() + 1].getWeight());

                // If adding the item doesn't exceed the capacity and the profit is greater than the current maxProfit,
                // Update maxProfit, if this node is promising
                if (nextNode.getWeight() <= capacity && nextNode.getProfit() > maxProfit) {
                    maxProfit = nextNode.getProfit();
                }

                // Calculate bound for the node with the next item added
                nextNode.setBound( calculateBound(nextNode, items, capacity));
                if (nextNode.getBound() > maxProfit) {
                    Q.offer(nextNode);
                }

                // Consider the next item not included
                Node u2 = new Node(node.getLevel() + 1, node.getProfit(), node.getWeight());
                u2.setBound( calculateBound(u2, items, capacity));
                if (u2.getBound() > maxProfit) {
                    Q.offer(u2);
                }
            }
        }

        return maxProfit;
    }

    /**
     * Sorts an array of items based on their value-to-weight ratio in descending order.
     *
     * @param items the array of items to be sorted.
     */
    private void sortByRatio(Item[] items) {
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Double.compare((double)o2.getValue() / o2.getWeight(), (double)o1.getValue() / o1.getWeight());
            }
        });
    }

    /**
     * Calculates the upper bound of the maximum profit that can be achieved with the given node and remaining items.
     *
     * @param u         the node for which to calculate the bound.
     * @param items     an array of remaining items.
     * @param capacity  the remaining capacity of the knapsack.
     * @return the upper bound of the maximum profit.
     */
    private long calculateBound(Node u, Item[] items, long capacity) {
        if (u.getWeight() >= capacity) return 0;
        long profitBound = u.getProfit();
        int j = u.getLevel() + 1;
        long totweight = u.getWeight();
        // Include items while there is room in the knapsack
        while (j < items.length && totweight + items[j].getWeight() <= capacity) {
            totweight += items[j].getWeight();
            profitBound += items[j].getValue();
            j++;
        }
        // If room is left in the knapsack, include a fraction of the next item
        if (j < items.length) {
            profitBound += (capacity - totweight) * items[j].getValue() / items[j].getWeight();
        }
        return profitBound;
    }
}