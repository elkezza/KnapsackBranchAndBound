

/**
 * Represents an item with a value and a weight.
 */
public class Item {
    private long value;
    private long weight;
    // Constructor, getters, and setters

    /**
     * Constructs a new item with the specified value and weight.
     *
     * @param value  the value of the item.
     * @param weight the weight of the item.
     */
    public Item(long value, long weight) {
        this.value = value;
        this.weight = weight;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
