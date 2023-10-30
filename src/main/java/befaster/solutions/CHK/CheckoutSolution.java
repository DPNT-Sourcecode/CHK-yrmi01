package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//| A    | 50    | 3A for 130, 5A for 200 |
//| B    | 30    | 2B for 45              |
//| C    | 20    |                        |
//| D    | 15    |                        |
//| E    | 40    | 2E get one B free      |
//| F    | 10    | 2F get one F free      |
//| G    | 20    |                        |
//| H    | 10    | 5H for 45, 10H for 80  |
//| I    | 35    |                        |
//| J    | 60    |                        |
//| K    | 80    | 2K for 150             |
//| L    | 90    |                        |
//| M    | 15    |                        |
//| N    | 40    | 3N get one M free      |
//| O    | 10    |                        |
//| P    | 50    | 5P for 200             |
//| Q    | 30    | 3Q for 80              |
//| R    | 50    | 3R get one Q free      |
//| S    | 30    |                        |
//| T    | 20    |                        |
//| U    | 40    | 3U get one U free      |
//| V    | 50    | 2V for 90, 3V for 130  |
//| W    | 20    |                        |
//| X    | 90    |                        |
//| Y    | 10    |                        |
//| Z    | 50    |                        |

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
        if(skus.isEmpty() || skus.isBlank()) return 0;

        Map<String, Integer> quantities = new HashMap<>();

        for (String c: skus.split("")){
            if(!priceList.containsKey(c)) return -1;
            quantities.put(c, quantities.getOrDefault(c, 0) + 1);
        }

        int total = 0;
        Map<String, Integer> freeItemsCount = new HashMap<>();

        for(Map.Entry<String, Integer> e: quantities.entrySet()) {
            int quantity = e.getValue();
            int price = priceList.get(e.getKey());

            while (quantity > 0) {
                if(offers.containsKey(e.getKey())) {
                    for(Offer offer: offers.get(e.getKey())){
                        int offerQuantity = offer.getCount();
                        int offerPrice = offer.getPrice();

                        if(offer.getFreeItem() != null) {
                            String freeItem = offer.getFreeItem();
                            freeItemsCount.put(freeItem, freeItemsCount.getOrDefault(freeItem, 0) + offerQuantity);
                        }
                    }

                } else {
                    total += quantity * price;
                    quantity = 0;
                }
            }

        }

        return total;
    }
}


