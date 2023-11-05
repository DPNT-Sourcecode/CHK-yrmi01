package befaster.solutions.CHK;

public class FreeItemOffer {
    private int count;
    private Character freeItem;

    public FreeItemOffer(int count, Character freeItem) {
        this.count = count;
        this.freeItem = freeItem;
    }

    public int getCount() {
        return count;
    }

    public Character getFreeItem() {
        return freeItem;
    }
}

