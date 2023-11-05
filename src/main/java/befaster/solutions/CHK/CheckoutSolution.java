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
//+------+-------+------------------------+

    public CheckoutSolution() {
        prices = new HashMap<>();
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        prices.put('E', 40);
        prices.put('F', 10);
        prices.put('G', 20);
        prices.put('H', 10);
        prices.put('I', 35);
        prices.put('J', 60);
        prices.put('K', 80);
        prices.put('L', 90);
        prices.put('M', 15);
        prices.put('N', 40);
        prices.put('O', 10);
        prices.put('P', 50);
        prices.put('Q', 30);
        prices.put('R', 50);
        prices.put('S', 30);
        prices.put('T', 20);
        prices.put('U', 40);
        prices.put('V', 50);
        prices.put('W', 20);
        prices.put('X', 90);
        prices.put('Y', 10);
        prices.put('Z', 50);

        offers = new HashMap<>();
        offers.put('A', List.of(new Offer(5, 200), new Offer(3, 130)));
        offers.put('B', List.of(new Offer(2, 45)));
        offers.put('F', List.of(new Offer(3, 20)));
        offers.put('H', List.of(new Offer(10, 80), new Offer(5, 45)));
        offers.put('K', List.of(new Offer(2, 150)));
        offers.put('P', List.of(new Offer(5, 200)));
        offers.put('Q', List.of(new Offer(3, 80)));
        offers.put('U', List.of(new Offer(4, 120)));
        offers.put('V', List.of(new Offer(3, 130), new Offer(2, 90)));

        freeItemOffers = new HashMap<>();
        freeItemOffers.put('E', new FreeItemOffer(2, 'B'));
        freeItemOffers.put('N', new FreeItemOffer(3, 'M'));
        freeItemOffers.put('R', new FreeItemOffer(3, 'Q'));
    }

    public Integer checkout(String skus) {
        Map<Character, Integer> basketStockCount = new HashMap<>();


        for(Character stock: skus.toCharArray()){
            if(!prices.containsKey(stock)) return -1;
            basketStockCount.put(stock, basketStockCount.getOrDefault(stock, 0) + 1);
        }

        // calculate free Items and remove from stock count
        for(Map.Entry<Character, Integer> entry: basketStockCount.entrySet()) {
            int count = entry.getValue();
            if (freeItemOffers.containsKey(entry.getKey())) {
                FreeItemOffer freeItemOffer = freeItemOffers.get(entry.getKey());
                if(count >= freeItemOffer.getCount()) {
                    if(basketStockCount.containsKey(freeItemOffer.getFreeItem())) {
                        basketStockCount.put(
                                freeItemOffer.getFreeItem(),
                                basketStockCount.get(freeItemOffer.getFreeItem()) - count/freeItemOffer.getCount()
                        );
                    }
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

        return total;
    }
}
