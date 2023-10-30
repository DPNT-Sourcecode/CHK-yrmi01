package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    Map<String, Integer> priceList;
    Map<String, List<Offer>> offers;

    public CheckoutSolution() {
        this.priceList = new HashMap<>();
        this.offers = new HashMap<>();
        priceList.put("A", 50);
        priceList.put("B", 30);
        priceList.put("C", 20);
        priceList.put("D", 15);
        priceList.put("E", 40);
        priceList.put("F", 10);
        priceList.put("G", 20);
        priceList.put("H", 10);
        priceList.put("I", 35);
        priceList.put("J", 60);
        priceList.put("K", 80);
        priceList.put("L", 90);
        priceList.put("M", 15);
        priceList.put("N", 40);
        priceList.put("O", 10);
        priceList.put("P", 50);
        priceList.put("Q", 30);
        priceList.put("R", 50);
        priceList.put("S", 30);
        priceList.put("T", 20);
        priceList.put("U", 40);
        priceList.put("V", 50);
        priceList.put("W", 20);
        priceList.put("X", 90);
        priceList.put("Y", 10);
        priceList.put("Z", 50);

        offers.put("A", List.of(new Offer(5, 200), new Offer(3, 130)));
        offers.put("B", List.of(new Offer(2, 45)));
        offers.put("E", List.of(new Offer(2, 80, "B")));
        offers.put("F", List.of(new Offer(2, 20, "F")));
        offers.put("H", List.of(new Offer(10, 90), new Offer(5, 45)));
        offers.put("K", List.of(new Offer(2, 150)));
        offers.put("N", List.of(new Offer(3, 120, "M")));
        offers.put("P", List.of(new Offer(5, 200)));
        offers.put("Q", List.of(new Offer(3, 80)));
        offers.put("R", List.of(new Offer(3, 150, "Q")));
        offers.put("U", List.of(new Offer(3, 120, "U")));
        offers.put("V", List.of(new Offer(3, 130), new Offer(2, 90)));

    }

    public Integer checkout(String skus) {
        if (skus.isEmpty() || skus.isBlank()) return 0;

        Map<String, Integer> quantities = new HashMap<>();

        for (String c : skus.split("")) {
            if (!priceList.containsKey(c)) return -1;
            quantities.put(c, quantities.getOrDefault(c, 0) + 1);
        }

        int total = 0;
        Map<String, Integer> freeItemsCount = new HashMap<>();

        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            String key = entry.getKey();
            int quantity = entry.getValue();
            int price = priceList.get(key);

            while (quantity > 0) {
                System.out.println("looping: " + quantity);
                if (offers.containsKey(key)) {
                    System.out.println("checking key: " + key);
                    for (Offer offer : offers.get(key)) {
                        int offerQuantity = offer.getCount();
                        int offerPrice = offer.getPrice();
                        System.out.println("offerQuant: " + offerQuantity);
                        if (quantity >= offerQuantity) {
                            int eligibleOffers = quantity / offerQuantity;
                            total += eligibleOffers * offerPrice;
                            quantity -= eligibleOffers * offerQuantity;

                            if (offer.getFreeItem() != null) {
                                String freeItem = offer.getFreeItem();
                                freeItemsCount.put(freeItem, freeItemsCount.getOrDefault(freeItem, 0) + eligibleOffers);
                            }
                        } else {
                            total += quantity * price;
                            quantity = 0;
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> item : freeItemsCount.entrySet()) {
            if (quantities.containsKey(item.getKey())) {
                total -= item.getValue() * priceList.get(item.getKey());
            }
        }

        return total;
    }
}



