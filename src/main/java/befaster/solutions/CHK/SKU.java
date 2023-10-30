package befaster.solutions.CHK;

public class SKU {
    private String name;
    private Integer price;
    private boolean hasLinkedPromo;

    public SKU(String name, Integer price, boolean hasLinkedPromo) {
        this.name = name;
        this.price = price;
        this.hasLinkedPromo = hasLinkedPromo;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean isHasLinkedPromo() {
        return hasLinkedPromo;
    }
}
