package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    private Map<Character, Integer> prices;
    private Map<Character, List<Offer>> offers;
    private Map<Character, FreeItemOffer> freeItemOffers;

    //+------+-------+------------------------+
    //| Item | Price | Special offers         |
    //+------+-------+------------------------+
    //| A    | 50    | 3A for 130, 5A for 200 |
    //| B    | 30    | 2B for 45              |
    //| C    | 20    |                        |
    //| D    | 15    |                        |
    //| E    | 40    | 2E get one B free      |
    //+------+-------+------------------------+
    public CheckoutSolution() {
        prices = new HashMap<>();
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        prices.put('E', 40);

        offers = new HashMap<>();
        offers.put('A', List.of(new Offer(5, 200), new Offer(3, 130)));
        offers.put('B', List.of(new Offer(2, 45)));

        freeItemOffers = new HashMap<>();
        freeItemOffers.put('E', new FreeItemOffer(2, 'B'));
    }

    public Integer checkout(String skus) {
        Map<Character, Integer> basketStockCount = new HashMap<>();
        Map<Character, Integer> freeItems = new HashMap<>();


        for(Character stock: skus.toCharArray()){
            if(!prices.containsKey(stock)) return -1;
            basketStockCount.put(stock, basketStockCount.getOrDefault(stock, 0) + 1);
        }

        // calculate free Items
        for(Map.Entry<Character, Integer> entry: basketStockCount.entrySet()) {
            int count = entry.getValue();
            if (freeItemOffers.containsKey(entry.getKey())) {
                FreeItemOffer freeItemOffer = freeItemOffers.get(entry.getKey());
                if(count > freeItemOffer.getCount()) {
                    freeItems.put(
                            freeItemOffer.getFreeItem(),
                            freeItems.getOrDefault(freeItemOffer.getFreeItem(), 0) + count / freeItemOffer.getCount()
                    );
                }
            }
        }

        int total = 0;
        for(Map.Entry<Character, Integer> entry: basketStockCount.entrySet()) {
            int count = entry.getValue();
            int price = prices.get(entry.getKey());

            while (count > 0) {
                if (offers.containsKey(entry.getKey())) {
                    List<Offer> ItemOffers = offers.get(entry.getKey());
                    for(Offer offer: ItemOffers) {
                        int offerPrice = offer.getPrice();
                        int offerCount = offer.getCount();
                        if(count >= offerCount) {
                            int offerQuantity = count / offerCount;
                            total += (offerQuantity * offerPrice);
                            count -= offerCount * offerQuantity;
                        }
                    }
                }

                total += (count * price);
                count = 0;
            }

        }

        for(Map.Entry<Character, Integer> entry: freeItems.entrySet()) {
            int freeItemPrice = prices.get(entry.getKey());
            if(basketStockCount.containsKey(entry.getKey())) {
                total -= (freeItemPrice * entry.getValue());
            }
        }

        return total;
    }
}

