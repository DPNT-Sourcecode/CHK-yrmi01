package befaster.solutions.CHK;

public class Offer {
    private int count;
    private int price;
    private String freeItem;

    public Offer(int count, int price) {
        this.count = count;
        this.price = price;
        this.freeItem = null;
    }

    public Offer(int count, int price, String freeItem) {
        this.count = count;
        this.price = price;
        this.freeItem = freeItem;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public String getFreeItem() {
        return freeItem;
    }
}
